package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.ItemDao;
import com.gmail.evanloafakahaitao.OrderDao;
import com.gmail.evanloafakahaitao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.model.Order;
import com.gmail.evanloafakahaitao.model.User;
import com.gmail.evanloafakahaitao.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private ConnectionService connectionService = new ConnectionService();
    private OrderDao orderDao = new OrderDaoImpl();
    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public int save(Long userId, Long vendorCode, int itemQuantity) {
        int savedOrders = 0;
        try (Connection connection = connectionService.getConnection()) {
            String uuid = UUID.randomUUID().toString();
            User user = User.newBuilder()
                    .withId(userId)
                    .build();
            try {
                connection.setAutoCommit(false);
                Item item = itemDao.findByVendorCode(connection, vendorCode);
                Order order = Order.newBuilder()
                        .withUser(user)
                        .withOrderUuid(uuid)
                        .withQuantity(itemQuantity)
                        .build();
                System.out.println("Saving order...");
                savedOrders = orderDao.save(connection, order, item);
                connection.commit();
            } catch (SQLException e) {
                System.out.printf("Error saving order from User ID %d\n", user.getId());
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
        return savedOrders;
    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> orderList = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Finding orders by user id ...");
                connection.setAutoCommit(false);
                List<Order> orderListWithoutItem = orderDao.findByUserId(connection, id);
                orderList = new ArrayList<>();
                for (Order order : orderListWithoutItem) {
                    Item item = itemDao.findById(connection, order.getItem().getId());
                    Order orderWithItem = Order.newBuilder()
                            .withItem(item)
                            .withCreatedDate(order.getCreatedDate())
                            .withQuantity(order.getQuantity())
                            .withId(order.getId())
                            .withOrderUuid(order.getOrderUuid())
                            .build();
                    orderList.add(orderWithItem);
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error retrieving orders by User id");
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
        return orderList;
    }

    @Override
    public int deleteByUuid(String uuid) {
        int deletedOrders = 0;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Deleting order by uuid and items from order ...");
                connection.setAutoCommit(false);
                deletedOrders = orderDao.deleteByUuid(connection, uuid);
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error deleting order");
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
        return deletedOrders;
    }
}
