package com.gmail.evanloafakahaitao.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuditDTO implements Serializable {

    private Long id;
    private String eventType;
    private LocalDateTime created;
    private AuditUserDTO user;

    public AuditDTO() {
    }

    private AuditDTO(Builder builder) {
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

    public AuditUserDTO getUser() {
        return user;
    }

    public void setUser(AuditUserDTO user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private String eventType;
        private LocalDateTime created;
        private AuditUserDTO user;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
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

        public Builder withUser(AuditUserDTO val) {
            user = val;
            return this;
        }

        public AuditDTO build() {
            return new AuditDTO(this);
        }
    }
}
