package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentFeedbackNewsUserDTOConverterImpl<D, E> implements DTOConverter<CommentFeedbackNewsUserDTO, User> {
    @Override
    public CommentFeedbackNewsUserDTO toDto(User entity) {
        return CommentFeedbackNewsUserDTO.newBuilder()
                .withEmail(entity.getEmail())
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .build();
    }

    @Override
    public List<CommentFeedbackNewsUserDTO> toDTOList(List<User> entityList) {
        return entityList.stream().map(entity -> CommentFeedbackNewsUserDTO.newBuilder()
                .withEmail(entity.getEmail())
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<CommentFeedbackNewsUserDTO> toDTOSet(Set<User> entitySet) {
        return null;
    }
}
