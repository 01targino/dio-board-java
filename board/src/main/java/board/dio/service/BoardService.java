package board.dio.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.dio.persistence.dao.BoardColumnDAO;
import board.dio.persistence.dao.BoardDAO;
import board.dio.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardService {
	
	private final Connection connection;
	
	public BoardEntity insert(final BoardEntity entity) throws SQLException {
		var dao = new BoardDAO(connection);
		var boardColumnDAO = new BoardColumnDAO(connection);
		try {
			dao.insert(entity);
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		}
		
		return entity;
		
	}
	
	public boolean delete(final Long id) throws SQLException{
		
		var dao = new BoardDAO(connection);
		try {
			if (!dao.exists(id)) {
				return false;
			} else {
			dao.delete(id);
			connection.commit();
			return true;
			}
		} catch(SQLException e) {
			connection.rollback();
			throw e;
		}
		
	}
	
	

}
