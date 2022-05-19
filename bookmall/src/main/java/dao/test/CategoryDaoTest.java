package dao.test;

import java.util.List;

import dao.CategoryDao;
import vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
//		testInsert();
		findSelect();
	}

	private static void findSelect() {
		List<CategoryVo> list = new CategoryDao().findSelect();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testInsert() {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo = new CategoryVo();
		vo.setCategoryName("IT");
		dao.insert(vo);

		vo.setCategoryName("과학");
		dao.insert(vo);

		vo.setCategoryName("문학");
		dao.insert(vo);
	}
}
