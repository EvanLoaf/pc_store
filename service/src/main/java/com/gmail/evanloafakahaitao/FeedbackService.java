package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.Feedback;

import java.util.List;

public interface FeedbackService {

    int save(Long userId, String message);

    List<Feedback> findAll();
}
