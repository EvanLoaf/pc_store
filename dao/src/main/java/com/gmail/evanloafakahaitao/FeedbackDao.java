package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.Feedback;

import java.sql.Connection;
import java.util.List;

public interface FeedbackDao {

    int save(Connection connection, Feedback feedback);

    List<Feedback> findAll(Connection connection);
}
