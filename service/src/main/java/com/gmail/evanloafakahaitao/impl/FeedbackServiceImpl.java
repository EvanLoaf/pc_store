package com.gmail.evanloafakahaitao.impl;

import com.gmail.evanloafakahaitao.FeedbackDao;
import com.gmail.evanloafakahaitao.connection.ConnectionService;
import com.gmail.evanloafakahaitao.model.Feedback;
import com.gmail.evanloafakahaitao.model.User;
import com.gmail.evanloafakahaitao.FeedbackService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {

    private ConnectionService connectionService = new ConnectionService();
    private FeedbackDao feedbackDao = new FeedbackDaoImpl();

    @Override
    public int save(Long userId, String message) {
        int feedbackSaved = 0;
        try (Connection connection = connectionService.getConnection()) {
            Feedback feedback = Feedback.newBuilder()
                    .withUser(User.newBuilder()
                            .withId(userId)
                            .build())
                    .withMessage(message)
                    .build();
            try {
                System.out.println("Saving feedback ...");
                connection.setAutoCommit(false);
                feedbackSaved = feedbackDao.save(connection, feedback);
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return feedbackSaved;
    }

    @Override
    public List<Feedback> findAll() {
        List<Feedback> feedbackList = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("Retrieving all feedback ...");
                connection.setAutoCommit(false);
                feedbackList = feedbackDao.findAll(connection);
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return feedbackList;
    }
}
