package com.gmail.evanloafakahaitao.pcstore.dao.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 2845455713476116479L;

    @EmbeddedId
    private OrderId id = new OrderId();
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
    @Column
    private BigDecimal totalPrice;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
