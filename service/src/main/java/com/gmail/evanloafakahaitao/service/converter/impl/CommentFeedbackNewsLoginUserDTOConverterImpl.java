package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsLoginUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentFeedbackNewsLoginUserDTOConverterImpl implements DTOConverter<CommentFeedbackNewsLoginUserDTO, User> {
    @Override
    public CommentFeedbackNewsLoginUserDTO toDto(User entity) {
        return CommentFeedbackNewsLoginUserDTO.newBuilder()
                .withEmail(entity.getEmail())
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .build();
    }

    @Override
    public List<CommentFeedbackNewsLoginUserDTO> toDTOList(List<User> entityList) {
        return entityList.stream().map(entity -> CommentFeedbackNewsLoginUserDTO.newBuilder()
                .withEmail(entity.getEmail())
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<CommentFeedbackNewsLoginUserDTO> toDTOSet(Set<User> entitySet) {
        return null;
    }
}
