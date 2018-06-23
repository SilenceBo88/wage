package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.model.Deduction;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.DeductionService;
import com.wage.service.DepartmentService;
import com.wage.service.EmployeeService;
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
* @Description: BankissuedController
* @author zb
* @date 2018/06/22 14:24
*/
@Controller
@RequestMapping("/bankissued")
public class BankissuedController {
    @Resource
    private DeductionService deductionService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Deduction>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Deduction> list = deductionService.selectListBy("dState", 1);
        for (Deduction deduction:list){
            Employee employee = employeeService.selectById(String.valueOf(deduction.geteId()));
            Department department = departmentService.selectById(String.valueOf(employee.getdId()));
            employee.setDepartment(department);
            deduction.setEmployee(employee);
        }
        PageInfo<Deduction> pageInfo = new PageInfo<Deduction>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "bankissuedList";
    }
}