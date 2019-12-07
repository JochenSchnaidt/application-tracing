package me.schnaidt.business.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "database")
public class DatabaseConfiguration {

  private String username;
  private String password;
  private String driver;
  private String url;
  private String hibernateDialect;
  private String hibernateHbm2ddlAuto;

}
