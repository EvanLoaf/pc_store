package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.FeedbackService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class);

    private final FeedbackDao feedbackDao;
    private final UserDao userDao;
    private final Converter<FeedbackDTO, Feedback> feedbackConverter;
    private final DTOConverter<FeedbackDTO, Feedback> feedbackDTOConverter;

    @Autowired
    public FeedbackServiceImpl(
            FeedbackDao feedbackDao,
            UserDao userDao,
            @Qualifier("feedbackConverter") Converter<FeedbackDTO, Feedback> feedbackConverter,
            @Qualifier("feedbackDTOConverter") DTOConverter<FeedbackDTO, Feedback> feedbackDTOConverter
    ) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
        this.feedbackConverter = feedbackConverter;
        this.feedbackDTOConverter = feedbackDTOConverter;
    }

    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        logger.info("Saving Feedback");
        Feedback feedback = feedbackConverter.toEntity(feedbackDTO);
        User user = userDao.findOne(
                CurrentUser.getCurrentId()
        );
        feedback.setUser(user);
        feedbackDao.create(feedback);
        return feedbackDTOConverter.toDto(feedback);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDTO> findAll(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all Feedback");
        List<Feedback> feedbacks = feedbackDao.findAll(startPosition, maxResults);
        return feedbackDTOConverter.toDTOList(feedbacks);
    }

    @Override
    public Long deleteById(Long id) {
        logger.info("Deleting Feedback by Id");
        feedbackDao.deleteById(id);
        return id;
    }

    @Override
    public Long countAll() {
        logger.info("Counting all Feedback");
        return feedbackDao.countAll();
    }
}
