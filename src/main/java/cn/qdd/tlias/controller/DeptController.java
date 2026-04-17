package cn.qdd.tlias.controller;

import cn.qdd.tlias.pojo.entity.Dept;
import cn.qdd.tlias.pojo.vo.Result;
import cn.qdd.tlias.service.impl.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result list(){
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer deptId){
        log.info("删除部门，id={}",deptId);
        try {
            deptService.delete(deptId);
            return Result.success();
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/depts")
    public Result save(@RequestBody Dept dept){
        log.info("新增部门，dept={}",dept);
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询部门，id={}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门，dept={}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
