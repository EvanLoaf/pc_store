package com.gmail.evanloafakahaitao.pcstore.service.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class SimpleOrderDTO implements Serializable {

    private static final long serialVersionUID = -1882979228195015744L;

    private String uuid;
    private LocalDateTime created;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatusEnum status;
    private Long countOfOrdersFromUser;
    private SimpleItemDTO item;

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

    public SimpleItemDTO getItem() {
        return item;
    }

    public void setItem(SimpleItemDTO item) {
        this.item = item;
    }

    public Long getCountOfOrdersFromUser() {
        return countOfOrdersFromUser;
    }

    public void setCountOfOrdersFromUser(Long countOfOrdersFromUser) {
        this.countOfOrdersFromUser = countOfOrdersFromUser;
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
