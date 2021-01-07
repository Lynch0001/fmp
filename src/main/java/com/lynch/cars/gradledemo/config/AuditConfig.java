package com.lynch.cars.gradledemo.config;

import com.lynch.cars.gradledemo.audit.AuditAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class AuditConfig {

  @Bean
  public AuditorAware<String> auditorAware(){
    return new AuditAwareImpl();
  }
}
