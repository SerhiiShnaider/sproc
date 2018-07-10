package com.gmail.shnapi007;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.gmail.shnapi007")
@SpringBootApplication(scanBasePackages = {"com.gmail.shnapi007"})

public class RunApp {

  @Autowired
  private Environment env;

  public static void main(String[] args) {
    SpringApplication.run(RunApp.class, args);
  }

  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    // See: application.properties
    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));

    System.out.println("## getDataSource: " + dataSource);

    return dataSource;
  }

}
