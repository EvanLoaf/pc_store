package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;

import java.util.List;

public interface NewsService {

    ArticleDTO save(ArticleDTO articleDTO);

    boolean deleteById(ArticleDTO articleDTO);

    ArticleDTO update(ArticleDTO articleDTO);

    List<ArticleDTO> findAll();
}
