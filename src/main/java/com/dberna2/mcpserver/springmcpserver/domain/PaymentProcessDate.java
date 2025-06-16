package com.dberna2.mcpserver.springmcpserver.domain;

import static java.time.ZoneOffset.UTC;
import java.time.LocalDateTime;
import java.util.Random;

public record PaymentProcessDate(LocalDateTime value) {

  public static PaymentProcessDate any() {
    LocalDateTime now = LocalDateTime.now();

    long startEpochSecond = now.minusDays(3).toEpochSecond(UTC);
    long endEpochSecond = now.toEpochSecond(UTC);

    long randomEpochSecond = new Random().nextLong(startEpochSecond, endEpochSecond);

    final LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(randomEpochSecond, 0, UTC);

    return new PaymentProcessDate(localDateTime);
  }
}
