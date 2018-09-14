package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.CommentDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.*;

public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl(Class<Comment> clazz) {
        super(clazz);
    }
}
