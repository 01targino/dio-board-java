package board.dio;

import java.sql.SQLException;

import static board.dio.persistence.config.ConnectionConfig.getConnection;
import board.dio.persistence.migration.MigrationStrategy;
import liquibase.exception.DatabaseException;

public class Main {
	public static void main(String[] args) throws SQLException, DatabaseException {
		try(var connection = getConnection()) {
			new MigrationStrategy(connection).executeMigration();
		}
	}
}
