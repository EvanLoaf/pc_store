package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;

public class CommentDTOConverter implements DTOConverter<CommentDTO, Comment> {

    private DTOConverter simpleUserDTOConverter = new SimpleUserDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public CommentDTO toDto(Comment entity) {
        return CommentDTO.newBuilder()
                .withId(entity.getId())
                .withContent(entity.getContent())
                .withCreated(entity.getCreated())
                .withUser((SimpleUserDTO) simpleUserDTOConverter.toDto(entity.getUser()))
                .build();
    }
}
