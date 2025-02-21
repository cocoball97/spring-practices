
package guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

public class JdbcContext {
private DataSource dataSource;
	
	public JdbcContext(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// select 해서 결과값 반환
	// RowMapper : row 값을 객체로 매핑
	public <E> List<E> queryForList(String sql, RowMapper<E> rowMapper) {
		return queryForListWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				return connection.prepareStatement(sql);
			}
		}, rowMapper);
	}
	
	// delete, update 등 수행
	public int excuteUpdate(String sql, Object[] parameters) {
		return executeUpdateWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				
				for(int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}				
				
				return pstmt;
			}
		});
	}
	
	// 값을 가져오는 함수
	private <E> List<E> queryForListWithStatementStrategy(StatementStrategy statementStrategy, RowMapper<E> rowMapper) throws RuntimeException {
		List<E> result = new ArrayList<>();
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = statementStrategy.makeStatement(conn);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				E e = rowMapper.mapRow(rs, rs.getRow());
				result.add(e);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
	
	// 결과 개수 카운트
	private int executeUpdateWithStatementStrategy(StatementStrategy statementStrategy) throws RuntimeException {		
		int count = 0;
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = statementStrategy.makeStatement(conn);
		) {
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		
		return count;	
	}
}
