package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;

public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

    public FeedbackDaoImpl(Class<Feedback> clazz) {
        super(clazz);
    }
}
