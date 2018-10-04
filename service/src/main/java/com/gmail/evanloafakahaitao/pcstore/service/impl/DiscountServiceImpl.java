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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private final DiscountDao discountDao;
    private final Converter discountConverter;
    private final DTOConverter discountDTOConverter;

    @Autowired
    public DiscountServiceImpl(
            DiscountDao discountDao,
            @Qualifier("discountConverter") Converter discountConverter,
            @Qualifier("discountDTOConverter") DTOConverter discountDTOConverter
    ) {
        this.discountDao = discountDao;
        this.discountConverter = discountConverter;
        this.discountDTOConverter = discountDTOConverter;
    }

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
