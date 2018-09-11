package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    FeedbackDTO save(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> findAll();

    boolean deleteById(FeedbackDTO feedbackDTO);
}
