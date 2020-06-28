package org.tain.config;

import java.sql.SQLException;

import org.h2.tools.Server;

//@Configuration
public class H2ServerConfig {

	//@Bean
	public Server h2TcpServer() throws SQLException {
		return Server.createTcpServer().start();
	}
}
