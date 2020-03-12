package springdatajpamysql.springdatajpamysql.datasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class MySqlDataSourceProperties {
	

		@Bean
		public MySqlDataSourceProperties dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();

			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("Kiran@1234");
			dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=false");

			return null;
		}
	}
