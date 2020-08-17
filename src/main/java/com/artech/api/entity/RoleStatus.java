package com.artech.api.entity;

import java.util.HashMap;
import java.util.Map;

public enum RoleStatus {
    ROLE_USER(2),ROLE_ADMIN(1);

    private static final Map<RoleStatus, Integer> BY_VALUE = new HashMap<>();

    static {
        for (RoleStatus status : values()) {
            BY_VALUE.put(status, status.value);
        }
    }

    private final Integer value;

    RoleStatus(Integer value) {
        this.value = value;
    }

    public static Integer valueOfStatus(RoleStatus status) {
        return BY_VALUE.get(status);
    }
}
