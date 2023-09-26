package com.security.jwtsecurity.security;

public enum ApplicationUserPermission {
    USER_READ("user:reed"),
    USER_WRITE("user:write"),
    ROLE_READ("Role:read"),
    ROLE_WRITE("Role:write");

    private final String permission;
    ApplicationUserPermission(String permission) {

        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
