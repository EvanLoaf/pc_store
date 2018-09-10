package com.gmail.evanloafakahaitao.web;

import com.gmail.evanloafakahaitao.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.dao.ItemDao;
import com.gmail.evanloafakahaitao.dao.OrderDao;
import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.impl.FeedbackDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.OrderDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.*;
import com.gmail.evanloafakahaitao.service.ItemService;
import com.gmail.evanloafakahaitao.service.OrderService;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.impl.ItemConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.service.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.service.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Logger logger = LogManager.getLogger(Main.class);

        /**
         * Item testing
         */
        ItemService itemService = new ItemServiceImpl();
        Converter itemConverter = new ItemConverterImpl();

        ItemDTO itemDTO = ItemDTO.newBuilder()
                .withName("M7y super item")
                .withDescription("Nice item yo")
                .withPrice(BigDecimal.valueOf(11.11))
                .withVendorCode((long) random.nextInt(1111111))
                .build();

        /*ItemDTO itemDTOsaved = itemService.save(itemDTO);

        logger.info("ItemDTO saved id: " + itemDTOsaved.getId());

        //************************************************************

        ItemDTO itemDTObyId = itemService.findById(ItemDTO.newBuilder().withId(3L).build());

        logger.info("Found item by id: " + itemDTO.getName());

        //************************************************************

        ItemDTO itemDTObyVC = itemService.findByVendorCode(ItemDTO.newBuilder().withVendorCode(1010101010L).build());

        logger.info("Found item by VC: " + itemDTObyVC.getName());

        //************************************************************

        List<ItemDTO> itemDTOList = itemService.findAll();

        for (ItemDTO dto : itemDTOList) {
            logger.info("One of all found items: " + dto.getName());
        }*/

        /**
         * Order testing
         */
        OrderService orderService = new OrderServiceImpl();
        OrderDao orderDao = new OrderDaoImpl(Order.class);
        UserDao userDao = new UserDaoImpl(User.class);
        ItemDao itemDao = new ItemDaoImpl(Item.class);
        FeedbackDao feedbackDao = new FeedbackDaoImpl(Feedback.class);

        /*OrderDTO orderDTO = orderService.save(OrderDTO.newBuilder()
                .withUuid(UUID.randomUUID().toString())
                .withStatus("NEW")
                .withCreated(LocalDateTime.now())
                .withQuantity(5)
                .withUser(OrderUserDTO.newBuilder()
                        .withProfile(ProfileDTO.newBuilder()
                                .withUserId(1L)
                                .withAddress("admin address")
                                .withPhoneNumber("admin pn")
                                .build())
                        .withEmail("root@admin")
                        .withName("root admin")
                        .withId(1L)
                        .build())
                .withItem(itemDTO)
                .build());

        logger.info("Saved order for: " + orderDTO.getItem().getName() + " " + orderDTO.getQuantity() + " pcs.");*/

        /*Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            *//*User user = User.newBuilder()
                    .withProfile(Profile.newBuilder()
                            .withUserId(1L)
                            .withAddress("admin address")
                            .withPhoneNumber("admin pn")
                            .build())
                    .withEmail("root@admin")
                    .withFirstName("root")
                    .withLastName("admin")
                    .withId(1L)
                    .withPassword("root")
                    .withRole(Role.newBuilder()
                            .withId(1L)
                            .withName("admin")
                            .build())
                    .build();

            Item item = Item.newBuilder()
                    .withId(3L)
                    .withName("M7y super item")
                    .withDescription("Nice item yo")
                    .withPrice(BigDecimal.valueOf(11.11))
                    .withVendorCode((long) random.nextInt(1111111))
                    .build();*//*

            User user = userDao.findOne(1L);
            //logger.info(user.getRole().getName());

            Item item = itemDao.findOne(3L);

            Order order = Order.newBuilder()
                    .withUuid(UUID.randomUUID().toString())
                    .withStatus("NEW")
                    .withCreated(LocalDateTime.now(Clock.systemDefaultZone()))
                    .withQuantity(5)
                    *//*.withId(OrderId.newBuilder()
                            .withItemId(item.getId())
                            .withUserId(user.getId())
                            .build())*//*
                    .withUser(user)
                    .withItem(item)
                    .build();
            logger.info("user id: " + user.getId() + " item id: " + item.getId());
            orderDao.create(order);

            List<Order> orderFromUserList = orderDao.findByUserId(2L);
            logger.info("Orders from user id 2 list size: " + orderFromUserList.size());

            Integer deletedOrders = orderDao.deleteByUuid("b3549a73-aff1-4301-8956-569b7c715e18");
            logger.info("Deleted orders: " + deletedOrders);

            List<Order> orderList = orderDao.findAll();
            logger.info("Order list size: " + orderList.size());
            for (Order orderInList : orderList) {
                logger.info("item in order: " + orderInList.getItem().getDescription());
                logger.info("user in order: " + orderInList.getUser().getEmail());
            }

            String hql = "from Order as O where O.id.itemId=:itemId and O.id.userId=:userId";
            Query query = orderDao.getCurrentSession().createQuery(hql);
            query.setParameter("itemId", 2L);
            query.setParameter("userId", 3L);
            logger.info("query res: " + ((Order) query.getSingleResult()).getUuid());

            transaction.commit();
            //logger.info("Saved order for: " + order.getItem().getName() + " " + order.getQuantity() + " pcs.");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            logger.error("Failed to save Order", e);
        }*/

        //************************************************************


        /**
         * User testing
         */

        /*Session sessionUserTest = userDao.getCurrentSession();
        try {
            Transaction transaction = sessionUserTest.getTransaction();
            if (!transaction.isActive()) {
                sessionUserTest.beginTransaction();
            }
            List<User> userList = userDao.findAll();
            for (User user : userList) {
                logger.info("user email: " + user.getEmail());
                logger.info("user role: " + user.getRole().getName());
                logger.info("user address: " + user.getProfile().getAddress());
            }

            User user = userDao.findByEmail("root@user");
            logger.info("user with mail root@user role: " + user.getRole().getName());

            for (Permission permission : user.getRole().getPermissionSet()) {
                logger.info("user with mail root@user permissions: " + permission.getName());
            }

            Profile profile = Profile.newBuilder()
                    .withPhoneNumber("555")
                    .withAddress("666")
                    *//*.withUserId()*//*
                    *//*.withUser()*//*
                    .build();

            User userToSave = User.newBuilder()
                    .withPassword("123")
                    .withEmail("456")
                    .withLastName("789")
                    .withFirstName("101112")
                    .withRole(Role.newBuilder()
                            .withId(2L)
                            .withName("user")
                            .build())
                    .withProfile(profile)
                    .build();

            profile.setUser(userToSave);

            userDao.create(userToSave);
            logger.info("saved user: " + userToSave.getId());

            transaction.commit();
        } catch (Exception e) {
            if (sessionUserTest.getTransaction().isActive()) {
                sessionUserTest.getTransaction().rollback();
            }
            logger.error("Failed to retrieve users", e);
        }*/

        /**
         * Feedback testing
         */

        Session sessionTestFeedback = userDao.getCurrentSession();
        try {
            Transaction transaction = sessionTestFeedback.getTransaction();
            if (!transaction.isActive()) {
                sessionTestFeedback.beginTransaction();
            }
            User user = userDao.findOne(1L);
            Feedback feedback = Feedback.newBuilder()
                    .withMessage("msg test")
                    .withUser(user)
                    .build();

            feedbackDao.create(feedback);
            logger.info("created feedback with id: " + feedback.getId());

            List<Feedback> feedbackList = feedbackDao.findAll();
            for (Feedback feedbackItem : feedbackList) {
                logger.info("feedback item: " + feedbackItem.getMessage());
                logger.info("feedback from user: " + feedbackItem.getUser().getEmail());
            }

            transaction.commit();
        } catch (Exception e) {
            if (sessionTestFeedback.getTransaction().isActive()) {
                sessionTestFeedback.getTransaction().rollback();
            }
            logger.error("Failed to retrieve users", e);
        }

    }
}
