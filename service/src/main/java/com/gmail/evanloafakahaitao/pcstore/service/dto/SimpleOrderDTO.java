package com.gmail.evanloafakahaitao.pcstore.service.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class SimpleOrderDTO {

    private String uuid;
    private LocalDateTime created;
    private Integer quantity;
    private OrderStatusEnum status;
    private ItemDTO item;

    public SimpleOrderDTO() {
    }

    private SimpleOrderDTO(Builder builder) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
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
        private Integer quantity;
        private OrderStatusEnum status;
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

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withStatus(OrderStatusEnum val) {
            status = val;
            return this;
        }

        public Builder withItem(ItemDTO val) {
            item = val;
            return this;
        }

        public SimpleOrderDTO build() {
            return new SimpleOrderDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleOrderDTO that = (SimpleOrderDTO) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
