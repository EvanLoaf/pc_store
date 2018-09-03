package com.gmail.evanloafakahaitao.model;

import java.time.LocalDateTime;

public class News {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private User user;

    private News(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime created;
        private User user;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
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

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public News build() {
            return new News(this);
        }
    }
}
