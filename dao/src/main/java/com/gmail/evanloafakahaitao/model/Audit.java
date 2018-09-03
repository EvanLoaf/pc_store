package com.gmail.evanloafakahaitao.model;

import java.time.LocalDateTime;

public class Audit {

    private Long id;
    private User user;
    private String eventType;
    private LocalDateTime created;

    private Audit(Builder builder) {
        setId(builder.id);
        setUser(builder.user);
        setEventType(builder.eventType);
        setCreated(builder.created);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public static final class Builder {
        private Long id;
        private User user;
        private String eventType;
        private LocalDateTime created;

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

        public Builder withEventType(String val) {
            eventType = val;
            return this;
        }

        public Builder withCreated(LocalDateTime val) {
            created = val;
            return this;
        }

        public Audit build() {
            return new Audit(this);
        }
    }
}
