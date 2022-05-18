package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest02 {

	public static void main(String[] args) {
//		update(4L, "전략기획팀");
		DepartmentVo vo = new DepartmentVo();
		vo.setNo(11L);
		vo.setName("기반");
		
		update(vo);
	}

	private static boolean update(DepartmentVo vo) {
		boolean result= false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. connection
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb","webdb");
			
			// 3. SQL준비
			String sql = "update department" +
			" set name = ?"+
			" where no = ?";
			pstmt = connection.prepareStatement(sql); //SQL을 실행할 수 있는 객체
			
			// 4. Parameter Mapping(binding)
			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());
			
			// 4. SQL 실행
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (ClassNotFoundException e) {
				System.out.println("fail Driver loading" + e);
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		return result;	
	}
}
