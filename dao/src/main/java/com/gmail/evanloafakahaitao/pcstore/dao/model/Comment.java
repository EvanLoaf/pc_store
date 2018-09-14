package com.gmail.evanloafakahaitao.pcstore.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column
    private String content;
    @NotNull
    @Column
    private LocalDateTime created;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Comment() {
    }

    private Comment(Builder builder) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private @NotNull String content;
        private @NotNull LocalDateTime created;
        private User user;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
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

        public Comment build() {
            return new Comment(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}
