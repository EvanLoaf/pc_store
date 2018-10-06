package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Feedback;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("feedbackConverter")
public class FeedbackConverter implements Converter<FeedbackDTO, Feedback> {

    private final Converter<SimpleUserDTO, User> simpleUserConverter;

    @Autowired
    public FeedbackConverter(
            @Qualifier("simpleUserConverter") Converter<SimpleUserDTO, User> simpleUserConverter
    ) {
        this.simpleUserConverter = simpleUserConverter;
    }

    @Override
    public Feedback toEntity(FeedbackDTO dto) {
        Feedback feedback = new Feedback();
        feedback.setMessage(dto.getMessage());
        feedback.setUser(simpleUserConverter.toEntity(dto.getUser()));
        return feedback;
    }
}
