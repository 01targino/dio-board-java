package board.dio.persistence.migration;

import java.io.FileOutputStream;

import static board.dio.persistence.config.ConnectionConfig.*;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MigrationStrategy {

	 private final Connection connection;

	    public void executeMigration() throws SQLException {
	        var originalOut = System.out;
	        var originalErr = System.err;

	        try (var fos = new FileOutputStream("liquibase.log");
	             var printStream = new PrintStream(fos)) {

	            System.setOut(printStream);
	            System.setErr(printStream);

	            try {
	                var jdbcConnection = new JdbcConnection(this.connection);
	                var liquibase = new Liquibase("/db/changelog/db.changelog-master.yml",
	                        new ClassLoaderResourceAccessor(), jdbcConnection);

	                liquibase.update();
	            } catch (LiquibaseException e) {
	                e.printStackTrace();
	                throw new RuntimeException("Erro ao executar migração do Liquibase", e);
	            }

	        } catch (IOException e) {
	            throw new RuntimeException("Erro ao criar arquivo de log", e);
	        } finally {
	            System.setOut(originalOut);
	            System.setErr(originalErr);
	            System.out.println("Execução finalizada.");
	        }
	    }
	}