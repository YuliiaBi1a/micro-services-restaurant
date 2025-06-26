package com.yuliia.restaurant.shared.dto;

import lombok.Builder;

@Builder
public record ApiError(
        int code,
        String message
) {
}
