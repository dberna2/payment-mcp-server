package com.dberna2.mcpserver.springmcpserver.domain;

public enum PaymentStatus {
  PENDING,
  AUTHORIZED,
  CANCELLED;

  public static PaymentStatus any() {
    PaymentStatus[] values = PaymentStatus.values();
    int randomIndex = (int) (Math.random() * values.length);
    return values[randomIndex];
  }
}
