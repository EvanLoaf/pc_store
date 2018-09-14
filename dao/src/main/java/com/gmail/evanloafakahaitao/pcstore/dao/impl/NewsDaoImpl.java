package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.NewsDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.News;

public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    public NewsDaoImpl(Class<News> clazz) {
        super(clazz);
    }
}
