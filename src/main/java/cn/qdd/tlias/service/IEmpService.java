package cn.qdd.tlias.service;

import cn.qdd.tlias.pojo.entity.Emp;
import cn.qdd.tlias.pojo.query.EmpQueryParam;
import cn.qdd.tlias.pojo.vo.PageResult;

import java.util.List;

public interface IEmpService {
    Emp login(Emp emp);

    PageResult page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);
}
