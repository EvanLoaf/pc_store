package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.ItemDao;
import com.gmail.evanloafakahaitao.service.converter.impl.ItemConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.ItemDTOConverterImpl;
import com.gmail.evanloafakahaitao.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private ItemConverterImpl itemConverter = new ItemConverterImpl();
    private ItemDTOConverterImpl itemDTOConverter = new ItemDTOConverterImpl();

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
                Item item = itemConverter.toEntity(itemDTO);
                itemDao.create(item);
                savedItems.add(item);
            }
            transaction.commit();
            return itemDTOConverter.toDTOList(savedItems);
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
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> itemList = itemDao.findAll();
            List<ItemDTO> itemDTOList = itemDTOConverter.toDTOList(itemList);
            transaction.commit();
            return itemDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Items", e);
        }
        return null;
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
            transaction.commit();
            return itemDTOConverter.toDto(item);
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
            transaction.commit();
            return itemDTOConverter.toDto(item);
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
            Item item = itemConverter.toEntity(itemDTO);
            itemDao.create(item);
            transaction.commit();
            return itemDTOConverter.toDto(item);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Item", e);
        }
        return null;
    }
}
