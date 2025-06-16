package com.dberna2.mcpserver.springmcpserver.domain;

import java.util.UUID;

public record Payment(
    String id,
    PaymentStatus status,
    Money amount,
    PaymentMethod method,
    CustomerId customerId,
    OrderId orderId,
    PaymentProcessDate processedAt
) {

  public static Payment any() {
    return new Payment(
        UUID.randomUUID().toString(),
        PaymentStatus.any(),
        Money.any(),
        PaymentMethod.any(),
        CustomerId.any(),
        OrderId.any(),
        PaymentProcessDate.any()
    );
  }
}
