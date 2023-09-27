package com.security.jwtsecurity.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.security.jwtsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER_ROLE(Sets.newHashSet()),
    ADMIN_ROLE(Sets.newHashSet(ROLE_WRITE, ROLE_READ, USER_READ, USER_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(ROLE_READ, USER_READ));
    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
