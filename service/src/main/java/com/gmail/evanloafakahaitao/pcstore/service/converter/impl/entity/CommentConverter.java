package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component("commentConverter")
public class CommentConverter implements Converter<CommentDTO, Comment> {

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setMessage(dto.getMessage());
        return comment;
    }
}
