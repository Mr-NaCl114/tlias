package cn.qdd.tlias.service.impl;

import cn.qdd.tlias.mapper.DeptMapper;
import cn.qdd.tlias.pojo.entity.Dept;
import cn.qdd.tlias.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeptService implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void delete(Integer deptId) {
        Integer empCount = deptMapper.countEmpByDeptId(deptId);
        if (empCount != null && empCount > 0) {
            throw new RuntimeException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.delete(deptId);
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        log.info("保存部门，dept={}",dept);

        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        Dept dept = deptMapper.getById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }

}
