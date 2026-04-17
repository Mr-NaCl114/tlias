package cn.qdd.tlias.service;

import cn.qdd.tlias.pojo.entity.Dept;

import java.util.List;

public interface IDeptService {

    List<Dept> findAll();

    void delete(Integer deptId);

    void insert(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
