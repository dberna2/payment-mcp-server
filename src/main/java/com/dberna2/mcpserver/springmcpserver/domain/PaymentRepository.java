package com.dberna2.mcpserver.springmcpserver.domain;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

  Optional<Payment> findBy(String paymentId);

  List<Payment> findAllBy(CustomerId value);
}
