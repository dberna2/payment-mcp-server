package com.dberna2.mcpserver.springmcpserver.domain;

public enum PaymentMethod {
  VISA,
  MASTERCARD;

  public static PaymentMethod any() {
    PaymentMethod[] values = PaymentMethod.values();
    int randomIndex = (int) (Math.random() * values.length);
    return values[randomIndex];
  }
}
