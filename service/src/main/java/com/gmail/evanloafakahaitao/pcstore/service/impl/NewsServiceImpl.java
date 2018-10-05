package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.NewsService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private final NewsDao newsDao;
    private final UserDao userDao;
    private final Converter newsConverter;
    private final DTOConverter newsDTOConverter;

    @Autowired
    public NewsServiceImpl(
            NewsDao newsDao,
            UserDao userDao,
            @Qualifier("newsConverter") Converter newsConverter,
            @Qualifier("newsDTOConverter") DTOConverter newsDTOConverter
    ) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.newsConverter = newsConverter;
        this.newsDTOConverter = newsDTOConverter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findByEmail(articleDTO.getUser().getEmail());
            Article article = (Article) newsConverter.toEntity(articleDTO);
            article.setUser(user);
            newsDao.create(article);
            ArticleDTO articleDTOsaved = (ArticleDTO) newsDTOConverter.toDto(article);
            transaction.commit();
            return articleDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Article", e);
        }
        return null;
    }

    @Override
    public boolean deleteById(ArticleDTO articleDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            newsDao.deleteById(articleDTO.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete news by id", e);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArticleDTO update(ArticleDTO articleDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Article article = newsDao.findOne(articleDTO.getId());
            if (articleDTO.getContent() != null) {
                article.setContent(articleDTO.getContent());
            }
            if (articleDTO.getTitle() != null) {
                article.setTitle(articleDTO.getTitle());
            }
            newsDao.update(article);
            ArticleDTO updatedArticleDTO = (ArticleDTO) newsDTOConverter.toDto(article);
            transaction.commit();
            return updatedArticleDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update news", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ArticleDTO> findAll() {
        Session session = newsDao.getCurrentSession();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Article> articles = newsDao.findAll();
            articleDTOS = newsDTOConverter.toDTOList(articles);
            transaction.commit();
            return articleDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find all news", e);
        }
        return articleDTOS;
    }
}
