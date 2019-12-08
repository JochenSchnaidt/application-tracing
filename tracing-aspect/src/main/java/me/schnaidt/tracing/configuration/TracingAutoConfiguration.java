package me.schnaidt.tracing.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ConditionalOnProperty(value = "tracing.enabled", havingValue = "true")
@Configuration
@ComponentScan(basePackages = "me.schnaidt.tracing")
@EnableAspectJAutoProxy
public class TracingAutoConfiguration {
}
