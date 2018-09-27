package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.FeedbackDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

    public FeedbackDaoImpl() {
        super(Feedback.class);
    }
}
