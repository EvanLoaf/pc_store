package com.gmail.evanloafakahaitao.service.dto;

import java.time.LocalDateTime;

public class ShowToUserOrderDTO {

    private String uuid;
    private LocalDateTime created;
    private int quantity;
    private String status;
    private ItemDTO item;

    public ShowToUserOrderDTO() {
    }

    private ShowToUserOrderDTO(Builder builder) {
        setUuid(builder.uuid);
        setCreated(builder.created);
        setQuantity(builder.quantity);
        setStatus(builder.status);
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

        public Builder withItem(ItemDTO val) {
            item = val;
            return this;
        }

        public ShowToUserOrderDTO build() {
            return new ShowToUserOrderDTO(this);
        }
    }
}
