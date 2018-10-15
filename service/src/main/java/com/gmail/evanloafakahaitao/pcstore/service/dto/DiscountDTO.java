package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class DiscountDTO implements Serializable {

    private static final long serialVersionUID = -2987498507930664736L;

    private Long id;
    private String name;
    private Integer percent;
    private LocalDateTime finishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountDTO that = (DiscountDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(percent, that.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, percent);
    }
}
