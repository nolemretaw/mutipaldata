package com.lanou.mapper.cluster;

import com.lanou.annotation.MybatisAnnotation;
import com.lanou.bean.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("clusterEmpMapper")
public @MybatisAnnotation interface ClusterEmpMapper {
    List<Emp> findAllEmp();
    Emp findById(int id);
}
