package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.dao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.dao.impl.FeedbackDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Feedback;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.FeedbackService;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.FeedbackConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.FeedbackDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.FeedbackDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class);

    private ConnectionService connectionService = new ConnectionService();
    private FeedbackDao feedbackDao = new FeedbackDaoImpl(Feedback.class);
    private Converter feedbackConverter = new FeedbackConverterImpl();
    private DTOConverter feedbackDTOConverter = new FeedbackDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        /*int feedbackSaved = 0;
        try (Connection connection = connectionService.getConnection()) {
            Feedback feedback = Feedback.newBuilder()
                    .withUser(User.newBuilder()
                            .withId(userId)
                            .build())
                    .withMessage(message)
                    .build();
            try {
                logger.info("Saving feedback ...");
                connection.setAutoCommit(false);
                feedbackSaved = feedbackDao.save(connection, feedback);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return feedbackSaved;*/
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Feedback feedback = (Feedback) feedbackConverter.toEntity(feedbackDTO);
            feedbackDao.create(feedback);
            FeedbackDTO savedFeedback = (FeedbackDTO) feedbackDTOConverter.toDto(feedback);
            transaction.commit();
            return savedFeedback;
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
        /*List<Feedback> feedbackList = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                logger.info("Retrieving all feedback ...");
                connection.setAutoCommit(false);
                feedbackList = feedbackDao.findAll(connection);
                connection.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return feedbackList;*/
        Session session = feedbackDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Feedback> feedbackList = feedbackDao.findAll();
            List<FeedbackDTO> feedbackDTOList = feedbackDTOConverter.toDTOList(feedbackList);
            transaction.commit();
            return feedbackDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Feedback", e);
        }
        return null;
    }
}
