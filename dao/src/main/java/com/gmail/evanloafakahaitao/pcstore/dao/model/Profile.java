package com.gmail.evanloafakahaitao.pcstore.dao.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class Profile implements Serializable {

    private static final long serialVersionUID = -5261206159106684954L;

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
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

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
