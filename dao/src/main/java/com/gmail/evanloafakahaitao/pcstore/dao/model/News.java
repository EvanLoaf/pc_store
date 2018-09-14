package com.gmail.evanloafakahaitao.pcstore.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table
@Entity
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column
    private String title;
    @NotNull
    @Column
    private String content;
    @NotNull
    @Column
    private LocalDateTime created;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "newsId", nullable = false)
    private Set<Comment> commentSet = new HashSet<>();

    public News() {
    }

    private News(Builder builder) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public static final class Builder {
        private Long id;
        private @NotNull String title;
        private @NotNull String content;
        private @NotNull LocalDateTime created;
        private User user;
        private Set<Comment> commentSet = new HashSet<>();

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withTitle(@NotNull String val) {
            title = val;
            return this;
        }

        public Builder withContent(@NotNull String val) {
            content = val;
            return this;
        }

        public Builder withCreated(@NotNull LocalDateTime val) {
            created = val;
            return this;
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Builder withCommentSet(Set<Comment> val) {
            commentSet = val;
            return this;
        }

        public News build() {
            return new News(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(content, news.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }
}
