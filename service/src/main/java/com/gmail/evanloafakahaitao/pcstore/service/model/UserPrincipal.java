package com.gmail.evanloafakahaitao.pcstore.service.model;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Permission;
import com.gmail.evanloafakahaitao.pcstore.dao.model.PermissionEnum;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 3868923801985737089L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private boolean isDeleted;
    private boolean isDisabled;
    private List<GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.isDisabled = user.isDisabled();
        this.isDeleted = user.isDeleted();
        String[] authorityList = user.getRole()
                .getPermissions()
                .stream()
                .map(Permission::getName)
                .map(PermissionEnum::toString)
                .toArray(String[]::new);
        this.authorities = AuthorityUtils.createAuthorityList(authorityList);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public boolean isEnabled() {
        return !isDisabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
