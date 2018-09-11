package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.ItemDao;
import com.gmail.evanloafakahaitao.dao.OrderDao;
import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.OrderDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.OrderService;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.OrderConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.OrderDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.ShowToUserOrderDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.service.dto.ShowToUserOrderDTO;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao = new OrderDaoImpl(Order.class);
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private Converter orderConverter = new OrderConverterImpl();
    private DTOConverter orderDTOConverter = new OrderDTOConverterImpl();
    private DTOConverter showToUserOrderDTOConverter = new ShowToUserOrderDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public ShowToUserOrderDTO save(OrderDTO orderDTO) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(orderDTO.getUser().getEmail());
            Item item = itemDao.findByVendorCode(orderDTO.getItem().getVendorCode());
            Order order = (Order) orderConverter.toEntity(orderDTO);
            order.setUser(user);
            order.setItem(item);
            orderDao.create(order);
            transaction.commit();
            return (ShowToUserOrderDTO) showToUserOrderDTOConverter.toDto(order);
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
            logger.error("Failed to find Orders by userId", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ShowToUserOrderDTO findByUuid(OrderDTO orderDTO) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Order order = orderDao.findByUuid(orderDTO.getUuid());
            ShowToUserOrderDTO foundOrderDTO = (ShowToUserOrderDTO) showToUserOrderDTOConverter.toDto(order);
            transaction.commit();
            return foundOrderDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find Orders by uuid", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Order order = orderDao.findByUuid(orderDTO.getUuid());
            if (orderDTO.getStatus() != null) {
                order.setStatus(orderDTO.getStatus());
            }
            User user = userDao.findByEmail(orderDTO.getUser().getEmail());
            Item item = itemDao.findByVendorCode(orderDTO.getItem().getVendorCode());
            order.setUser(user);
            order.setItem(item);
            orderDao.update(order);
            transaction.commit();
            return (OrderDTO) orderDTOConverter.toDto(order);
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update Order", e);
        }
        return null;
    }

    @Override
    public Integer deleteByUuid(OrderDTO orderDTO) {
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

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderDTO> findAll() {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Order> orderList = orderDao.findAll();
            List<OrderDTO> orderDTOList = orderDTOConverter.toDTOList(orderList);
            transaction.commit();
            return orderDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve orders", e);
        }
        return null;
    }
}
