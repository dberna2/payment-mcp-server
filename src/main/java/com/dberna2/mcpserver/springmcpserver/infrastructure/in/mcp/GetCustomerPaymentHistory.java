package com.dberna2.mcpserver.springmcpserver.infrastructure.in.mcp;

import com.dberna2.mcpserver.springmcpserver.application.CustomerPaymentHistoryFinder;
import com.dberna2.mcpserver.springmcpserver.domain.Payment;
import com.dberna2.mcpserver.springmcpserver.infrastructure.in.mcp.config.ToolSpecification;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.Content;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import io.modelcontextprotocol.spec.McpSchema.Tool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.springframework.stereotype.Component;

@Component
public final class GetCustomerPaymentHistory implements ToolSpecification {

  private final CustomerPaymentHistoryFinder         customerPaymentHistoryFinder;
  private final Map<String, Tool> toolDefinitions;

  public GetCustomerPaymentHistory(final CustomerPaymentHistoryFinder customerPaymentHistoryFinder,
      final Map<String, Tool> toolDefinitions) {
    this.customerPaymentHistoryFinder = customerPaymentHistoryFinder;
    this.toolDefinitions = toolDefinitions;
  }

  @Override
  public SyncToolSpecification getToolSpecification() {
    final Tool tool = this.toolDefinitions.get("getCustomerPaymentHistory");
    return new SyncToolSpecification(tool, this.buildCallToolResult());
  }

  private BiFunction<McpSyncServerExchange, Map<String, Object>, CallToolResult> buildCallToolResult() {
    return (exchange, args) -> {

      final String customerId = (String) args.get("customerId");
      final String paymentStatus = (String) args.get("paymentStatus");

      final List<Payment> payments = this.customerPaymentHistoryFinder.execute(customerId, paymentStatus);

      final List<Content> contents = new ArrayList<>();
      for (final Payment payment : payments) {
        contents.add(new TextContent(payment.toString()));
      }

      return new CallToolResult(contents, false);
    };
  }
}
