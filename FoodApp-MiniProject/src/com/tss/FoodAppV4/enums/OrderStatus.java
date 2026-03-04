package com.tss.FoodAppV4.enums;

public enum OrderStatus {
    PLACED,
    CONFIRMED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED;

    public OrderStatus next() {
        return switch (this) {
            case PLACED           -> CONFIRMED;
            case CONFIRMED        -> PREPARING;
            case PREPARING        -> OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY -> DELIVERED;
            default               -> this;
        };
    }

    public boolean isFinal() {
        return this == DELIVERED || this == CANCELLED;
    }
}