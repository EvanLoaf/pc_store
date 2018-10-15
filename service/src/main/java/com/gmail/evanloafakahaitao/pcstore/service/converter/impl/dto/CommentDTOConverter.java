package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component("commentDTOConverter")
public class CommentDTOConverter implements DTOConverter<CommentDTO, Comment> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;

    @Autowired
    public CommentDTOConverter(
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter
    ) {
        this.simpleUserDTOConverter = simpleUserDTOConverter;
    }

    @Override
    public CommentDTO toDto(Comment entity) {
        CommentDTO comment = new CommentDTO();
        comment.setId(entity.getId());
        comment.setMessage(entity.getMessage());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        comment.setCreated(entity.getCreated().format(formatter));
        comment.setUser(simpleUserDTOConverter.toDto(entity.getUser()));
        return comment;
    }
}
