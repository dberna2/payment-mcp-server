package com.dberna2.mcpserver.springmcpserver.domain;

import java.math.BigDecimal;
import java.util.Random;

public record Money(BigDecimal value, String currency) {

  private static final Random RANDOM = new Random();

  public static final String DEFAULT_CURRENCY = "EUR";

  public static Money any() {
    final BigDecimal amount = BigDecimal.valueOf(RANDOM.nextDouble() * 1000);
    return new Money(amount, DEFAULT_CURRENCY);
  }
}
