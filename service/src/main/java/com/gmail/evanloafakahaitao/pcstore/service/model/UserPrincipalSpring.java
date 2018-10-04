package com.gmail.evanloafakahaitao.pcstore.service.model;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Permission;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipalSpring implements UserDetails {

    private Long id;
    private String name;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserPrincipalSpring(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.username = user.getEmail();
        this.password = user.getPassword();
        String[] authorityList = user.getRole()
                .getPermissionSet()
                .stream()
                .map(Permission::getName)
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

    //TODO enabled functionality
    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
