package com.wage.controller;

import com.github.pagehelper.PageHelper;
import com.wage.model.Department;
import com.wage.service.DepartmentService;
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
* @Description: DepartmentController类
* @author zb
* @date 2018/06/22 14:21
*/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Department>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Department> list = departmentService.selectAll();
        PageInfo<Department> pageInfo = new PageInfo<Department>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "departmentList";
    }

    @GetMapping("/addPage")
    public String addPage() throws Exception {
        return "departmentAdd";
    }

    @PostMapping("/add")
    public String add(Department department, Model model) throws Exception {
        Integer state = departmentService.insert(department);
        if (state == 0){
            model.addAttribute("message", "添加部门失败");
            return "message";
        }else {
            model.addAttribute("message", "添加部门成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, String id) throws Exception {
        Department department = departmentService.selectById(id);
        model.addAttribute("department", department);
        return "departmentUpdate";
    }

    @PostMapping("/update")
    public String update(Department department, Model model) throws Exception {
        Integer state = departmentService.update(department);
        if (state == 0){
            model.addAttribute("message", "修改部门失败");
            return "message";
        }else {
            model.addAttribute("message", "修改部门成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(String id, Model model) throws Exception {
        Integer state = departmentService.deleteById(id);
        if (state == 0){
            model.addAttribute("message", "删除部门失败");
            return "message";
        }else {
            model.addAttribute("message", "删除部门成功");
            return "message";
        }
    }

    /*@PostMapping("/insert")
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

    *//*
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Department>>
    *//*
    @GetMapping("/list")
    public Result<PageInfo<Department>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Department> list = departmentService.selectAll();
        PageInfo<Department> pageInfo = new PageInfo<Department>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }*/
}