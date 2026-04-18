package cn.qdd.tlias.controller;

import cn.qdd.tlias.pojo.entity.Emp;
import cn.qdd.tlias.pojo.vo.Result;
import cn.qdd.tlias.service.IEmpService;
import cn.qdd.tlias.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class AuthController {

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
}
