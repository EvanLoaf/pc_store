package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.OrderDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;
    private final ItemDao itemDao;
    private final UserDao userDao;
    private final DTOConverter<OrderDTO, Order> orderDTOConverter;
    private final DTOConverter<SimpleOrderDTO, Order> simpleOrderDTOConverter;

    @Autowired
    public OrderServiceImpl(
            OrderDao orderDao,
            ItemDao itemDao,
            UserDao userDao,
            @Qualifier("orderDTOConverter") DTOConverter<OrderDTO, Order> orderDTOConverter,
            @Qualifier("simpleOrderDTOConverter") DTOConverter<SimpleOrderDTO, Order> simpleOrderDTOConverter
    ) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.userDao = userDao;
        this.orderDTOConverter = orderDTOConverter;
        this.simpleOrderDTOConverter = simpleOrderDTOConverter;
    }

    @Override
    public SimpleOrderDTO save(CreateOrderDTO createOrderDTO) {
        logger.info("Saving Order");
            User user = userDao.findByEmail(createOrderDTO.getUserEmail());
            Item item = itemDao.findByVendorCode(createOrderDTO.getItemVendorCode());
            Order order = new Order();
            order.setQuantity(createOrderDTO.getQuantity());
            order.setCreated(LocalDateTime.now());
            order.setStatus(OrderStatusEnum.NEW);
            order.setUuid(UUID.randomUUID().toString());
            BigDecimal quantity = BigDecimal.valueOf(order.getQuantity());
            BigDecimal itemDiscountFactor;
            if (!item.getDiscounts().isEmpty()) {
                itemDiscountFactor = BigDecimal.valueOf((long) 100 - (long) item.getDiscounts().iterator().next().getPercent())
                        .divide(BigDecimal.valueOf(100));
            } else {
                itemDiscountFactor = BigDecimal.valueOf(1);
            }
            BigDecimal userDiscountFactor;
            if (user.getDiscount() != null) {
                userDiscountFactor = BigDecimal.valueOf((long) 100 - (long) user.getDiscount().getPercent())
                        .divide(BigDecimal.valueOf(100));
            } else {
                userDiscountFactor = BigDecimal.valueOf(1);
            }
            order.setTotalPrice(
                    item.getPrice()
                            .multiply(itemDiscountFactor)
                            .multiply(userDiscountFactor)
                            .multiply(quantity)
            );
            order.setUser(user);
            order.setItem(item);
            orderDao.create(order);
            return simpleOrderDTOConverter.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleOrderDTO> findByCurrentUserId(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving Order by User Id");
        List<Order> orders = orderDao.findByUserId(
                CurrentUserUtil.getCurrentId(),
                startPosition,
                maxResults
        );
        if (!orders.isEmpty()) {
            return simpleOrderDTOConverter.toDTOList(orders);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public SimpleOrderDTO update(SimpleOrderDTO simpleOrderDTO) {
        logger.info("Updating Order");
        if (simpleOrderDTO.getStatus() != null) {
            Order order = orderDao.findByUuid(simpleOrderDTO.getUuid());
            order.setStatus(simpleOrderDTO.getStatus());
            orderDao.update(order);
            return simpleOrderDTOConverter.toDto(order);
        } else {
            return null;
        }
    }

    @Override
    public void deleteByUuid(String uuid) {
        logger.info("Deleting Order by Uuid");
        orderDao.deleteByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(Integer startPosition, Integer maxResults) {
            logger.info("Retrieving all Orders");
            List<Order> orders = orderDao.findAll(startPosition, maxResults);
            return orderDTOConverter.toDTOList(orders);
    }

    @Override
    public Long countAll() {
        logger.info("Counting all Orders");
        return orderDao.countAll();
    }
}
