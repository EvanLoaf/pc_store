package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.BusinessCardDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.BusinessCard;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.BusinessCardService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUserExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class BusinessCardServiceImpl implements BusinessCardService {

    private static final Logger logger = LogManager.getLogger(BusinessCardServiceImpl.class);

    private final BusinessCardDao businessCardDao;
    private final Converter<BusinessCardDTO, BusinessCard> businessCardConverter;
    private final DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter;
    private final UserDao userDao;

    @Autowired
    public BusinessCardServiceImpl(
            BusinessCardDao businessCardDao,
            @Qualifier("businessCardConverter") Converter<BusinessCardDTO, BusinessCard> businessCardConverter,
            @Qualifier("businessCardDTOConverter") DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter,
            UserDao userDao
    ) {
        this.businessCardDao = businessCardDao;
        this.businessCardConverter = businessCardConverter;
        this.businessCardDTOConverter = businessCardDTOConverter;
        this.userDao = userDao;
    }

    @Override
    public BusinessCardDTO save(BusinessCardDTO businessCard) {
        logger.info("Saving Business Card");
        User user = userDao.findOne(
                CurrentUserExtractor.getCurrentId()
        );
        BusinessCard businessCardSaving = businessCardConverter.toEntity(businessCard);
        user.getBusinessCards().add(businessCardSaving);
        userDao.update(user);
        return businessCardDTOConverter.toDto(businessCardSaving);
    }

    @Override
    public BusinessCardDTO deleteById(BusinessCardDTO businessCard) {
        logger.info("Deleting Business Card by Id");
        businessCardDao.deleteById(businessCard.getId());
        return businessCard;
    }
}
