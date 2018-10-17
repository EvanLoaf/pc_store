package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
import org.springframework.stereotype.Component;

@Component("feedbackConverter")
public class FeedbackConverter implements Converter<FeedbackDTO, Feedback> {

    @Override
    public Feedback toEntity(FeedbackDTO dto) {
        Feedback feedback = new Feedback();
        feedback.setMessage(dto.getMessage());
        return feedback;
    }
}
