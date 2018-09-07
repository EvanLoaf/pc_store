package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.dao.model.Feedback;
import com.gmail.evanloafakahaitao.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDaoImpl implements FeedbackDao {

    private static final Logger logger = LogManager.getLogger(FeedbackDaoImpl.class);

    @Override
    public int save(Connection connection, Feedback feedback) {
        String saveFeedback = "insert into feedback(user_id, message) values(?, ?)";
        int changedRows = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveFeedback)) {
            //preparedStatement.setLong(1, feedback.getUser().getId());
            preparedStatement.setString(2, feedback.getMessage());
            changedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return changedRows;
    }

    @Override
    public List<Feedback> findAll(Connection connection) {
        String findAllFeedback = "select f.id as feedback_id, f.message, u.first_name, u.last_name, u.email from feedback f join user u on f.user_id = u.id";
        List<Feedback> feedbackList = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(findAllFeedback);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Feedback feedback = Feedback.newBuilder()
                        .withId(resultSet.getLong("feedback_id"))
                        .withMessage(resultSet.getString("message"))
                        /*.withUser(User.newBuilder()
                                .withLastName(resultSet.getString("last_name"))
                                .withFirstName(resultSet.getString("first_name"))
                                .withEmail(resultSet.getString("email"))
                                .build())*/
                        .build();
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return feedbackList;
    }
}
