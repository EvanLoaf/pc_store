package com.gmail.evanloafakahaitao.pcstore.dao.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderId implements Serializable {

    @NotNull
    @Column
    private Long userId;
    @NotNull
    @Column
    private Long itemId;

    public OrderId() {
    }

    private OrderId(Builder builder) {
        setfUserId(builder.userId);
        setfItemId(builder.itemId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getfUserId() {
        return userId;
    }

    public void setfUserId(Long fUserId) {
        this.userId = fUserId;
    }

    public Long getfItemId() {
        return itemId;
    }

    public void setfItemId(Long fItemId) {
        this.itemId = fItemId;
    }

    public static final class Builder {
        private Long userId;
        private Long itemId;

        private Builder() {
        }

        public Builder withUserId(Long val) {
            userId = val;
            return this;
        }

        public Builder withItemId(Long val) {
            itemId = val;
            return this;
        }

        public OrderId build() {
            return new OrderId(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return Objects.equals(userId, orderId.userId) &&
                Objects.equals(itemId, orderId.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, itemId);
    }
}
