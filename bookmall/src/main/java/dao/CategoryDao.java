package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CategoryVo;
import vo.MemberVo;

public class CategoryDao {
	public boolean insert(CategoryVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "insert into category values (null, ?)";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. Mapping(bind)
			pstmt.setString(1, vo.getCategoryName());

			// 4. SQL 실행
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
		return result;
	}

	public List<CategoryVo> findSelect() {
		List<CategoryVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select *"
					+ "from category";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping

			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {

				Long no = rs.getLong(1);
				String name = rs.getString(2);
				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setCategoryName(name);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.45:3306/bookmall?charset=utf8";
			connection = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail Driver loading");
		}

		return connection;
	}
}
