package com.br.service;

import com.br.common.JwtTokenUtils;
import com.br.common.Result;
import com.br.dao.AdminDao;
import com.br.entity.Admin;
import com.br.entity.Params;
import com.br.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public List<Admin> findAll(){
        return adminDao.selectAll();
    }

    public PageInfo<Admin> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Admin> list = adminDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Admin admin) {
        //进行非空判断
        if(admin.getName() == null || "".equals(admin.getName())){
            throw new CustomException("用户名不能为空!");
        }
        //进行重复性判断
        Admin user = adminDao.findByName(admin.getName());
        if(user != null){
            //说明重复了
            throw new CustomException("用户名已经存在，请更换!");
        }
        //不然就添加
        if(admin.getPassword() == null){
            admin.setPassword("123456");
        }
        adminDao.insertSelective(admin);
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }

    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);
    }

    public Admin login(Admin admin) {
        // 1. 进行一些非空判断
        if(admin.getName() == null || "".equals(admin.getName())){
            throw new CustomException("用户名不能为空!");
        }
        if(admin.getPassword() == null || "".equals(admin.getPassword())){
            throw new CustomException("密码不能为空!");
        }
        // 2. 从数据库里面根据这个用户名和密码去查询对应的管理员信息，
         Admin user = adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        // 如果查出来没有，那说明输入的用户名或者密码有误，提示用户，不允许登录
        if(user == null){
            throw new CustomException("用户名或密码输入有误！");
        }
        // 如果查出来了有，那说明确实有这个管理员，而且输入的用户名和密码都对
        //生成对应的token，然后返回前台
        String token = JwtTokenUtils.genToken(user.getId().toString(),user.getPassword());
        user.setToken(token);
        return user;
    }

    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
}
