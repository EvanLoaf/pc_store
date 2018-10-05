package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.CommentDao;
import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.CommentService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;
    private final NewsDao newsDao;
    private final UserDao userDao;
    private final Converter commentConverter;
    private final DTOConverter commentDTOConverter;

    @Autowired
    public CommentServiceImpl(
            CommentDao commentDao,
            NewsDao newsDao,
            UserDao userDao,
            @Qualifier("commentConverter") Converter commentConverter,
            @Qualifier("commentDTOConverter") DTOConverter commentDTOConverter
    ) {
        this.commentDao = commentDao;
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.commentConverter = commentConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CommentDTO save(ArticleDTO articleDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Article article = newsDao.findOne(articleDTO.getId());
            Comment comment = (Comment) commentConverter.toEntity(articleDTO.getCommentSet().iterator().next());
            User user = userDao.findByEmail(comment.getUser().getEmail());
            comment.setUser(user);
            article.getComments().add(comment);
            newsDao.update(article);
            CommentDTO commentDTOsaved = (CommentDTO) commentDTOConverter.toDto(comment);
            transaction.commit();
            return commentDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Comment", e);
        }
        return null;
    }

    @Override
    public boolean deleteById(ArticleDTO articleDTO) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Long deleteCommentId = articleDTO.getCommentSet().iterator().next().getId();
            Article article = newsDao.findOne(articleDTO.getId());
            Iterator<Comment> iterator = article.getComments().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getId().equals(deleteCommentId)) {
                    iterator.remove();
                    break;
                }
            }
            newsDao.update(article);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete comment by id", e);
        }
        return false;
    }
}
