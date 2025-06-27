package com.dberna2.mcpserver.springmcpserver.infrastructure.in.mcp;

import com.dberna2.mcpserver.springmcpserver.application.PaymentStatusFinder;
import com.dberna2.mcpserver.springmcpserver.infrastructure.in.mcp.config.ToolSpecification;
import com.dberna2.mcpserver.springmcpserver.domain.Payment;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.spec.McpSchema;
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
public final class GetPaymentStatus implements ToolSpecification {

  private final PaymentStatusFinder         paymentStatusFinder;
  private final Map<String, McpSchema.Tool> toolDefinitions;

  public GetPaymentStatus(final PaymentStatusFinder paymentStatusFinder,
      final Map<String, McpSchema.Tool> toolDefinitions) {
    this.paymentStatusFinder = paymentStatusFinder;
    this.toolDefinitions = toolDefinitions;
  }

  @Override
  public SyncToolSpecification getToolSpecification() {
    final Tool tool = this.toolDefinitions.get("getPaymentStatus");
    return new SyncToolSpecification(tool, this.buildCallToolResult());
  }

  private BiFunction<McpSyncServerExchange, Map<String, Object>, CallToolResult> buildCallToolResult() {
    return (exchange, args) -> {

      final String paymentId = (String) args.get("paymentId");

      final Payment response = this.paymentStatusFinder.execute(paymentId);

      final List<Content> contents = new ArrayList<>();
      contents.add(new TextContent(response.toString()));

      return new CallToolResult(contents, false);
    };
  }
}
