package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.ItemConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.ItemDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private Converter itemConverter = new ItemConverter();
    private ItemDTOConverter itemDTOConverter = new ItemDTOConverter();

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
}
