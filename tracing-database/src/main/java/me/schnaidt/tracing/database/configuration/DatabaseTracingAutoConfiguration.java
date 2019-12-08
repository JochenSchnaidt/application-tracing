package me.schnaidt.tracing.database.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;


//@ConditionalOnClass(ActivateDatabaseTracing.class)
@ConditionalOnProperty(value = "tracing.database.enabled", havingValue = "true")
@Configuration
@EnableJpaRepositories(
    basePackages = "me.schnaidt.tracing.database.repository",
    entityManagerFactoryRef = "tracingEntityManagerFactory",
    transactionManagerRef = "tracingTransactionManager"
)
@EnableTransactionManagement
@ComponentScan(basePackages = "me.schnaidt.tracing.database")
public class DatabaseTracingAutoConfiguration {

  @Autowired
  private DatabaseTracingConfiguration configuration;

  @Bean
  PlatformTransactionManager tracingTransactionManager() {

    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(tracingEntityManagerFactory().getObject());
    return transactionManager;
  }

  @Bean
  LocalContainerEntityManagerFactoryBean tracingEntityManagerFactory() {

    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(tracingDataSource());
    em.setPackagesToScan("me.schnaidt.tracing.database.domain");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", configuration.getHibernateHbm2ddlAuto());
    properties.put("hibernate.dialect", configuration.getHibernateDialect());
    em.setJpaPropertyMap(properties);

    return em;
  }

  @Bean
  public DataSource tracingDataSource() {

    // @formatter:off
    HikariDataSource ds =
        DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .driverClassName(configuration.getDriver())
            .url(configuration.getUrl())
            .username(configuration.getUsername())
            .password(configuration.getPassword())
            .build();
    // @formatter:on

    ds.setPoolName("TRACE-POOL");
    ds.setConnectionTestQuery("SELECT 1 FROM DUAL");
    ds.setMaximumPoolSize(5);
    ds.setMinimumIdle(1);
    ds.setIdleTimeout(36000); // 10 min
    ds.setMaxLifetime(360000); // 100 min
    ds.setAutoCommit(false);
    return ds;
  }

}
