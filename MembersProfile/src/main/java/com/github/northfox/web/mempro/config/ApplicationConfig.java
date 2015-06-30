package com.github.northfox.web.mempro.config;

import java.io.File;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.github.northfox.web.mempro" })
@EnableTransactionManagement
@EnableJpaRepositories("com.github.northfox.web.mempro.persistence")
public class ApplicationConfig {

  private static final Logger logger =
      LoggerFactory.getLogger(ApplicationConfig.class);

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    String scanedPackage = "com.github.northfox.web.mempro";
    logger.info("Do entityManagerFactory and scan to the package[{}].",
        scanedPackage);

    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setPackagesToScan(scanedPackage);

    EclipseLinkJpaVendorAdapter jva = new EclipseLinkJpaVendorAdapter();
    jva.setDatabase(Database.H2);
    jva.setGenerateDdl(true);
    jva.setShowSql(true);

    Properties prop = new Properties();
    prop.setProperty("eclipselink.weaving", "false");
    em.setJpaVendorAdapter(jva);
    em.setJpaProperties(prop);

    em.setDataSource(dataSource());

    return em;
  }

  @Bean
  public DataSource dataSource() {
    String dbUrl = new File(ApplicationConfig.class.getClassLoader()
        .getResource(".").getPath()
        + "derby/MembersProfile").getAbsolutePath();
    dbUrl = "jdbc:derby:" + dbUrl + ";create=true";
    logger.info("Do dataSource and load the data source[{}]", dbUrl);

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(EmbeddedDriver.class.getName());
    dataSource.setUrl(dbUrl);
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    logger.info("Do transactionManager.");

    JpaTransactionManager tm = new JpaTransactionManager();
    tm.setEntityManagerFactory(emf);
    return tm;
  }
}