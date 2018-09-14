package com.gmail.evanloafakahaitao.pcstore.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class Order implements Serializable {

    @EmbeddedId
    private OrderId id = OrderId.newBuilder().build();
    @NotNull
    @Column
    private String uuid;
    @NotNull
    @Column
    private LocalDateTime created;
    @NotNull
    @Column
    private Integer quantity;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatusEnum status;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    private Item item;

    public Order() {
    }

    private Order(Builder builder) {
        setId(builder.id);
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

    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public static final class Builder {
        private OrderId id = OrderId.newBuilder().build();
        private @NotNull String uuid;
        private @NotNull LocalDateTime created;
        private @NotNull Integer quantity;
        private @NotNull OrderStatusEnum status;
        private User user;
        private Item item;

        private Builder() {
        }

        public Builder withId(OrderId val) {
            id = val;
            return this;
        }

        public Builder withUuid(@NotNull String val) {
            uuid = val;
            return this;
        }

        public Builder withCreated(@NotNull LocalDateTime val) {
            created = val;
            return this;
        }

        public Builder withQuantity(@NotNull Integer val) {
            quantity = val;
            return this;
        }

        public Builder withStatus(@NotNull OrderStatusEnum val) {
            status = val;
            return this;
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Builder withItem(Item val) {
            item = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(uuid, order.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
