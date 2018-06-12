package br.com.ganix.establishment.persistence.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Class to configuration connection database
 * @author Marllon
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "br.com.ganix.establishment.persistence.repository")
@PropertySource("classpath:datasource/postgre-datasource.properties")
public class Connection {

	@Autowired
	private Environment env;
	
	private static String DRIVER_CLASS	= "jdbc.driverClassName";
	private static String URL 			= "jdbc.url";
	private static String USER_NAME 	= "jdbc.username";
	private static String PASSWORD 		= "jdbc.password" ; 
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName(env.getProperty(DRIVER_CLASS));
		driver.setUrl(env.getProperty(URL));
		driver.setUsername(env.getProperty(USER_NAME));
		driver.setPassword(env.getProperty(PASSWORD));
		
		//driver.setDriverClassName("org.postgresql.Driver");
		//driver.setUrl("jdbc:postgresql://93.188.164.195:5432/establishmentdb");
		//driver.setUsername("postgres");
		//driver.setPassword("p0stgr3s");
		return driver;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(getClass().getPackage().getName());
		factory.setDataSource(dataSource());
		return factory;
	}

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
}
