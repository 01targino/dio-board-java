package board.dio.persistence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dio.persistence.entity.BoardColumnEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardColumnDAO {

	private final Connection connection; 
	
	public BoardColumnEntity insert(final BoardColumnEntity entity) throws SQLException{
		var sql = "INSERT INTO BOARDS_COLUMN (name, ´order´, kind, board_id) VALUES (?, ?, ?, ?);";
		try(var statement = connection.prepareStatement(sql)) {
			var i = 1; 
			statement.setString(i++, entity.getName());
			statement.setInt(i++, entity.getOrder());
			statement.setString(i++, entity.getKind().name());			statement.setString(0, null);
			statement.setLong(i++, entity.getBoard().getId());
			statement.executeUpdate();
			
			var generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				entity.setId(generatedKeys.getLong(1));
			}
			
			return entity;
		}
	}

	public List<BoardColumnEntity> findByBoardId(Long id) throws SQLException {
		return null;
	}
	
}
