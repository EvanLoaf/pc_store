package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;

public interface CommentService {

    CommentDTO save(NewsDTO newsDTO);

    boolean deleteById(NewsDTO newsDTO);
}
