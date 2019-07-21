package com.lanou.mapper.master;

import java.util.List;

import com.lanou.annotation.MybatisAnnotation;

import com.lanou.bean.Emp;
import org.springframework.stereotype.Repository;

@Repository("empMapper")
public @MybatisAnnotation interface EmpMapper {
	public List<Emp> findAllEmp();

	public void deleteEmpById(int id);

	public void addEmp(Emp emp);

	public void update(Emp emp);

	public Emp findEmpById(int id);
}
