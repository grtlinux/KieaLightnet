package org.tain.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;

//@Profile("local")
//@Configuration
public class H2ServerConfig {

	/*
	@Bean
	public Server h2TcpServer() throws SQLException {
		//return Server.createTcpServer().start();
		return Server.createTcpServer("-tcp", "-tcpAllowOthers").start();
	}
	*/
	
	//@Bean
	//@ConfigurationProperties("spring.datasource.hikari")
	public DataSource dataSource() throws SQLException {
		Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
		return new com.zaxxer.hikari.HikariDataSource();
	}
}
