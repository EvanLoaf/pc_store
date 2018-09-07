package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.dao.model.Feedback;

import java.util.List;

public interface FeedbackService {

    int save(Long userId, String message);

    List<Feedback> findAll();
}
