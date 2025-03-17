package board.dio.persistence.config;

import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = PRIVATE)
public final class ConnectionConfig {

	public static Connection getConnection() throws SQLException {
		
		String url = "jdbc:mysql://localhost/board";
		String user = "root";
		String password = "123456";
		
		Connection connection = DriverManager.getConnection(url, user, password); 
		connection.setAutoCommit(false);
		return connection;
	}
	
}
