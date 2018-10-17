package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.DiscountService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private final DiscountDao discountDao;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Autowired
    public DiscountServiceImpl(
            DiscountDao discountDao,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter
    ) {
        this.discountDao = discountDao;
        this.discountDTOConverter = discountDTOConverter;
    }

    @Override
    public List<DiscountDTO> findAll() {
        logger.info("Retrieving all Discounts");
        List<Discount> discounts = discountDao.findAll();
        return discountDTOConverter.toDTOList(discounts);
    }
}
