package com.gmail.evanloafakahaitao.pcstore.controller.util;

import com.gmail.evanloafakahaitao.pcstore.controller.NewsController;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;
import com.gmail.evanloafakahaitao.pcstore.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

    private static final Logger logger = LogManager.getLogger(PaginationUtil.class);

    private final PageProperties pageProperties;

    @Autowired
    public PaginationUtil(
            PageProperties pageProperties
    ) {
        this.pageProperties = pageProperties;
    }

    public int getStartPosition(int page) {
        return (page - 1) * pageProperties.getPaginationMaxResults();
    }

    public int[] getPageNumbers(int itemCount) {
        if (itemCount == 0) {
            return new int[] {1};
        } else {
            int pagesCount = (itemCount + pageProperties.getPaginationMaxResults() - 1) / pageProperties.getPaginationMaxResults();
            int[] pageNumbers = new int[pagesCount];
            for (int i = 1; i <= pagesCount; i++) {
                pageNumbers[i - 1] = i;
            }
            return pageNumbers;
        }
    }
}
