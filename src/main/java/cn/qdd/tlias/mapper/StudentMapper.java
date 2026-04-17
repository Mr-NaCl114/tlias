package cn.qdd.tlias.mapper;

import cn.qdd.tlias.pojo.entity.Student;
import cn.qdd.tlias.pojo.query.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    Student getById(Integer id);

    void insert(Student student);

    void updateById(Student student);

    void deleteById(Integer id);

    @MapKey("clazzName")
    List<Map<String,Object>> countStudentByClazz();

    @MapKey("name")
    List<Map> countStudentDegreeData();
}
