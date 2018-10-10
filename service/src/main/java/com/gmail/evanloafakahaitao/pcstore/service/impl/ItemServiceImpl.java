package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.DiscountDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.DiscountConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.ItemConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.ItemDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemDao itemDao;
    private final DiscountDao discountDao;
    private final Converter<ItemDTO, Item> itemConverter;
    private final DTOConverter<ItemDTO, Item> itemDTOConverter;
    private final DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter;

    @Autowired
    public ItemServiceImpl(
            ItemDao itemDao,
            DiscountDao discountDao,
            @Qualifier("itemConverter") Converter<ItemDTO, Item> itemConverter,
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter,
            @Qualifier("simpleItemDTOConverter") DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter
    ) {
        this.itemDao = itemDao;
        this.discountDao = discountDao;
        this.itemConverter = itemConverter;
        this.itemDTOConverter = itemDTOConverter;
        this.simpleItemDTOConverter = simpleItemDTOConverter;
    }

    //TODO most likely delete this method
    @Override
    public List<ItemDTO> save(List<ItemDTO> itemList) {
        logger.info("Saving List of Items");
        List<Item> savedItems = new ArrayList<>();
        for (ItemDTO itemDTO : itemList) {
            Item item = itemConverter.toEntity(itemDTO);
            itemDao.create(item);
            savedItems.add(item);
        }
        return itemDTOConverter.toDTOList(savedItems);
    }

    @Override
    public List<ItemDTO> findAll(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all Items");
        List<Item> items = itemDao.findAll(startPosition, maxResults);
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public ItemDTO findByVendorCode(ItemDTO itemDTO) {
        logger.info("Retrieving Item by VendorCode");
        Item item = itemDao.findByVendorCode(itemDTO.getVendorCode());
        return itemDTOConverter.toDto(item);
    }

    @Override
    public ItemDTO findById(ItemDTO itemDTO) {
        logger.info("Retrieving Item by Id");
        Item item = itemDao.findOne(itemDTO.getId());
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

    //TODO might need to set disc for price range (Remove start pos, max res if so) ___ or Delete this Method
    @Override
    public List<ItemDTO> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Integer startPos, Integer maxResults) {
        logger.info("Retrieving Items in price range");
        List<Item> items = itemDao.findInPriceRange(minPrice, maxPrice, startPos, maxResults);
        return itemDTOConverter.toDTOList(items);
    }

    //TODO it clears all the existing discount, although we got a set there.. if that's okay
    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        logger.info("Updating Item Discount");
        if (!itemDTO.getDiscounts().isEmpty()) {
            Item item = itemDao.findByVendorCode(itemDTO.getVendorCode());
            Discount discount = discountDao.findByPercent(itemDTO.getDiscounts().iterator().next().getPercent());
            item.getDiscounts().clear();
            item.getDiscounts().add(discount);
            itemDao.update(item);
            return itemDTOConverter.toDto(item);
        } else {
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
    }

    @Override
    public SimpleItemDTO softDelete(SimpleItemDTO simpleItemDTO) {
        logger.info("Soft Deleting Item");
        itemDao.softDelete(simpleItemDTO.getId());
        return simpleItemDTO;
    }

    @Override
    public SimpleItemDTO hardDelete(SimpleItemDTO simpleItemDTO) {
        logger.info("Hard Deleting Item");
        itemDao.deleteById(simpleItemDTO.getId());
        return simpleItemDTO;
    }

    @Override
    public SimpleItemDTO copy(SimpleItemDTO simpleItemDTO) {
        logger.info("Copying Item");
        Item item = itemDao.findOne(simpleItemDTO.getId());
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

    //TODO most likely wont need these methods... DEL
    /*@Override
    public List<ItemDTO> findByDiscount(DiscountDTO discountDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findByDiscount(discountDTO.getPercent());
            List<ItemDTO> itemDTOS = itemDTOConverter.toDTOList(items);
            transaction.commit();
            return itemDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Items by discount", e);
        }
        return null;
    }

    @Override
    public Long findCountInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Long countOfItems = itemDao.findCountInPriceRange(minPrice, maxPrice);
            transaction.commit();
            return countOfItems;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Item count", e);
        }
        return null;
    }*/
}
