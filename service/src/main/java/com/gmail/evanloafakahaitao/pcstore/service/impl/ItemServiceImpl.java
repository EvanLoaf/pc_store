package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.OrderDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemDao itemDao;
    private final DiscountDao discountDao;
    private final OrderDao orderDao;
    private final Converter<ItemDTO, Item> itemConverter;
    private final DTOConverter<ItemDTO, Item> itemDTOConverter;
    private final DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Autowired
    public ItemServiceImpl(
            ItemDao itemDao,
            DiscountDao discountDao,
            @Qualifier("itemConverter") Converter<ItemDTO, Item> itemConverter,
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter,
            @Qualifier("simpleItemDTOConverter") DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter,
            OrderDao orderDao
    ) {
        this.itemDao = itemDao;
        this.discountDao = discountDao;
        this.itemConverter = itemConverter;
        this.itemDTOConverter = itemDTOConverter;
        this.simpleItemDTOConverter = simpleItemDTOConverter;
        this.discountDTOConverter = discountDTOConverter;
        this.orderDao = orderDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDTO> findAllNotDeleted(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all not deleted Items");
        List<Item> items = itemDao.findAllNotDeleted(startPosition, maxResults);
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDTO findByVendorCode(String vendorCode) {
        logger.info("Retrieving Item by VendorCode");
        Item item = itemDao.findByVendorCode(vendorCode);
        if (item != null) {
            return itemDTOConverter.toDto(item);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDTO findById(Long id) {
        logger.info("Retrieving Item by Id");
        Item item = itemDao.findOne(id);
        return itemDTOConverter.toDto(item);
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        logger.info("Saving Item");
        Item item = itemConverter.toEntity(itemDTO);
        item.setDeleted(false);
        itemDao.create(item);
        return itemDTOConverter.toDto(item);
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        logger.info("Updating Item Discount");
        Item item = itemDao.findOne(itemDTO.getId());
        if (itemDTO.getName() != null) {
            item.setName(itemDTO.getName());
        }
        if (itemDTO.getDescription() != null) {
            item.setDescription(itemDTO.getDescription());
        }
        if (itemDTO.getVendorCode() != null) {
            Item itemByVendorCode = itemDao.findByVendorCode(itemDTO.getVendorCode());
            if (itemByVendorCode == null) {
                item.setVendorCode(itemDTO.getVendorCode());
            }
        }
        if (itemDTO.getPrice() != null) {
            item.setPrice(itemDTO.getPrice());
        }
        itemDao.update(item);
        return itemDTOConverter.toDto(item);
    }

    @Override
    public void softDelete(Long id) {
        logger.info("Soft Deleting Item");
        itemDao.softDelete(id);
    }

    @Override
    public SimpleItemDTO copy(Long id) {
        logger.info("Copying Item");
        Item item = itemDao.findOne(id);
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        String newVendorCode = null;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            newVendorCode = item.getId() + "copy" + i;
            Item itemFound = itemDao.findByVendorCode(newVendorCode);
            if (itemFound == null) {
                break;
            }
        }
        newItem.setVendorCode(newVendorCode);
        newItem.setPrice(item.getPrice());
        newItem.setDeleted(item.isDeleted());
        if (!item.getDiscounts().isEmpty()) {
            newItem.setDiscounts(item.getDiscounts());
        }
        itemDao.create(newItem);
        return simpleItemDTOConverter.toDto(newItem);
    }

    @Override
    public Long countAllNotDeleted() {
        logger.info("Counting all Items");
        return itemDao.countAllNotDeleted();
    }

    @Override
    public DiscountDTO updateDiscountAll(Long discountId, BigDecimal minPriceRange, BigDecimal maxPriceRange) {
        Discount discount = discountDao.findOne(discountId);
        List<Item> items = itemDao.findInPriceRange(minPriceRange, maxPriceRange);
        for (Item item : items) {
            item.getDiscounts().clear();
            item.getDiscounts().add(discount);
            itemDao.update(item);
        }
        return discountDTOConverter.toDto(discount);
    }

    @Override
    public void deleteByOrdersCount(Long id) {
        logger.info("Deleting by order count");
        Long countOfOrdersForItem = orderDao.countByItemId(id);
        if (countOfOrdersForItem != null && countOfOrdersForItem.equals(0L)) {
            itemDao.deleteById(id);
        } else {
            itemDao.softDelete(id);
        }
    }
}
