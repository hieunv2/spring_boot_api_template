package com.artech.api.entity;

public enum OrderStatus {
    ORDER_PROCESSING(0),
    ORDER_CONFIRM(1),
    ORDER_SUCCESS(2),
    ORDER_CANCLED(3);

    private final int value;

    OrderStatus(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
