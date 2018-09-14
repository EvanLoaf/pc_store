package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;

public class CommentConverter implements Converter<CommentDTO, Comment> {

    private Converter simpleUserConverter = new SimpleUserConverter();

    @SuppressWarnings("unchecked")
    @Override
    public Comment toEntity(CommentDTO dto) {
        return Comment.newBuilder()
                .withId(dto.getId())
                .withContent(dto.getContent())
                .withCreated(dto.getCreated())
                .withUser((User) simpleUserConverter.toEntity(dto.getUser()))
                .build();
    }
}
