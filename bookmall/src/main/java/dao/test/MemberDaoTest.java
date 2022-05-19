package dao.test;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
//		testInsert();
		findSelect();
	}

	private static void findSelect() {
		List<MemberVo> list = new MemberDao().findSelect();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}

	}

	public static void testInsert() {
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();
		vo.setName("둘리");
		vo.setPhone_number("010-1111-1111");
		vo.setEmail("dooly@gmail.com");
		vo.setPassword("1234");
		dao.insert(vo);

		vo.setName("마이콜");
		vo.setPhone_number("010-2222-2222");
		vo.setEmail("michol@gmail.com");
		vo.setPassword("1234");
		dao.insert(vo);
	}
}
