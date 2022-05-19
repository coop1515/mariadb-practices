package dao.test;

import java.util.List;

import dao.BookDao;
import dao.CartDao;
import vo.BookVo;
import vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
//		testInsert();
//		findSelect();
		findSelectAll();

	}

	private static void findSelect() {
		List<CartVo> list = new CartDao().findSelect();
		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}
	private static void findSelectAll() {
		List<CartVo> list = new CartDao().findSelectAll();
		for (CartVo vo : list) {
			System.out.println(vo);
		}

	}

	public static void testInsert() {
		CartDao dao = new CartDao();
		CartVo vo = new CartVo();
		vo.setTitle("이것이 MariaDB다");
		vo.setPrice(1000L);
		vo.setAmount(2L);
		vo.setMember_no(1L);
		vo.setBook_no(1L);
		dao.insert(vo);

		vo.setTitle("코스모스");
		vo.setPrice(1000L);
		vo.setAmount(1L);
		vo.setMember_no(1L);
		vo.setBook_no(3L);
		dao.insert(vo);
	}
}
