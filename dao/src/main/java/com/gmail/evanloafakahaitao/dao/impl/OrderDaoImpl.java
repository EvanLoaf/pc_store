package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.OrderDao;
import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.dao.util.OrderConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    private OrderConverter orderConverter = new OrderConverter();

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    @Override
    public int save(Connection connection, Order order, Item item) {
        String saveOrder = "insert into `order`(user_id, uuid, item_id, quantity) values(?, ?, ? , ?)";
        int changedRows = 0;
        try (
                PreparedStatement preparedStatementSave = connection.prepareStatement(saveOrder, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatementSave.setLong(1, order.getUser().getId());
            preparedStatementSave.setString(2, order.getUuid());
            preparedStatementSave.setLong(3, item.getId());
            preparedStatementSave.setInt(4, order.getQuantity());
            changedRows = preparedStatementSave.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return changedRows;
    }

    @Override
    public List<Order> findByUserId(Connection connection, Long id) {
        String findOrdersByUserId = "select id as order_id, uuid, item_id, created, quantity from `order` where user_id = ?";
        List<Order> orderList = new ArrayList<>();
        try (
                PreparedStatement preparedStatementOrders = connection.prepareStatement(findOrdersByUserId);
        ) {
            preparedStatementOrders.setLong(1, id);
            try (ResultSet resultSetOrder = preparedStatementOrders.executeQuery()) {
                while (resultSetOrder.next()) {
                    Order order = orderConverter.toOrder(resultSetOrder);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return orderList;
    }

    @Override
    public int deleteByUuid(Connection connection, String uuid) {
        String deleteOrderByUuid = "delete from `order` where uuid = ?";
        int changedOrderRows = 0;
        try (
                PreparedStatement preparedStatementDeleteOrderByUuid = connection.prepareStatement(deleteOrderByUuid)
        ) {
            preparedStatementDeleteOrderByUuid.setString(1, uuid);
            changedOrderRows = preparedStatementDeleteOrderByUuid.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return changedOrderRows;
    }
}
