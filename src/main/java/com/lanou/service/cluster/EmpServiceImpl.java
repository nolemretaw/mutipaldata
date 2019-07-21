package com.lanou.service.cluster;

import com.lanou.bean.Emp;
import com.lanou.mapper.cluster.ClusterEmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot01
 * @description:
 * @author: hutingrong
 * @create: 2019-07-14 17:02
 */
@Service("clusterEmpService")
public class EmpServiceImpl implements ClusterEmpService {
    @Autowired
    private ClusterEmpMapper clusterEmpMapper;
    @Override
    public List<Emp> findAllEmp() {
        System.out.println("findAllEmp is called");
        return clusterEmpMapper.findAllEmp();
    }

    @Override
    public Emp findById(int id) {
        return clusterEmpMapper.findById(id);
    }

}
