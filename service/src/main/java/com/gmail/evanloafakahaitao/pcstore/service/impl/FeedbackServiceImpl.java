package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.FeedbackDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.FeedbackService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.FeedbackConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.FeedbackDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
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
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class);

    private final FeedbackDao feedbackDao;
    private final UserDao userDao;
    private final Converter feedbackConverter;
    private final DTOConverter feedbackDTOConverter;

    @Autowired
    public FeedbackServiceImpl(
            FeedbackDao feedbackDao,
            UserDao userDao,
            @Qualifier("feedbackConverter") Converter feedbackConverter,
            @Qualifier("feedbackDTOConverter") DTOConverter feedbackDTOConverter
    ) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
        this.feedbackConverter = feedbackConverter;
        this.feedbackDTOConverter = feedbackDTOConverter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Feedback feedback = (Feedback) feedbackConverter.toEntity(feedbackDTO);
            User user = userDao.findByEmail(feedbackDTO.getUser().getEmail());
            feedback.setUser(user);
            feedbackDao.create(feedback);
            FeedbackDTO feedbackDTOsaved = (FeedbackDTO) feedbackDTOConverter.toDto(feedback);
            transaction.commit();
            return feedbackDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Feedback", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FeedbackDTO> findAll() {
        Session session = feedbackDao.getCurrentSession();
        List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Feedback> feedbackCollection = feedbackDao.findAll();
            feedbackDTOS = feedbackDTOConverter.toDTOList(feedbackCollection);
            transaction.commit();
            return feedbackDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Feedback", e);
        }
        return feedbackDTOS;
    }

    @Override
    public boolean deleteById(FeedbackDTO feedbackDTO) {
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            feedbackDao.deleteById(feedbackDTO.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete feedback by id", e);
        }
        return false;
    }
}
