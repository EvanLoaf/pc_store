package com.gmail.evanloafakahaitao.pcstore.controller.util;

import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldTrimmerUtil {

    private static final Logger logger = LogManager.getLogger(FieldTrimmerUtil.class);

    public UserDTO trim(UserDTO user) {
        logger.info("Trimming user entity");

        if (user.getFirstName() != null) {
            user.setFirstName(user.getFirstName().trim());
        }
        if (user.getLastName() != null) {
            user.setLastName(user.getLastName().trim());
        }
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim());
        }
        if (user.getPassword() != null) {
            user.setPassword(user.getPassword().trim());
        }
        if (user.getAddress() != null) {
            user.setAddress(user.getAddress().trim());
        }
        if (user.getPhoneNumber() != null) {
            user.setPhoneNumber(user.getPhoneNumber().trim());
        }
        return user;
    }

    public NewsDTO trim(NewsDTO news) {
        logger.info("Trimming news entity");

        if (news.getTitle() != null) {
            news.setTitle(news.getTitle().trim());
        }
        if (news.getContent() != null) {
            news.setContent(news.getContent().trim());
        }
        return news;
    }

    public ItemDTO trim(ItemDTO item) {
        logger.info("Trimming item entity");

        if (item.getVendorCode() != null) {
            item.setVendorCode(item.getVendorCode().trim());
        }
        if (item.getName() != null) {
            item.setName(item.getName().trim());
        }
        if (item.getDescription() != null) {
            item.setDescription(item.getDescription().trim());
        }
        return item;
    }

    public List<ItemDTO> trim(List<ItemDTO> items) {
        List<ItemDTO> trimmedItems = new ArrayList<>();
        for (ItemDTO item : items) {
            trimmedItems.add(trim(item));
        }
        return trimmedItems;
    }

    public FeedbackDTO trim(FeedbackDTO feedback) {
        logger.info("Trimming feedback entity");

        if (feedback.getMessage() != null) {
            feedback.setMessage(feedback.getMessage().trim());
        }
        return feedback;
    }

    public CommentDTO trim(CommentDTO comment) {
        logger.info("Trimming comment entity");

        if (comment.getMessage() != null) {
            comment.setMessage(comment.getMessage().trim());
        }
        return comment;
    }

    public BusinessCardDTO trim(BusinessCardDTO businessCard) {
        logger.info("Trimming business card entity");

        if (businessCard.getTitle() != null) {
            businessCard.setTitle(businessCard.getTitle().trim());
        }
        if (businessCard.getFullName() != null) {
            businessCard.setFullName(businessCard.getFullName().trim());
        }
        if (businessCard.getWorkingTelephone() != null) {
            businessCard.setWorkingTelephone(businessCard.getWorkingTelephone().trim());
        }
        return businessCard;
    }
}
