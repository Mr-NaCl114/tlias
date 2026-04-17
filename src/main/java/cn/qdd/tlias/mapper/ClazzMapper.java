package cn.qdd.tlias.mapper;

import cn.qdd.tlias.pojo.entity.Clazz;
import cn.qdd.tlias.pojo.query.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    Clazz getById(Integer id);

    void insert(Clazz clazz);

    void updateById(Clazz clazz);

    void deleteById(Integer id);
}
