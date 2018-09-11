package com.gmail.evanloafakahaitao.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column
    private String eventType;
    @NotNull
    @Column
    private LocalDateTime created;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    public Audit() {
    }

    private Audit(Builder builder) {
        id = builder.id;
        setEventType(builder.eventType);
        setCreated(builder.created);
        setUser(builder.user);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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
        private @NotNull String eventType;
        private @NotNull LocalDateTime created;
        private User user;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withEventType(@NotNull String val) {
            eventType = val;
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

        public Audit build() {
            return new Audit(this);
        }
    }
}
