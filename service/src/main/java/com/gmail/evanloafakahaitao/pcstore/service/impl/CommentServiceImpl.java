package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.News;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Comment;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.CommentService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Iterator;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private final NewsDao newsDao;
    private final UserDao userDao;
    private final Converter<CommentDTO, Comment> commentConverter;
    private final DTOConverter<CommentDTO, Comment> commentDTOConverter;

    @Autowired
    public CommentServiceImpl(
            NewsDao newsDao,
            UserDao userDao,
            @Qualifier("commentConverter") Converter<CommentDTO, Comment> commentConverter,
            @Qualifier("commentDTOConverter") DTOConverter<CommentDTO, Comment> commentDTOConverter
    ) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.commentConverter = commentConverter;
        this.commentDTOConverter = commentDTOConverter;
    }

    @Override
    public CommentDTO save(NewsDTO newsDTO) {
        logger.info("Saving comment for article : " + newsDTO.getId());
        if (!newsDTO.getComments().isEmpty()) {
            News news = newsDao.findOne(newsDTO.getId());
            Comment comment = commentConverter.toEntity(newsDTO.getComments().iterator().next());
            User user = userDao.findOne(CurrentUserUtil.getCurrentId());
            if (comment.getCreated() == null) {
                comment.setCreated(LocalDateTime.now());
            }
            comment.setDeleted(false);
            comment.setUser(user);
            news.getComments().add(comment);
            newsDao.update(news);
            return commentDTOConverter.toDto(comment);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long newsId, Long commendId) {
        logger.info("Deleting Comment by Id");
        News news = newsDao.findOne(newsId);
        Iterator<Comment> iterator = news.getComments().iterator();
        while (iterator.hasNext()) {
            Comment comment = iterator.next();
            if (comment.getId().equals(commendId)) {
                iterator.remove();
                break;
            }
        }
        newsDao.update(news);
    }
}
