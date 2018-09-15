package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.DiscountDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.DiscountService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.DiscountDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.DiscountConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private Converter discountConverter = new DiscountConverter();
    private DTOConverter discountDTOConverter = new DiscountDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Discount discount = (Discount) discountConverter.toEntity(discountDTO);
            discountDao.create(discount);
            DiscountDTO discountDTOsaved = (DiscountDTO) discountDTOConverter.toDto(discount);
            transaction.commit();
            return discountDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Discount", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public DiscountDTO findByPercent(DiscountDTO discountDTO) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Discount discount = discountDao.findByPercent(discountDTO.getPercent());
            DiscountDTO discountDTOfound = (DiscountDTO) discountDTOConverter.toDto(discount);
            transaction.commit();
            return discountDTOfound;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find Discount by percent", e);
        }
        return null;
    }
}
