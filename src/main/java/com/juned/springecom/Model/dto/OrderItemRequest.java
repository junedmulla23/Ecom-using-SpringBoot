package com.juned.springecom.Model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
) {}
