package com.gmail.evanloafakahaitao.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private List<Audit> auditList;
    private List<Comment> commentList;
    private List<News> newsList;
    private List<Order> ordersFromUser;
    private Profile profile;
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String addInfo;
    private RoleEnum role;

    private User(Builder builder) {
        auditList = builder.auditList;
        commentList = builder.commentList;
        newsList = builder.newsList;
        ordersFromUser = builder.ordersFromUser;
        profile = builder.profile;
        id = builder.id;
        email = builder.email;
        password = builder.password;
        firstName = builder.firstName;
        lastName = builder.lastName;
        phoneNumber = builder.phoneNumber;
        addInfo = builder.addInfo;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<Audit> getAuditList() {
        return auditList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public List<Order> getOrdersFromUser() {
        return ordersFromUser;
    }

    public Profile getProfile() {
        return profile;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public RoleEnum getRole() {
        return role;
    }

    public static final class Builder {
        private List<Audit> auditList;
        private List<Comment> commentList;
        private List<News> newsList;
        private List<Order> ordersFromUser;
        private Profile profile;
        private Long id;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String addInfo;
        private RoleEnum role;

        private Builder() {
        }

        public Builder withAuditList(List<Audit> val) {
            auditList = val;
            return this;
        }

        public Builder withCommentList(List<Comment> val) {
            commentList = val;
            return this;
        }

        public Builder withNewsList(List<News> val) {
            newsList = val;
            return this;
        }

        public Builder withOrdersFromUser(List<Order> val) {
            ordersFromUser = val;
            return this;
        }

        public Builder withProfile(Profile val) {
            profile = val;
            return this;
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withPhoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Builder withAddInfo(String val) {
            addInfo = val;
            return this;
        }

        public Builder withRole(RoleEnum val) {
            role = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
