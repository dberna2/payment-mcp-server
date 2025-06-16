package com.dberna2.mcpserver.springmcpserver.infrastructure.in.mcp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MCPConfiguration {

 @Bean
 public McpSyncServer mcpServer(final List<ToolSpecification> toolDefinitions) {

    final SyncToolSpecification[] toolSpecifications = toolDefinitions.stream()
        .map(ToolSpecification::getToolSpecification)
        .toArray(SyncToolSpecification[]::new);
    var transportProvider = new StdioServerTransportProvider(new ObjectMapper());

    return McpServer.sync(transportProvider)
        .serverInfo("payment-mcp-server", "0.0.1-SNAPSHOT")
        .capabilities(
            McpSchema.ServerCapabilities.builder()
                .tools(true)
                .logging()
                .build()
        )
        .tools(toolSpecifications)
        .build();
  }

  @Bean
  @SuppressWarnings("unchecked")
  public Map<String, McpSchema.Tool> buildTools() throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    final PathMatchingResourcePatternResolver resolver     = new PathMatchingResourcePatternResolver();
    final Map<String, McpSchema.Tool> toolDefinitions = new HashMap<>();

    final Resource[] resources = resolver.getResources("classpath:tools/*.json");

    for (final Resource resource : resources) {
      try (final InputStream is = resource.getInputStream()) {
        final Map<String, Object> rawTool = mapper.readValue(is, Map.class);
        final McpSchema.Tool tool = new McpSchema.Tool(
            (String) rawTool.get("name"),
            (String) rawTool.get("description"),
            mapper.writeValueAsString(rawTool.get("schema"))
        );
        toolDefinitions.put(tool.name(), tool);
      }
    }
    return toolDefinitions;
  }
}
