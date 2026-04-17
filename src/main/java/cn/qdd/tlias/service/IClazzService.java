package cn.qdd.tlias.service;

import cn.qdd.tlias.pojo.entity.Clazz;
import cn.qdd.tlias.pojo.query.ClazzQueryParam;
import cn.qdd.tlias.pojo.vo.PageResult;

public interface IClazzService {
    PageResult page(ClazzQueryParam clazzQueryParam);

    Clazz getById(Integer id);

    void save(Clazz clazz);

    void update(Clazz clazz);

    void deleteById(Integer id);
}
