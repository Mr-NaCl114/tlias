package cn.qdd.tlias.controller;

import cn.qdd.tlias.pojo.vo.PageResult;
import cn.qdd.tlias.pojo.vo.Result;
import cn.qdd.tlias.pojo.entity.Student;
import cn.qdd.tlias.pojo.query.StudentQueryParam;
import cn.qdd.tlias.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学员分页查询参数: {}", studentQueryParam);
        PageResult pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询学员: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学员参数: {}", student);
        studentService.save(student);
        return Result.success(student.getId());
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员参数: {}", student);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteById(@RequestParam Integer id) {
        log.info("删除学员id: {}", id);
        studentService.deleteById(id);
        return Result.success();
    }
}
