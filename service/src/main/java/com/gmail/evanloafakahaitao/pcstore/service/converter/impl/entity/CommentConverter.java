package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("commentConverter")
public class CommentConverter implements Converter<CommentDTO, Comment> {

    private final Converter<SimpleUserDTO, User> simpleUserConverter;

    @Autowired
    public CommentConverter(
            @Qualifier("simpleUserConverter") Converter<SimpleUserDTO, User> simpleUserConverter
    ) {
        this.simpleUserConverter = simpleUserConverter;
    }

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setMessage(dto.getMessage());
        /*comment.setCreated(dto.getCreated());
        comment.setUser(simpleUserConverter.toEntity(dto.getUser()));*/
        return comment;
    }
}
