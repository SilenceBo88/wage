package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Deduction;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.DeductionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
* @Description: DeductionController类
* @author zb
* @date 2018/06/22 14:23
*/
@Controller
@RequestMapping("/deduction")
public class DeductionController {

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
        List<Deduction> list = deductionService.selectAll();
        for (Deduction deduction:list){
            Employee employee = employeeService.selectById(String.valueOf(deduction.geteId()));
            Department department = departmentService.selectById(String.valueOf(employee.getdId()));
            employee.setDepartment(department);
            deduction.setEmployee(employee);
        }
        PageInfo<Deduction> pageInfo = new PageInfo<Deduction>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "deductionList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model, String id) throws Exception {
        Employee employee = employeeService.selectById(id);
        model.addAttribute("employee", employee);
        return "deductionAdd";
    }

    @PostMapping("/add")
    public String add(Deduction deduction, Model model) throws Exception {
        deduction.setdRealWage(deduction.getdBasicWage()
                + deduction.getdBonus() - deduction.getdFine() - deduction.getdTax());
        Integer state = deductionService.insert(deduction);
        if (state == 0){
            model.addAttribute("message", "添加工资详情失败");
            return "message";
        }else {
            model.addAttribute("message", "添加工资详情成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, String id) throws Exception {
        Deduction deduction = deductionService.selectById(id);
        Employee employee = employeeService.selectById(String.valueOf(deduction.geteId()));
        deduction.setEmployee(employee);
        model.addAttribute("deduction", deduction);
        return "deductionUpdate";
    }

    @PostMapping("/update")
    public String update(Deduction deduction, Model model) throws Exception {
        deduction.setdRealWage(deduction.getdBasicWage()
                + deduction.getdBonus() - deduction.getdFine() - deduction.getdTax());
        Integer state = deductionService.update(deduction);
        if (state == 0){
            model.addAttribute("message", "修改工资详情失败");
            return "message";
        }else {
            model.addAttribute("message", "修改工资详情成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(String id, Model model) throws Exception {
        Integer state = deductionService.deleteById(id);
        if (state == 0){
            model.addAttribute("message", "删除工资详情失败");
            return "message";
        }else {
            model.addAttribute("message", "删除工资详情成功");
            return "message";
        }
    }

    @GetMapping("/updateState")
    public String updateState(String id, String dState, Model model) throws Exception {
        Deduction deduction = deductionService.selectById(id);
        deduction.setdState(Integer.valueOf(dState));
        Integer state = deductionService.update(deduction);
        if (state == 0){
            model.addAttribute("message", "修改状态失败");
            return "message";
        }else {
            return "redirect:/deduction/list?page=1&size=10";
        }
    }
    /*@PostMapping("/insert")
    public Result<Integer> insert(Deduction deduction) throws Exception{
        Integer state = deductionService.insert(deduction);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = deductionService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Deduction deduction) throws Exception {
        Integer state = deductionService.update(deduction);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Deduction> selectById(@RequestParam String id) throws Exception {
        Deduction deduction = deductionService.selectById(id);
        return ResultUtil.SUCCESS(deduction);
    }

    *//*
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Deduction>>
    *//*
    @GetMapping("/list")
    public Result<PageInfo<Deduction>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Deduction> list = deductionService.selectAll();
        PageInfo<Deduction> pageInfo = new PageInfo<Deduction>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }*/
}