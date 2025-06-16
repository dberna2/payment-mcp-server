package com.dberna2.mcpserver.springmcpserver.domain;

import java.util.List;

public record CustomerId(String value) {

  private static final List<String> VALUES =  List.of("1","2","3");

  public static CustomerId from(final String value) {
    return new CustomerId(value);
  }

  public static CustomerId any() {
    int randomIndex = (int) (Math.random() * VALUES.size());
    return new CustomerId(VALUES.get(randomIndex));
  }
}
