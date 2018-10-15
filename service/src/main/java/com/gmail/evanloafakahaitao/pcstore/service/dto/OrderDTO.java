package com.gmail.evanloafakahaitao.pcstore.service.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.OrderStatusEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = -5815895356279223025L;

    private String uuid;
    private String created;
    private Integer quantity;
    private BigDecimal totalPrice;
    private OrderStatusEnum status;
    private OrderUserDTO user;
    private ItemDTO item;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(uuid, orderDTO.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
