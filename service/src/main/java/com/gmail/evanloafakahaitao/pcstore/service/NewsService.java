package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;

import java.util.List;

public interface NewsService {

    NewsDTO save(NewsDTO newsDTO);

    void deleteById(Long id);

    NewsDTO update(NewsDTO newsDTO);

    List<SimpleArticleDTO> findAll(Integer startPosition, Integer maxResults);

    Long countAll();

    NewsDTO findById(Long id);
}
