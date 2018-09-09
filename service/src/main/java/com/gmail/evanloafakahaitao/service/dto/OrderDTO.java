package com.gmail.evanloafakahaitao.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderDTO implements Serializable {

    private String uuid;
    private LocalDateTime created;
    private int quantity;
    private String status;
    private OrderUserDTO user;
    private ItemDTO item;

    public OrderDTO() {
    }

    private OrderDTO(Builder builder) {
        setUuid(builder.uuid);
        setCreated(builder.created);
        setQuantity(builder.quantity);
        setStatus(builder.status);
        setUser(builder.user);
        setItem(builder.item);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderUserDTO getUser() {
        return user;
    }

    public void setUser(OrderUserDTO user) {
        this.user = user;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public static final class Builder {
        private String uuid;
        private LocalDateTime created;
        private int quantity;
        private String status;
        private OrderUserDTO user;
        private ItemDTO item;

        private Builder() {
        }

        public Builder withUuid(String val) {
            uuid = val;
            return this;
        }

        public Builder withCreated(LocalDateTime val) {
            created = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public Builder withStatus(String val) {
            status = val;
            return this;
        }

        public Builder withUser(OrderUserDTO val) {
            user = val;
            return this;
        }

        public Builder withItem(ItemDTO val) {
            item = val;
            return this;
        }

        public OrderDTO build() {
            return new OrderDTO(this);
        }
    }
}
