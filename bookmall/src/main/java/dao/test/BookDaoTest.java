package dao.test;

import java.util.List;

import dao.BookDao;
import vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
//		testInsert();
		findSelect();
	}

	private static void findSelect() {
		List<BookVo> list = new BookDao().findSelect();
		for (BookVo vo : list) {
			System.out.println(vo);
		}

	}

	public static void testInsert() {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo.setTitle("이것이 MariaDB다");
		vo.setPrice(1000L);
		vo.setCategory_no(1L);
		dao.insert(vo);

		vo.setTitle("러닝 ReactJS");
		vo.setPrice(2000L);
		vo.setCategory_no(1L);
		dao.insert(vo);

		vo.setTitle("코스모스");
		vo.setPrice(1000L);
		vo.setCategory_no(2L);
		dao.insert(vo);
	}
}
