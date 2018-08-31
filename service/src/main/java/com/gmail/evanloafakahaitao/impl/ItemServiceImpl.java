package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.ItemDao;
import com.gmail.evanloafakahaitao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.ItemService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ConnectionService connectionService = new ConnectionService();
    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public int save(List<Item> itemList) {
        int savedItems = 0;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Saving list of items ...");
                connection.setAutoCommit(false);
                savedItems = itemDao.save(connection, itemList);
                connection.commit();
            } catch (SQLException e) {
                System.out.printf("Error saving List of %d items\n", itemList.size());
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return savedItems;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Finding all items ...");
                connection.setAutoCommit(false);
                itemList = itemDao.findAll(connection);
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error retrieving all Items");
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item findByVendorCode(Long vendorCode) {
        Item item = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Finding item by vendor code ...");
                connection.setAutoCommit(false);
                item = itemDao.findByVendorCode(connection, vendorCode);
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error retrieving item by vendor code");
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item findById(int itemId) {
        Item item = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Finding item by id ...");
                connection.setAutoCommit(false);
                item = itemDao.findById(connection, itemId);
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error retrieving item by id");
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return item;
    }
}
