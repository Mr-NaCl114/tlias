package cn.qdd.tlias.mapper;

import cn.qdd.tlias.pojo.entity.Emp;
import cn.qdd.tlias.pojo.query.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    Emp login(Emp emp);

    List<Emp> list(EmpQueryParam empQueryParam);

    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @MapKey("name")
    List<Map> countEmpGenderData();

}
