package cn.qdd.tlias.controller;

import cn.qdd.tlias.pojo.entity.Clazz;
import cn.qdd.tlias.pojo.query.ClazzQueryParam;
import cn.qdd.tlias.pojo.vo.PageResult;
import cn.qdd.tlias.pojo.vo.Result;
import cn.qdd.tlias.service.IClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private IClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("班级分页查询参数: {}", clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询班级: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("新增班级参数: {}", clazz);
        clazzService.save(clazz);
        return Result.success(clazz.getId());
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级参数: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteById(@RequestParam Integer id) {
        log.info("删除班级id: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
}
