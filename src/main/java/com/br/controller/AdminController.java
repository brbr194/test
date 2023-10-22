package com.br.controller;

import com.br.common.AutoLog;
import com.br.common.CaptureConfig;
import com.br.common.JwtTokenUtils;
import com.br.common.Result;
import com.br.dao.AdminDao;
import com.br.entity.Admin;
import com.br.entity.Params;
import com.br.service.AdminService;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import org.apache.naming.factory.LookupFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(value = "管理员接口", tags = "管理员")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);


    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    @AutoLog("登陆系统")
    public Result login(@RequestBody Admin admin, @RequestParam String key, HttpServletRequest request){
        if(!admin.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))){
            //不相等验证不通过
            CaptchaUtil.clear(request);
            return Result.error("验证码不正确");
        }

        Admin loginUser = adminService.login(admin);
        CaptureConfig.CAPTURE_MAP.remove(key);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Admin admin){
        adminService.add(admin);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Admin admin){
        if(admin.getId() == null){
            adminService.add(admin);
        }else{
            adminService.update(admin);
        }

        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        List<Admin> list = adminService.findAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Admin> info = adminService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.delete(id);
        return Result.success();
    }
}
