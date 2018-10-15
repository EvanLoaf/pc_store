package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ArticleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.CommentDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Article;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.CommentService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Iterator;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;
    private final ArticleDao articleDao;
    private final UserDao userDao;
    private final Converter<CommentDTO, Comment> commentConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;
    private final DTOConverter<ArticleDTO, Article> articleDTOConverter;

    @Autowired
    public CommentServiceImpl(
            CommentDao commentDao,
            ArticleDao articleDao,
            UserDao userDao,
            @Qualifier("commentConverter") Converter<CommentDTO, Comment> commentConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter,
            @Qualifier("articleDTOConverter") DTOConverter<ArticleDTO, Article> articleDTOConverter
    ) {
        this.commentDao = commentDao;
        this.articleDao = articleDao;
        this.userDao = userDao;
        this.commentConverter = commentConverter;
        this.commentDTOConverter = commentDTOConverter;
        this.articleDTOConverter = articleDTOConverter;
    }

    @Override
    public CommentDTO save(ArticleDTO articleDTO) {
        logger.info("Saving comment for article : " + articleDTO.getId());
        if (!articleDTO.getComments().isEmpty()) {
            Article article = articleDao.findOne(articleDTO.getId());
            Comment comment = commentConverter.toEntity(articleDTO.getComments().iterator().next());
            User user = userDao.findOne(CurrentUser.getCurrentId());
            if (comment.getCreated() == null) {
                comment.setCreated(LocalDateTime.now());
            }
            comment.setDeleted(false);
            comment.setUser(user);
            article.getComments().add(comment);
            articleDao.update(article);
            return commentDTOConverter.toDto(comment);
        } else {
            return null;
        }
    }

    @Override
    public ArticleDTO deleteById(ArticleDTO articleDTO) {
        logger.info("Deleting Comment by Id");
        if (!articleDTO.getComments().isEmpty()) {
            Article article = articleDao.findOne(articleDTO.getId());
            Iterator<Comment> iterator = article.getComments().iterator();
            while (iterator.hasNext()) {
                Comment comment = iterator.next();
                if (comment.getId().equals(articleDTO.getComments().iterator().next().getId())) {
                    iterator.remove();
                    break;
                }
            }
            articleDao.update(article);
            return articleDTOConverter.toDto(article);
        } else {
            return articleDTO;
        }
    }
}
