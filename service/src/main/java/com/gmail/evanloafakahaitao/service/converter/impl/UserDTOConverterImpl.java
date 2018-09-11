package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.service.dto.RoleDTO;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTOConverterImpl implements DTOConverter<UserDTO, User> {

    private DTOConverter profileDTOConverter = new ProfileDTOConverterImpl();
    private DTOConverter roleDTOConverter = new RoleDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public UserDTO toDto(User entity) {
        return UserDTO.newBuilder()
                .withId(entity.getId())
                .withLastName(entity.getLastName())
                .withFirstName(entity.getFirstName())
                .withPassword(entity.getPassword())
                .withEmail(entity.getEmail())
                .withProfile((ProfileDTO) profileDTOConverter.toDto(entity.getProfile()))
                .withRole((RoleDTO) roleDTOConverter.toDto(entity.getRole()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDTO> toDTOList(List<User> entityList) {
        return entityList.stream().map(entity -> UserDTO.newBuilder()
                .withId(entity.getId())
                .withLastName(entity.getLastName())
                .withFirstName(entity.getFirstName())
                .withPassword(entity.getPassword())
                .withEmail(entity.getEmail())
                .withProfile((ProfileDTO) profileDTOConverter.toDto(entity.getProfile()))
                .withRole((RoleDTO) roleDTOConverter.toDto(entity.getRole()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<UserDTO> toDTOSet(Set<User> entitySet) {
        return null;
    }
}
