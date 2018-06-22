package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Bankissued;
import com.wage.service.BankissuedService;
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
* @Description: BankissuedController类
* @author zb
* @date 2018/06/22 14:24
*/
@RestController
@RequestMapping("/bankissued")
public class BankissuedController {

    @Resource
    private BankissuedService bankissuedService;

    @PostMapping("/insert")
    public Result<Integer> insert(Bankissued bankissued) throws Exception{
        Integer state = bankissuedService.insert(bankissued);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = bankissuedService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Bankissued bankissued) throws Exception {
        Integer state = bankissuedService.update(bankissued);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Bankissued> selectById(@RequestParam String id) throws Exception {
        Bankissued bankissued = bankissuedService.selectById(id);
        return ResultUtil.SUCCESS(bankissued);
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Bankissued>>
    */
    @GetMapping("/list")
    public Result<PageInfo<Bankissued>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Bankissued> list = bankissuedService.selectAll();
        PageInfo<Bankissued> pageInfo = new PageInfo<Bankissued>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }
}