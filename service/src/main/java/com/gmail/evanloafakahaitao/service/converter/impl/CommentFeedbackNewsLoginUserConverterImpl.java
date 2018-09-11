package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsLoginUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentFeedbackNewsLoginUserConverterImpl implements Converter<CommentFeedbackNewsLoginUserDTO, User> {
    @Override
    public User toEntity(CommentFeedbackNewsLoginUserDTO dto) {
        return User.newBuilder()
                .withEmail(dto.getEmail())
                .build();
    }

    @Override
    public List<User> toEntityList(List<CommentFeedbackNewsLoginUserDTO> dtoList) {
        return dtoList.stream().map(dto -> User.newBuilder()
                .withEmail(dto.getEmail())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<User> toEntitySet(Set<CommentFeedbackNewsLoginUserDTO> dtoSet) {
        return null;
    }
}
