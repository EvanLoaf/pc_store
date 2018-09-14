package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class FeedbackDTO implements Serializable {

    private Long id;
    private String message;
    private SimpleUserDTO user;

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

    public SimpleUserDTO getUser() {
        return user;
    }

    public void setUser(SimpleUserDTO user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private String message;
        private SimpleUserDTO user;

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

        public Builder withUser(SimpleUserDTO val) {
            user = val;
            return this;
        }

        public FeedbackDTO build() {
            return new FeedbackDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackDTO that = (FeedbackDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }
}
