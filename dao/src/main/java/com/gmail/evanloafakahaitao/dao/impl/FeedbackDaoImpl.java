package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.dao.model.Feedback;

public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

    public FeedbackDaoImpl(Class<Feedback> clazz) {
        super(clazz);
    }
}
