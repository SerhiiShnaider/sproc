package com.gmail.shnapi007;

import com.gmail.shnapi007.dao.CountryDao;
import com.gmail.shnapi007.entity.Country;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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

  // add countries to DB
  @Bean
  CommandLineRunner addCountriesToDBTable(CountryDao countryDao)
      throws IOException, URISyntaxException {

    List<String> countries = Files.readAllLines(Paths.get(RunApp.class.getClassLoader()
        .getResource("db.data/countries.txt").toURI()));
    return args -> countries.forEach(name -> countryDao.save(new Country(name)));
  }
}
