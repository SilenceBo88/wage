package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.DepartmentService;
import com.wage.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Employee>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Employee> list = employeeService.selectAll();
        for (Employee employee:list){
            Department department = departmentService.selectById(String.valueOf(employee.getdId()));
            employee.setDepartment(department);
        }
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "employeeList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model) throws Exception {
        List<Department> departments = departmentService.selectAll();
        model.addAttribute("departments", departments);
        return "employeeAdd";
    }

    @PostMapping("/add")
    public String add(Employee employee, Model model) throws Exception {
        Integer state = employeeService.insert(employee);
        if (state == 0){
            model.addAttribute("message", "添加职员失败");
            return "message";
        }else {
            model.addAttribute("message", "添加职员成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, String id) throws Exception {
        Employee employee = employeeService.selectById(id);
        Department department = departmentService.selectById(String.valueOf(employee.getdId()));
        employee.setDepartment(department);

        List<Department> departments = departmentService.selectAll();

        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        return "employeeUpdate";
    }

    @PostMapping("/update")
    public String update(Employee employee, Model model) throws Exception {
        Integer state = employeeService.update(employee);
        if (state == 0){
            model.addAttribute("message", "修改职员失败");
            return "message";
        }else {
            model.addAttribute("message", "修改职员成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(String id, Model model) throws Exception {
        Integer state = employeeService.deleteById(id);
        if (state == 0){
            model.addAttribute("message", "删除职员失败");
            return "message";
        }else {
            model.addAttribute("message", "删除职员成功");
            return "message";
        }
    }

    /*@PostMapping("/insert")
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

    *//*
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Employee>>
    *//*
    @GetMapping("/list")
    public Result<PageInfo<Employee>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Employee> list = employeeService.selectAll();
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }*/

}