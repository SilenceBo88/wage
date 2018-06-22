package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Employee;
import com.wage.service.EmployeeService;
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
* @Description: EmployeeController类
* @author zb
* @date 2018/06/22 14:46
*/
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/insert")
    public Result<Integer> insert(Employee employee) throws Exception{
        Integer state = employeeService.insert(employee);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = employeeService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Employee employee) throws Exception {
        Integer state = employeeService.update(employee);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Employee> selectById(@RequestParam String id) throws Exception {
        Employee employee = employeeService.selectById(id);
        return ResultUtil.SUCCESS(employee);
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Employee>>
    */
    @GetMapping("/list")
    public Result<PageInfo<Employee>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Employee> list = employeeService.selectAll();
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }
}