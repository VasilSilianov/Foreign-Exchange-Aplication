package com.example.demo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")

public class JPAConfiguration {


        private String dbURl,dbUsername,dBPassword;

        @Autowired
        public JPAConfiguration(Environment env) {
            dbURl = env.getProperty("spring.database.url");
            dbUsername = env.getProperty("spring.database.username");
            dBPassword = env.getProperty("spring.database.password");
        }


        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl(dbURl);
            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dBPassword);
            return dataSource;
        }

        private Properties hibernateProperties() {
            Properties hibernateProperties = new Properties();
            hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

            return hibernateProperties;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean em
                    = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource());
            em.setPackagesToScan("com.example.demo.models");

            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.setJpaProperties(hibernateProperties());
            return em;
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapperImpl();
        }

    }



