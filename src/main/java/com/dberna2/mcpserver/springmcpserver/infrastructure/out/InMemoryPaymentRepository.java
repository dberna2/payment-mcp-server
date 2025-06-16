package com.dberna2.mcpserver.springmcpserver.infrastructure.out;

import static java.util.stream.Collectors.toMap;
import com.dberna2.mcpserver.springmcpserver.domain.CustomerId;
import com.dberna2.mcpserver.springmcpserver.domain.Payment;
import com.dberna2.mcpserver.springmcpserver.domain.PaymentRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Repository;

@Repository
public final class InMemoryPaymentRepository implements PaymentRepository {

  private final Map<String, Payment> payments;

  public InMemoryPaymentRepository() {
    this.payments = initData();
  }

  private Map<String, Payment> initData() {
    return IntStream.range(0, 15)
        .mapToObj(index -> Payment.any())
        .collect(Collectors.toMap(Payment::id, Function.identity()));
  }

  @Override
  public Optional<Payment> findBy(final String paymentId) {
   return Optional.ofNullable(this.payments.get(paymentId));
  }

  @Override
  public List<Payment> findAllBy(final CustomerId value) {
    return this.payments.values().stream()
        .filter(payment -> payment.customerId().value().equals(value.value()))
        .toList();
  }
}
