package com.lanou.service.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanou.bean.Emp;
import com.lanou.mapper.master.EmpMapper;

@Service("empService")
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpMapper empMapper;
	
	@Override
	public List<Emp> findAllEmp() {
		// TODO Auto-generated method stub
		return empMapper.findAllEmp();
	}

	@Override
	public void deleteEmpById(int id) {
		// TODO Auto-generated method stub
		empMapper.deleteEmpById(id);
	}

	@Override
	public void addEmp(Emp emp) {
		empMapper.addEmp(emp);
	}

	@Override
	public void update(Emp emp) {
		// TODO Auto-generated method stub
		empMapper.update(emp);
	}

	@Override
	public Emp findEmpById(int id) {
		// TODO Auto-generated method stub
		return empMapper.findEmpById(id);
	}

}
