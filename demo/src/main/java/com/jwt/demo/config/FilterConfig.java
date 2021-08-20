package com.jwt.demo.config;

import com.jwt.demo.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for registering Filter classes.
 */
@Configuration
public class FilterConfig {

  private final AuthFilter authFilter;

  public FilterConfig(AuthFilter authFilter) {
    this.authFilter = authFilter;
  }

  @Bean
  public FilterRegistrationBean<AuthFilter> createRootFilter() {
    final FilterRegistrationBean<AuthFilter> fgb = new FilterRegistrationBean<>();
    fgb.setFilter(this.authFilter);
    fgb.addUrlPatterns("/users/*");
    fgb.setName("AuthFilter");
    fgb.setOrder(1);
    return fgb;
  }

}
