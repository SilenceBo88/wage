package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Admin;
import com.wage.model.Department;
import com.wage.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Description: AdminController类
* @author zb
* @date 2018/06/23 18:01
*/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private DepartmentService departmentService;

    @ResponseBody
    @PostMapping("/login")
    public Result<Admin> login(Admin admin, HttpServletRequest request) throws Exception{
        Admin adm = adminService.selectBy("uname", admin.getUname());
        if (adm.getUname().equals(admin.getUname()) && adm.getPwd().equals(admin.getPwd())){
            request.getSession().setAttribute("admin", adm);
            return ResultUtil.SUCCESS();
        }else{
            return ResultUtil.Error400("用户名或密码错误");
        }
    }

    @GetMapping("/logOut")
    public String sinOut(HttpServletRequest request) throws Exception{
        request.getSession().removeAttribute("admin");
        return "login";
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Admin>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Admin> list = adminService.selectAll();
        list.remove(0);
        for (Admin admin:list){
            Department department = departmentService.selectById(String.valueOf(admin.getType()));
            admin.setDepartment(department);
        }
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "adminList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model) throws Exception {
        List<Department> departments = departmentService.selectAll();
        model.addAttribute("departments", departments);
        return "adminAdd";
    }

    @PostMapping("/add")
    public String add(Admin admin, Model model) throws Exception {
        Integer state = adminService.insert(admin);
        if (state == 0){
            model.addAttribute("message", "添加管理员失败");
            return "message";
        }else {
            model.addAttribute("message", "添加管理员成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, String id) throws Exception {
        Admin admin = adminService.selectById(id);
        Department department = departmentService.selectById(String.valueOf(admin.getType()));
        admin.setDepartment(department);

        List<Department> departments = departmentService.selectAll();

        model.addAttribute("departments", departments);
        model.addAttribute("admin", admin);
        return "adminUpdate";
    }

    @PostMapping("/update")
    public String update(Admin admin, Model model) throws Exception {
        Integer state = adminService.update(admin);
        if (state == 0){
            model.addAttribute("message", "修改管理员失败");
            return "message";
        }else {
            model.addAttribute("message", "修改管理员成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(String id, Model model) throws Exception {
        Integer state = adminService.deleteById(id);
        if (state == 0){
            model.addAttribute("message", "删除管理员失败");
            return "message";
        }else {
            model.addAttribute("message", "删除管理员成功");
            return "message";
        }
    }

    /*@PostMapping("/insert")
    public Result<Integer> insert(Admin admin) throws Exception{
        Integer state = adminService.insert(admin);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = adminService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Admin admin) throws Exception {
        Integer state = adminService.update(admin);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Admin> selectById(@RequestParam String id) throws Exception {
        Admin admin = adminService.selectById(id);
        return ResultUtil.SUCCESS(admin);
    }

    *//*
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Admin>>
    *//*
    @GetMapping("/list")
    public Result<PageInfo<Admin>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Admin> list = adminService.selectAll();
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }*/
}