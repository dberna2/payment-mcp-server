package com.dberna2.mcpserver.springmcpserver.application;

import com.dberna2.mcpserver.springmcpserver.domain.Payment;
import com.dberna2.mcpserver.springmcpserver.domain.PaymentNotFound;
import com.dberna2.mcpserver.springmcpserver.domain.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusFinder {

  private final PaymentRepository paymentRepository;

  public PaymentStatusFinder(final PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  public Payment execute(final String paymentId, final String paymentStatus) {
    return this.paymentRepository.findBy(paymentId)
        .orElseThrow(() -> new PaymentNotFound(paymentId));
  }
}
