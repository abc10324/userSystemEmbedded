package com.sam.usersystem.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.sam.usersystem.model")
public class SpringJavaConfig {

	
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//		
//		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		ds.setUrl("jdbc:mysql://localhost:3306/usersystem?serverTimezone=UTC");
//		ds.setUsername("root");
//		ds.setPassword("passw0rd");
//		
//		return ds;
//	} 
	
//	@Bean
//	public DataSource dataSource() {
//		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
//		
//		bean.setJndiName("java:comp/env/jdbc/UserDB");
//		bean.setProxyInterface(DataSource.class);
//		
//		try {
//			bean.afterPropertiesSet();
//		} catch (IllegalArgumentException | NamingException e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//		return (DataSource) bean.getObject();
//	} 
	
	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
//                .addScript("schema.sql")
//                .addScripts("user_data.sql", "country_data.sql")
                .build();
    }
	
	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(dataSource())
				  .configure("hibernate.cfg.xml")
				  .buildSessionFactory();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactory());
	}
	
	
}
