package com.gmail.evanloafakahaitao.web;

import com.gmail.evanloafakahaitao.dao.ItemDao;
import com.gmail.evanloafakahaitao.dao.impl.ItemDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);
        ItemDao itemDao = new ItemDaoImpl(Item.class);
        Item item = Item.newBuilder()
                .withName("My Item")
                .withDescription("Best Item")
                .withPrice(BigDecimal.valueOf(99.99))
                .withVendorCode(1199119919L)
                .build();
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            itemDao.create(item);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Item! - CREATE METHOD");
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Failed to save Item! - CREATE METHOD");
        }

    }
}
