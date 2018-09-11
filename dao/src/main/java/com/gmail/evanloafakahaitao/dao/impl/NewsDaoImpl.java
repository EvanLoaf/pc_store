package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.NewsDao;
import com.gmail.evanloafakahaitao.dao.model.News;

public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    public NewsDaoImpl(Class<News> clazz) {
        super(clazz);
    }
}
