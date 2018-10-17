package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("newsValidator")
public class NewsValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(NewsValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return NewsDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        NewsDTO news = (NewsDTO) obj;
        if (news.getId() == null) {
            logger.info("Validating news - create");

            ValidationUtils.rejectIfEmpty(err, "title", "news.title.empty");
            ValidationUtils.rejectIfEmpty(err, "content", "news.content.empty");

            if (news.getTitle().length() > 40) {
                err.rejectValue("title", "news.title.length");
            }
            if (news.getContent().length() > 500) {
                err.rejectValue("title", "news.content.length");
            }
        } else {
            logger.info("Validating news - update");

            if (news.getTitle() != null && !news.getTitle().equals("") && news.getTitle().length() > 40) {
                err.rejectValue("title", "news.title.length");
            }
            if (news.getContent() != null && !news.getContent().equals("") && news.getContent().length() > 500) {
                err.rejectValue("title", "news.content.length");
            }
        }
    }
}
