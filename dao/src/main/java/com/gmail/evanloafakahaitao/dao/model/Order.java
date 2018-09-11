package com.gmail.evanloafakahaitao.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    private int quantity;
    @NotNull
    @Column
    private String status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
        private @NotNull int quantity;
        private @NotNull String status;
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

        public Builder withQuantity(@NotNull int val) {
            quantity = val;
            return this;
        }

        public Builder withStatus(@NotNull String val) {
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
}
