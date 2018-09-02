package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.ItemDao;
import com.gmail.evanloafakahaitao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private ConnectionService connectionService = new ConnectionService();
    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public int save(List<Item> itemList) {
        int savedItems = 0;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Saving list of items ...");
                connection.setAutoCommit(false);
                savedItems = itemDao.save(connection, itemList);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return savedItems;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Finding all items ...");
                connection.setAutoCommit(false);
                itemList = itemDao.findAll(connection);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return itemList;
    }

    @Override
    public Item findByVendorCode(Long vendorCode) {
        Item item = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Finding item by vendor code ...");
                connection.setAutoCommit(false);
                item = itemDao.findByVendorCode(connection, vendorCode);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public Item findById(int itemId) {
        Item item = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Finding item by id ...");
                connection.setAutoCommit(false);
                item = itemDao.findById(connection, itemId);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return item;
    }
}
