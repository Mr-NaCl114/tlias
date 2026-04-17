package cn.qdd.tlias.service;

import cn.qdd.tlias.pojo.vo.PageResult;
import cn.qdd.tlias.pojo.entity.Student;
import cn.qdd.tlias.pojo.query.StudentQueryParam;

public interface IStudentService {
    PageResult page(StudentQueryParam studentQueryParam);

    Student getById(Integer id);

    void save(Student student);

    void update(Student student);

    void deleteById(Integer id);
}
