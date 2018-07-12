package com.gmail.shnapi007;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
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
  @Profile({"mysql", "default"})
  public DataSource getMysqlDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    // See: application.properties
    dataSource.setDriverClassName(env.getProperty("spring.datasource.mysql.driver-class-name"));
    dataSource.setUrl(env.getProperty("spring.datasource.mysql.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.mysql.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.mysql.password"));

    System.out.println("## getDataSource: " + dataSource);
    return dataSource;
  }

  /*
  @Bean
  @ConditionalOnExpression("${h2.web.enabled:true}")
  public Server h2WebServer() throws SQLException {
    return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "1488").start();
  }*/


  // use --spring.profiles.active=h2
  @Bean(name = "dataSource")
  @Profile("h2")
  public DataSource getH2DataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    // See: application.properties
    dataSource.setDriverClassName(env.getProperty("spring.datasource.h2.driver-class-name"));
    dataSource.setUrl(env.getProperty("spring.datasource.h2.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.h2.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.h2.password"));

    System.out.println("## getDataSource: " + dataSource);
    return dataSource;
  }
}
