package org.module.client.businesslogicservice.departmentBLService_stub;

import java.util.ArrayList;

import org.module.client.businesslogicservice.departmentBLservice.DepartmentManageBLService;
import org.module.client.vo.DepartmentVO;
import org.module.common.dataservice.MyList;


public class DepartmentManageBLService_stub implements DepartmentManageBLService {
	DepartmentVO dpvo;
	public ArrayList<DepartmentVO> showAll() {
		System.out.println("show all departments");
		return null;
	}


	

	
	

	public ArrayList<DepartmentVO> fuzzySearch(String s) {
		System.out.println("find!");
		return null;
	}

	public boolean add(String name, String category, String location,
			String identity) {
		System.out.println("add successfully");
		return false;
	}

	public boolean delete(String identity) {
		System.out.println("delete successfully");		return false;
	}

	public boolean delete(MyList<String> ids) {
		System.out.println("delete successfully");
		return false;
	}

	public boolean update(String name, String category, String location,
			String identity) {
		System.out.println("modify successfully");
		return false;
	}







	public String[][] toArray() {
		// TODO Auto-generated method stub
		return null;
	}







	public ArrayList<String[]> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

}
