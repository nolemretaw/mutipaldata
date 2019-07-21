package com.lanou.service.cluster;

import com.lanou.bean.Emp;

import java.util.List;

public interface ClusterEmpService {
    public List<Emp> findAllEmp();
    public Emp findById(int id);
}
