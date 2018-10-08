package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ArticleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.ArticleService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class ArticleServiceImpl implements ArticleService {

    private static final Logger logger = LogManager.getLogger(ArticleServiceImpl.class);

    private final ArticleDao articleDao;
    private final UserDao userDao;
    private final Converter<ArticleDTO, Article> articleConverter;
    private final DTOConverter<ArticleDTO, Article> articleDTOConverter;

    @Autowired
    public ArticleServiceImpl(
            ArticleDao articleDao,
            UserDao userDao,
            @Qualifier("articleConverter") Converter<ArticleDTO, Article> articleConverter,
            @Qualifier("articleDTOConverter") DTOConverter<ArticleDTO, Article> articleDTOConverter
    ) {
        this.articleDao = articleDao;
        this.userDao = userDao;
        this.articleConverter = articleConverter;
        this.articleDTOConverter = articleDTOConverter;
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        logger.info("Saving News");
        if (articleDTO.getUser().getEmail() != null) {
            User user = userDao.findByEmail(articleDTO.getUser().getEmail());
            Article article = articleConverter.toEntity(articleDTO);
            if (article.getCreated() == null) {
                article.setCreated(LocalDateTime.now());
            }
            article.setUser(user);
            articleDao.create(article);
            return articleDTOConverter.toDto(article);
        } else {
            return null;
        }
    }

    @Override
    public ArticleDTO deleteById(ArticleDTO articleDTO) {
        logger.info("Deleting News by Id");
        articleDao.deleteById(articleDTO.getId());
        return articleDTO;
    }

    @Override
    public ArticleDTO update(ArticleDTO articleDTO) {
        logger.info("Updating News");
        Article article = articleDao.findOne(articleDTO.getId());
        if (articleDTO.getContent() != null && !articleDTO.getContent().equals(article.getContent())) {
            article.setContent(articleDTO.getContent());
        }
        if (articleDTO.getTitle() != null && !articleDTO.getTitle().equals(article.getTitle())) {
            article.setTitle(articleDTO.getTitle());
        }
        articleDao.update(article);
        return articleDTOConverter.toDto(article);
    }

    @Override
    public List<ArticleDTO> findAll(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all News");
        List<Article> articles = articleDao.findAll(startPosition, maxResults);
        return articleDTOConverter.toDTOList(articles);
    }
}
