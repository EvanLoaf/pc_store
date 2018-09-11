package com.gmail.evanloafakahaitao.service.dto;

import java.io.Serializable;

public class FeedbackDTO implements Serializable {

    private Long id;
    private String message;
    private CommentFeedbackNewsLoginUserDTO user;

    public FeedbackDTO() {
    }

    private FeedbackDTO(Builder builder) {
        id = builder.id;
        setMessage(builder.message);
        setUser(builder.user);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommentFeedbackNewsLoginUserDTO getUser() {
        return user;
    }

    public void setUser(CommentFeedbackNewsLoginUserDTO user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private String message;
        private CommentFeedbackNewsLoginUserDTO user;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withMessage(String val) {
            message = val;
            return this;
        }

        public Builder withUser(CommentFeedbackNewsLoginUserDTO val) {
            user = val;
            return this;
        }

        public FeedbackDTO build() {
            return new FeedbackDTO(this);
        }
    }
}
