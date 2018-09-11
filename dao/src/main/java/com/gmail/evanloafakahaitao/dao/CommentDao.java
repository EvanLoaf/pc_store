package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {

    List findByNewsId(long newsId);
}
