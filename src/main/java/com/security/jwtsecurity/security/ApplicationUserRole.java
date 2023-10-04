package com.security.jwtsecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.security.jwtsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER_ROLE(Sets.newHashSet()),
    ADMIN_ROLE(Sets.newHashSet(ROLE_WRITE, ROLE_READ, USER_READ, USER_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(ROLE_READ, USER_READ));
    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
