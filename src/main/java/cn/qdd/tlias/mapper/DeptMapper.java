package cn.qdd.tlias.mapper;

import cn.qdd.tlias.pojo.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> findAll();

    void delete(Integer deptId);

    Integer countEmpByDeptId(Integer deptId);

    void insert(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);

}
