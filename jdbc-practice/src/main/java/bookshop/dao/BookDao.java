package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {

	public boolean insert(BookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "insert into book values (null, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. Mapping(bind)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getStateCode());
			pstmt.setLong(3, vo.getAuthorNo());

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

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select a.no, a.title, b.name, a.state_code, a.author_no" + " from book a, author b"
					+ " where a.author_no = b.no" + " order by no";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping

			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String authorName = rs.getString(3);
				String stateCode = rs.getString(4);
				Long author = rs.getLong(5);
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setAuthorName(authorName);
				vo.setStateCode(stateCode);
				vo.setAuthorNo(author);
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
	public void update(Long no, String statecode) {
		BookVo vo = new BookVo();
		vo.setNo(no);
		vo.setStateCode(statecode);
		update(vo);
		
	}
	public boolean update(BookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "update book set state_code =? where no=?";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. Mapping(bind)
			pstmt.setString(1, vo.getStateCode());
			pstmt.setLong(2, vo.getNo());

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

	public BookVo findByNo(long no) {
		BookVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select a.no, a.title, a.author_no, b.name, a.state_code" 
					+ " from book a, author b"
					+ " where a.author_no = b.no"
					+ " and a.no=?";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체
			
			// 4. parameter mapping
			pstmt.setLong(1, no);
			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			if(rs.next()) {
				
				result = new BookVo();
				result.setNo(rs.getLong(1));
				result.setTitle(rs.getString(2));
				result.setAuthorNo(rs.getLong(3));
				result.setAuthorName(rs.getString(4));
				result.setStateCode(rs.getString(5));
//				Long no = rs.getLong(1);
//				String title = rs.getString(2);
//				String authorName = rs.getString(3);
//				String stateCode = rs.getString(4);

//				BookVo vo = new BookVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setAuthorName(authorName);
//				vo.setStateCode(stateCode);
//				result.add(vo);
				
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
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail Driver loading");
		}

		return connection;
	}

	

}
