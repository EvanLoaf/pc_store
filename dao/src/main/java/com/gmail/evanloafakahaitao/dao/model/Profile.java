package com.gmail.evanloafakahaitao.dao.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class Profile implements Serializable {

    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user")
    )
    @Id
    @GeneratedValue(generator = "generator")
    @Column(unique = true, nullable = false)
    private Long userId;
    @NotNull
    @Column
    private String address;
    @NotNull
    @Column
    private String phoneNumber;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    public Profile() {
    }

    private Profile(Builder builder) {
        setUserId(builder.userId);
        setAddress(builder.address);
        setPhoneNumber(builder.phoneNumber);
        setUser(builder.user);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final class Builder {
        private Long userId;
        private @NotNull String address;
        private @NotNull String phoneNumber;
        private User user;

        private Builder() {
        }

        public Builder withUserId(Long val) {
            userId = val;
            return this;
        }

        public Builder withAddress(@NotNull String val) {
            address = val;
            return this;
        }

        public Builder withPhoneNumber(@NotNull String val) {
            phoneNumber = val;
            return this;
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(userId, profile.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
