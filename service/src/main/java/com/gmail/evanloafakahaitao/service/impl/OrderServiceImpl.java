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
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.OrderConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.OrderDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.service.dto.ShowToUserOrderDTO;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private ConnectionService connectionService = new ConnectionService();
    private OrderDao orderDao = new OrderDaoImpl(Order.class);
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private Converter orderConverter = new OrderConverterImpl();
    private DTOConverter orderDTOConverter = new OrderDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        /*int savedOrders = 0;
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
        return savedOrders;*/
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Order order = (Order) orderConverter.toEntity(orderDTO);
            orderDao.create(order);
            transaction.commit();
            return (OrderDTO) orderDTOConverter.toDto(order);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Order", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ShowToUserOrderDTO> findByUserId(UserDTO userDTO) {
        /*List<Order> orderList = null;
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
        return orderList;*/
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Order> orderList = orderDao.findByUserId(userDTO.getId());
            List<ShowToUserOrderDTO> showToUserOrderDTOList = orderDTOConverter.toDTOList(orderList);
            transaction.commit();
            return showToUserOrderDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find Order by userId", e);
        }
        return null;
    }

    @Override
    public Integer deleteByUuid(OrderDTO orderDTO) {
        /*int deletedOrders = 0;
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
        return deletedOrders;*/
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            int deletedRows = orderDao.deleteByUuid(orderDTO.getUuid());
            transaction.commit();
            return deletedRows;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete Order by uuid", e);
        }
        return null;
    }

    @Override
    public List<OrderDTO> findAll() {
        return null;
    }
}
