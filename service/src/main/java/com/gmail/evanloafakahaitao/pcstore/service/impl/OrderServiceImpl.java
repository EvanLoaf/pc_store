package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.OrderDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.OrderDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.OrderConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.OrderDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.SimpleOrderDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleOrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;
    private final ItemDao itemDao;
    private final UserDao userDao;
    private final Converter orderConverter;
    private final DTOConverter orderDTOConverter;
    private final DTOConverter simpleOrderDTOConverter;

    @Autowired
    public OrderServiceImpl(
            OrderDao orderDao,
            ItemDao itemDao,
            UserDao userDao,
            @Qualifier("orderConverter") Converter orderConverter,
            @Qualifier("orderDTOConverter") DTOConverter orderDTOConverter,
            @Qualifier("simpleOrderDTOConverter") DTOConverter simpleOrderDTOConverter
    ) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.userDao = userDao;
        this.orderConverter = orderConverter;
        this.orderDTOConverter = orderDTOConverter;
        this.simpleOrderDTOConverter = simpleOrderDTOConverter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SimpleOrderDTO save(OrderDTO orderDTO) {
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
            SimpleOrderDTO orderDTOsaved =  (SimpleOrderDTO) simpleOrderDTOConverter.toDto(order);
            transaction.commit();
            return orderDTOsaved;
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
    public List<SimpleOrderDTO> findByUserId(UserDTO userDTO) {
        Session session = orderDao.getCurrentSession();
        List<SimpleOrderDTO> orderDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Order> orders = orderDao.findByUserId(userDTO.getId());
            orderDTOS = simpleOrderDTOConverter.toDTOList(orders);
            transaction.commit();
            return orderDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find Orders by userId", e);
        }
        return orderDTOS;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SimpleOrderDTO findByUuid(OrderDTO orderDTO) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Order order = orderDao.findByUuid(orderDTO.getUuid());
            SimpleOrderDTO foundOrderDTO = (SimpleOrderDTO) simpleOrderDTOConverter.toDto(order);
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
            OrderDTO orderDTOupdated = (OrderDTO) orderDTOConverter.toDto(order);
            transaction.commit();
            return orderDTOupdated;
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
        List<OrderDTO> orderDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Order> orders = orderDao.findAll();
            orderDTOS = orderDTOConverter.toDTOList(orders);
            transaction.commit();
            return orderDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve orders", e);
        }
        return orderDTOS;
    }
}
