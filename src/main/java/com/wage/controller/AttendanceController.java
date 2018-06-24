package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Attendance;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.AttendanceService;
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
* @Description: AttendanceController类
* @author zb
* @date 2018/06/22 14:23
*/
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Attendance>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Attendance> list = attendanceService.selectAll();
        for (Attendance attendance:list){
            Employee employee = employeeService.selectById(String.valueOf(attendance.geteId()));
            Department department = departmentService.selectById(String.valueOf(employee.getdId()));
            employee.setDepartment(department);
            attendance.setEmployee(employee);
        }
        PageInfo<Attendance> pageInfo = new PageInfo<Attendance>(list);

        List<Attendance> titles = attendanceService.selectTitles();
        model.addAttribute("titles", titles);
        model.addAttribute("pageInfo", pageInfo);
        return "attendanceList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model, String id) throws Exception {
        Employee employee = employeeService.selectById(id);
        model.addAttribute("employee", employee);
        return "attendanceAdd";
    }

    @PostMapping("/add")
    public String add(Attendance attendance, Model model) throws Exception {
        attendance.setaAbsences(attendance.getaShould() - attendance.getaReal());
        Integer state = attendanceService.insert(attendance);
        if (state == 0){
            model.addAttribute("message", "添加出勤情况失败");
            return "message";
        }else {
            model.addAttribute("message", "添加出勤情况成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, String id) throws Exception {
        Attendance attendance = attendanceService.selectById(id);
        Employee employee = employeeService.selectById(String.valueOf(attendance.geteId()));
        attendance.setEmployee(employee);
        model.addAttribute("attendance", attendance);
        return "attendanceUpdate";
    }

    @PostMapping("/update")
    public String update(Attendance attendance, Model model) throws Exception {
        attendance.setaAbsences(attendance.getaShould() - attendance.getaReal());
        Integer state = attendanceService.update(attendance);
        if (state == 0){
            model.addAttribute("message", "修改出勤情况失败");
            return "message";
        }else {
            model.addAttribute("message", "修改出勤情况成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(String id, Model model) throws Exception {
        Integer state = attendanceService.deleteById(id);
        if (state == 0){
            model.addAttribute("message", "删除出勤情况失败");
            return "message";
        }else {
            model.addAttribute("message", "删除出勤情况成功");
            return "message";
        }
    }

    /*@PostMapping("/insert")
    public Result<Integer> insert(Attendance attendance) throws Exception{
        Integer state = attendanceService.insert(attendance);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = attendanceService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Attendance attendance) throws Exception {
        Integer state = attendanceService.update(attendance);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Attendance> selectById(@RequestParam String id) throws Exception {
        Attendance attendance = attendanceService.selectById(id);
        return ResultUtil.SUCCESS(attendance);
    }

    *//*
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Attendance>>
    *//*
    @GetMapping("/list")
    public Result<PageInfo<Attendance>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Attendance> list = attendanceService.selectAll();
        PageInfo<Attendance> pageInfo = new PageInfo<Attendance>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }*/
}