package com.take.store.model.user;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.take.store.model.user.Permission.*;

public enum Role {
    @JsonEnumDefaultValue
    CUSTOMER(Sets.newHashSet(READ)),
    MODERATOR(Sets.newHashSet(READ, UPDATE)),
    ADMIN(Sets.newHashSet(READ, UPDATE, CREATE, DELETE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> authorities = getPermissionsAsGrantedAuthorities();
        GrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + this.name());
        authorities.add(role);
        return authorities;
    }

    private Set<GrantedAuthority> getPermissionsAsGrantedAuthorities() {
        return permissions.stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
    }
}
