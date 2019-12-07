package me.schnaidt.business.persistence.configuration;

import com.zaxxer.hikari.HikariDataSource;
import me.schnaidt.business.configuration.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
//@ConditionalOnProperty(name = "app.audit.auditDatabase.enabled", havingValue = "true")
@EnableJpaRepositories(
    basePackages = "me.schnaidt.business.persistence.repository",
    entityManagerFactoryRef = "businessEntityManagerFactory",
    transactionManagerRef = "businessTransactionManager"
)
//@ComponentScan("de.degussabank.atacama.component.journal.database")
@EnableTransactionManagement
//@Import(JournalConfiguration.class)
public class BusinessDatabaseConfiguration {

  @Autowired
  private DatabaseConfiguration configuration;

  @Bean
  PlatformTransactionManager businessTransactionManager() {

    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(businessEntityManagerFactory().getObject());
    return transactionManager;
  }

  @Bean
  LocalContainerEntityManagerFactoryBean businessEntityManagerFactory() {

    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(businessDataSource());
    em.setPackagesToScan("me.schnaidt.business.persistence.domain");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", configuration.getHibernateHbm2ddlAuto());
    properties.put("hibernate.dialect", configuration.getHibernateDialect());
    em.setJpaPropertyMap(properties);

    return em;
  }

  @Bean
  public DataSource businessDataSource() {

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

    ds.setPoolName("MY-POOL");
    ds.setConnectionTestQuery("SELECT 1 FROM DUAL");
    ds.setMaximumPoolSize(5);
    ds.setMinimumIdle(1);
    ds.setIdleTimeout(36000); // 10 min
    ds.setMaxLifetime(360000); // 100 min
    ds.setAutoCommit(false);
    return ds;
  }

}
