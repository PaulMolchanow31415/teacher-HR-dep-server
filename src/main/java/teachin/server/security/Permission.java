package teachin.server.security;

import lombok.Getter;

public enum Permission {
    READ("read"),
    WRITE("write");

    @Getter
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
