package com.dberna2.mcpserver.springmcpserver.domain;

public final class PaymentNotFound extends RuntimeException {

  public PaymentNotFound(final String paymentId) {
    super("Payment with id: " + paymentId + " not found.");
  }

}
