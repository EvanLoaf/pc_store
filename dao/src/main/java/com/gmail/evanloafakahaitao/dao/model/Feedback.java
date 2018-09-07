package com.gmail.evanloafakahaitao.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column
    private String message;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "F_USER_ID")
    private User user;

    public Feedback() {
    }

    private Feedback(Builder builder) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private @NotNull String message;
        private User user;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withMessage(@NotNull String val) {
            message = val;
            return this;
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Feedback build() {
            return new Feedback(this);
        }
    }
}
