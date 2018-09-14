package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;

import java.util.List;

public interface NewsService {

    NewsDTO save(NewsDTO newsDTO);

    boolean deleteById(NewsDTO newsDTO);

    NewsDTO update(NewsDTO newsDTO);

    List<NewsDTO> findAll();
}
