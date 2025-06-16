package com.dberna2.mcpserver.springmcpserver.domain;

import java.util.UUID;

public  record OrderId(String value) {

  public static OrderId any() {
    return new OrderId(UUID.randomUUID().toString());
  }
}
