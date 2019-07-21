package com.lanou.service.master;

import java.util.List;

import com.lanou.bean.Emp;

public interface EmpService {
	public List<Emp> findAllEmp();
	public void deleteEmpById(int id);
	public void addEmp(Emp emp);
	public void update(Emp emp);
	public Emp findEmpById(int id);
}
