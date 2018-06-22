package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Department;
import com.wage.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: DepartmentController类
* @author zb
* @date 2018/06/22 14:21
*/
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @PostMapping("/insert")
    public Result<Integer> insert(Department department) throws Exception{
        Integer state = departmentService.insert(department);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = departmentService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Department department) throws Exception {
        Integer state = departmentService.update(department);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Department> selectById(@RequestParam String id) throws Exception {
        Department department = departmentService.selectById(id);
        return ResultUtil.SUCCESS(department);
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Department>>
    */
    @GetMapping("/list")
    public Result<PageInfo<Department>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Department> list = departmentService.selectAll();
        PageInfo<Department> pageInfo = new PageInfo<Department>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }
}