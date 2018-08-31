package com.gmail.evanloafakahaitao.model;

public class Order {

    private Item item;
    private User user;
    private Long id;
    private String orderUuid;
    private String createdDate;
    private int quantity;

    private Order(Builder builder) {
        item = builder.item;
        user = builder.user;
        id = builder.id;
        orderUuid = builder.orderUuid;
        createdDate = builder.createdDate;
        quantity = builder.quantity;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public static final class Builder {
        private Item item;
        private User user;
        private Long id;
        private String orderUuid;
        private String createdDate;
        private int quantity;

        private Builder() {
        }

        public Builder withItem(Item val) {
            item = val;
            return this;
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withOrderUuid(String val) {
            orderUuid = val;
            return this;
        }

        public Builder withCreatedDate(String val) {
            createdDate = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
