package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NewsDTO implements Serializable {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private SimpleUserDTO user;
    private Set<CommentDTO> commentSet = new HashSet<>();

    public NewsDTO() {
    }

    private NewsDTO(Builder builder) {
        id = builder.id;
        setTitle(builder.title);
        setContent(builder.content);
        setCreated(builder.created);
        setUser(builder.user);
        setCommentSet(builder.commentSet);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
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

    public SimpleUserDTO getUser() {
        return user;
    }

    public void setUser(SimpleUserDTO user) {
        this.user = user;
    }

    public Set<CommentDTO> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<CommentDTO> commentSet) {
        this.commentSet = commentSet;
    }

    public static final class Builder {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime created;
        private SimpleUserDTO user;
        private Set<CommentDTO> commentSet = new HashSet<>();

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

        public Builder withUser(SimpleUserDTO val) {
            user = val;
            return this;
        }

        public Builder withCommentSet(Set<CommentDTO> val) {
            commentSet = val;
            return this;
        }

        public NewsDTO build() {
            return new NewsDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(title, newsDTO.title) &&
                Objects.equals(content, newsDTO.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }
}
