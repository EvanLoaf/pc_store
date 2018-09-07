package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.ItemDao;
import com.gmail.evanloafakahaitao.dao.OrderDao;
import com.gmail.evanloafakahaitao.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.OrderDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private ConnectionService connectionService = new ConnectionService();
    private OrderDao orderDao = new OrderDaoImpl();
    private ItemDao itemDao = new ItemDaoImpl(Item.class);

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
                logger.info("Saving order...");
                savedOrders = orderDao.save(connection, order, item);
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
        return savedOrders;
    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> orderList = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Finding orders by user id ...");
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
        return orderList;
    }

    @Override
    public int deleteByUuid(String uuid) {
        int deletedOrders = 0;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Deleting order by uuid and items from order ...");
                connection.setAutoCommit(false);
                deletedOrders = orderDao.deleteByUuid(connection, uuid);
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
        return deletedOrders;
    }
}
