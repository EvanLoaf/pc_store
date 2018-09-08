package com.gmail.evanloafakahaitao.service.dto;

public class NewsAndCommentUserDTO {

    private String name;

    public NewsAndCommentUserDTO() {
    }

    private NewsAndCommentUserDTO(Builder builder) {
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private String name;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public NewsAndCommentUserDTO build() {
            return new NewsAndCommentUserDTO(this);
        }
    }
}
