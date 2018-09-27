package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class DiscountDTO {

    private Long id;
    private String name;
    private Integer percent;
    private LocalDateTime finishDate;

    public DiscountDTO() {
    }

    private DiscountDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setPercent(builder.percent);
        setFinishDate(builder.finishDate);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public static final class Builder {
        private Long id;
        private String name;
        private Integer percent;
        private LocalDateTime finishDate;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withPercent(Integer val) {
            percent = val;
            return this;
        }

        public Builder withFinishDate(LocalDateTime val) {
            finishDate = val;
            return this;
        }

        public DiscountDTO build() {
            return new DiscountDTO(this);
        }
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
