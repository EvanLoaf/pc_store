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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private final DiscountDao discountDao;
    private final Converter<DiscountDTO, Discount> discountConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Autowired
    public DiscountServiceImpl(
            DiscountDao discountDao,
            @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter
    ) {
        this.discountDao = discountDao;
        this.discountConverter = discountConverter;
        this.discountDTOConverter = discountDTOConverter;
    }

    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        logger.info("Saving Discount");
        Discount discount = discountConverter.toEntity(discountDTO);
        discountDao.create(discount);
        return discountDTOConverter.toDto(discount);
    }

    @Override
    @Transactional(readOnly = true)
    public DiscountDTO findByPercent(DiscountDTO discountDTO) {
        logger.info("Retrieving Discount by Percent");
        Discount discount = discountDao.findByPercent(discountDTO.getPercent());
        return discountDTOConverter.toDto(discount);
    }

    @Override
    public List<DiscountDTO> findAll() {
        logger.info("Retrieving all Discounts");
        List<Discount> discounts = discountDao.findAll();
        return discountDTOConverter.toDTOList(discounts);
    }
}
