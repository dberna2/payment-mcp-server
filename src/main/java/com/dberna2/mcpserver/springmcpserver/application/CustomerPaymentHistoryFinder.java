package com.dberna2.mcpserver.springmcpserver.application;

import com.dberna2.mcpserver.springmcpserver.domain.CustomerId;
import com.dberna2.mcpserver.springmcpserver.domain.Payment;
import com.dberna2.mcpserver.springmcpserver.domain.PaymentRepository;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;

@Service
public class CustomerPaymentHistoryFinder {

  private final PaymentRepository paymentRepository;

  public CustomerPaymentHistoryFinder(final PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  public List<Payment> execute(final String customerId, final String paymentStatus) {
    return this.paymentRepository
        .findAllBy(CustomerId.from(customerId))
        .stream()
        .filter(filterByStatus(paymentStatus))
        .toList();
  }

  private Predicate<? super Payment> filterByStatus(final String paymentStatus) {
    return payment -> {
      if (paymentStatus == null)
        return true;
      return payment.status().name().equals(paymentStatus);
    };
  }
}
