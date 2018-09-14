package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    FeedbackDTO save(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> findAll();

    boolean deleteById(FeedbackDTO feedbackDTO);
}
