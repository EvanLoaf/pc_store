package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NewsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ArticleDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ArticleDTO news = (ArticleDTO) obj;
        if (news.getId() == null) {
            ValidationUtils.rejectIfEmpty(err, "title", "news.title.empty");
            ValidationUtils.rejectIfEmpty(err, "content", "news.content.empty");

            if (news.getTitle().length() > 40) {
                err.rejectValue("title", "news.title.length");
            }
            if (news.getContent().length() > 500) {
                err.rejectValue("title", "news.content.length");
            }
        } else {
            if (news.getTitle() != null && !news.getTitle().equals("") && news.getTitle().length() > 40) {
                err.rejectValue("title", "news.title.length");
            }
            if (news.getContent() != null && !news.getContent().equals("") && news.getContent().length() > 500) {
                err.rejectValue("title", "news.content.length");
            }
        }
    }
}
