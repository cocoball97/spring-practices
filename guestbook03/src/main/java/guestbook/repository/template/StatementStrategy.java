package guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// sql 작업 (PreparedStatement 생성) 유연하게 처리하기 위한 패턴 
// connection역할
public interface StatementStrategy {
	public PreparedStatement makeStatement(Connection connection) throws SQLException ;
}