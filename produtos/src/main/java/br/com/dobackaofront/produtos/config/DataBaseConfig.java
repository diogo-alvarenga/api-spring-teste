package br.com.dobackaofront.produtos.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataBaseConfig {

	/*tou fazendo as configurações que antes tinha feito no
	 * properties. Sao configurações do sql e seu driver*/
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/cursos");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
}
