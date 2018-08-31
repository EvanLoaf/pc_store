package com.gmail.evanloafakahaitao.model;

public class Feedback {

    private Long id;
    private User user;
    private String message;

    private Feedback(Builder builder) {
        id = builder.id;
        user = builder.user;
        message = builder.message;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public static final class Builder {
        private Long id;
        private User user;
        private String message;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Builder withMessage(String val) {
            message = val;
            return this;
        }

        public Feedback build() {
            return new Feedback(this);
        }
    }
}
