package me.schnaidt.tracing.database.configuration;

import lombok.Data;

@Data
public class DatabaseTracingConfiguration {

  private String username;
  private String password;
  private String driver;
  private String url;
  private String hibernateDialect;
  private String hibernateHbm2ddlAuto;

}
