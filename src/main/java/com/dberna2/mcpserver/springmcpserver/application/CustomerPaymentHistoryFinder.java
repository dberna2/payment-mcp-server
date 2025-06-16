package com.dberna2.mcpserver.springmcpserver.application;

import com.dberna2.mcpserver.springmcpserver.domain.CustomerId;
import com.dberna2.mcpserver.springmcpserver.domain.Payment;
import com.dberna2.mcpserver.springmcpserver.domain.PaymentNotFound;
import com.dberna2.mcpserver.springmcpserver.domain.PaymentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerPaymentHistoryFinder {

  private final PaymentRepository paymentRepository;

  public CustomerPaymentHistoryFinder(final PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  public List<Payment> execute(final String customerId) {
    return this.paymentRepository.findAllBy(CustomerId.from(customerId));
  }
}
