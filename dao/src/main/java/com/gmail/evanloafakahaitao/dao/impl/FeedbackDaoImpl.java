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

public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

    private static final Logger logger = LogManager.getLogger(FeedbackDaoImpl.class);

    public FeedbackDaoImpl(Class<Feedback> clazz) {
        super(clazz);
    }
}
