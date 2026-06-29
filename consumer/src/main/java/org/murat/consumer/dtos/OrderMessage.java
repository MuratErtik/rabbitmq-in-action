package org.murat.consumer.dtos;

public record OrderMessage(Long orderId, String status) {
}
