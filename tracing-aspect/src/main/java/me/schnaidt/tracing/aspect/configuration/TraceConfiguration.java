package me.schnaidt.tracing.aspect.configuration;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.schnaidt.tracing.database.configuration.ActivateDatabaseTracing;
import me.schnaidt.tracing.database.configuration.DatabaseTracingConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "tracing")
public class TraceConfiguration {

  @NestedConfigurationProperty
  private DatabaseTracingConfiguration database;

  @Bean
  public DatabaseTracingConfiguration createDatabaseTracingConfiguration() {
    return this.getDatabase();
  }

  /* @Bean
  @ConditionalOnProperty(value = "tracing.database.enabled", havingValue = "true")
  public ActivateDatabaseTracing createActivateDatabaseTracing() {
    log.info("marker generated");
    return new ActivateDatabaseTracing();
  } */

}
