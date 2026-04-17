package cn.qdd.tlias.controller;

import cn.qdd.tlias.pojo.entity.Emp;
import cn.qdd.tlias.pojo.query.EmpQueryParam;
import cn.qdd.tlias.pojo.vo.PageResult;
import cn.qdd.tlias.pojo.vo.Result;
import cn.qdd.tlias.service.IEmpService;
import cn.qdd.tlias.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private IEmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录: username={}", emp.getUsername());
        Emp loginEmp = empService.login(emp);
        if (loginEmp == null) {
            return Result.error("用户名或密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginEmp.getId());
        claims.put("username", loginEmp.getUsername());
        String jwt = JwtUtils.generateJwt(claims);
        return Result.success(jwt);
    }

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数： {}", empQueryParam);
        PageResult pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("请求参数emp: {}", emp);
        empService.save(emp);
        return Result.success(emp.getId());
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除部门: ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息");
        Emp emp  = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
