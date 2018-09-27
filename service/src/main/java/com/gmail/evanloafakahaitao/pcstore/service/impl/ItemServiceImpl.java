package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.DiscountDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.DiscountConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.ItemConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.ItemDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private Converter itemConverter = new ItemConverter();
    private ItemDTOConverter itemDTOConverter = new ItemDTOConverter();
    private Converter discountConverter = new DiscountConverter();

    @Override
    public List<ItemDTO> save(List<ItemDTO> itemList) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> savedItems = new ArrayList<>();
            for (ItemDTO itemDTO : itemList) {
                Item item = (Item) itemConverter.toEntity(itemDTO);
                itemDao.create(item);
                savedItems.add(item);
            }
            List<ItemDTO> itemDTOSsaved = itemDTOConverter.toDTOList(savedItems);
            transaction.commit();
            return itemDTOSsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save List of Items", e);
        }
        return null;
    }

    @Override
    public List<ItemDTO> findAll() {
        Session session = itemDao.getCurrentSession();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findAll();
            itemDTOS = itemDTOConverter.toDTOList(items);
            transaction.commit();
            return itemDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Items", e);
        }
        return itemDTOS;
    }

    @Override
    public ItemDTO findByVendorCode(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemDao.findByVendorCode(itemDTO.getVendorCode());
            ItemDTO itemDTOfound = itemDTOConverter.toDto(item);
            transaction.commit();
            return itemDTOfound;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Item by vendor code", e);
        }
        return null;
    }

    @Override
    public ItemDTO findById(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemDao.findOne(itemDTO.getId());
            ItemDTO itemDTOfound = itemDTOConverter.toDto(item);
            transaction.commit();
            return itemDTOfound;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Item by id", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = (Item) itemConverter.toEntity(itemDTO);
            itemDao.create(item);
            ItemDTO itemDTOsaved = itemDTOConverter.toDto(item);
            transaction.commit();
            return itemDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Item", e);
        }
        return null;
    }

    @Override
    public List<ItemDTO> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Integer startPos, Integer maxResults) {
        Session session = itemDao.getCurrentSession();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findInPriceRange(minPrice, maxPrice, startPos, maxResults);
            List<ItemDTO> itemDTOfound = itemDTOConverter.toDTOList(items);
            transaction.commit();
            return itemDTOfound;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Items in price range", e);
        }
        return itemDTOS;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ItemDTO updateDiscount(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Item item = itemDao.findByVendorCode(itemDTO.getVendorCode());
            Discount discount = discountDao.findByPercent(itemDTO.getDiscounts().iterator().next().getPercent());
            item.getDiscounts().clear();
            item.getDiscounts().add(discount);
            itemDao.update(item);
            ItemDTO itemDTOupdated = (ItemDTO) itemDTOConverter.toDto(item);
            transaction.commit();
            return itemDTOupdated;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update Item", e);
        }
        return null;
    }

    @Override
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
    }
}
