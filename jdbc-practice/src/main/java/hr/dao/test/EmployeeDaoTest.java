package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {
		testfindbyNameOrLastName("ken");
	}
	
	public static void testfindbyNameOrLastName(String name) {
		List<EmployeeVo> list = new EmployeeDao().findbyNameOrLastName(name);
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}
}
