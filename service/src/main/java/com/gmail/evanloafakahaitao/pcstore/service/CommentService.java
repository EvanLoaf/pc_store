package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;

public interface CommentService {

    CommentDTO save(ArticleDTO articleDTO);

    boolean deleteById(ArticleDTO articleDTO);
}
