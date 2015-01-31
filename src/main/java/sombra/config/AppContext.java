package sombra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.security.Security;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = "sombra")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "sombra.dao")
@PropertySources({
        @PropertySource("classpath:properties/hibernate.properties"),
        @PropertySource("classpath:properties/jdbc.properties"),
        @PropertySource("classpath:properties/mail.properties")
})
@Import({SecuriryConfiguration.class, MvcConfiguration.class})
public class AppContext {

    @Autowired
    Environment env;

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
        return properties;
    };

    private Properties mailProperties(){
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        return properties;
    }


    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.host"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("sombra/entity", "sombra/dao");
        entityManagerFactoryBean.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        entityManagerFactoryBean.setValidationMode(ValidationMode.NONE);
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public JavaMailSender mailSender(){
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        mailSender.setUsername(env.getProperty("mail.user"));
        mailSender.setPassword(env.getProperty("mail.password"));
        mailSender.setDefaultEncoding("utf8");
        mailSender.setProtocol("smtp");
        mailSender.setJavaMailProperties(mailProperties());
        return mailSender;
    }
}
