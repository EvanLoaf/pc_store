package com.gmail.evanloafakahaitao.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDTO implements Serializable {

    private Long id;
    private String content;
    private LocalDateTime created;
    private CommentFeedbackNewsLoginUserDTO user;

    public CommentDTO() {
    }

    private CommentDTO(Builder builder) {
        id = builder.id;
        setContent(builder.content);
        setCreated(builder.created);
        setUser(builder.user);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public CommentFeedbackNewsLoginUserDTO getUser() {
        return user;
    }

    public void setUser(CommentFeedbackNewsLoginUserDTO user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private String content;
        private LocalDateTime created;
        private CommentFeedbackNewsLoginUserDTO user;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withContent(String val) {
            content = val;
            return this;
        }

        public Builder withCreated(LocalDateTime val) {
            created = val;
            return this;
        }

        public Builder withUser(CommentFeedbackNewsLoginUserDTO val) {
            user = val;
            return this;
        }

        public CommentDTO build() {
            return new CommentDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(content, that.content) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, created);
    }
}
