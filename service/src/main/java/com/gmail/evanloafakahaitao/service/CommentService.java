package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.service.dto.NewsDTO;

import java.util.List;

public interface CommentService {

    CommentDTO save(NewsDTO newsDTO);

    boolean deleteById(NewsDTO newsDTO);

    List<CommentDTO> findByNewsId(NewsDTO newsDTO);
}
