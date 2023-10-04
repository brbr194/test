package com.br.service;


import cn.hutool.core.util.ObjectUtil;
import com.br.common.JwtTokenUtils;
import com.br.dao.AdminDao;
import com.br.dao.AuditDao;
import com.br.entity.Admin;
import com.br.entity.Audit;
import com.br.entity.Params;
import com.br.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class AuditService {
    @Resource
    private AuditDao auditDao;

    @Resource
    private AdminDao adminDao;


    public void add(Audit audit) {
        auditDao.insertSelective(audit);
    }

    public void update(Audit audit) {
        auditDao.updateByPrimaryKeySelective(audit);
    }

    public PageInfo<Audit> findBySearch(Params params) {

        //实现自己自己能看到自己提交的信息
        Admin user = JwtTokenUtils.getCurrentUser();
        if(ObjectUtil.isNull(user)){
            throw new CustomException("未有用户信息，请重新登录！");
        }
        if("ROLE_STUDENT".equals(user.getRole())){
            params.setUserId(user.getId());
        }

        // 开启分页查询，下一次查询就会分页
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Audit> list = auditDao.findBySearch(params);
/*        关联查询java形式
        for (Audit audit : list) {
            if (ObjectUtil.isNotEmpty(audit.getUserId())) {
                Admin user = adminDao.selectByPrimaryKey(audit.getUserId());
                if (ObjectUtil.isNotEmpty(user)) {
                    audit.setUserName(user.getName());
                }
            }
        }
        */
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        auditDao.deleteByPrimaryKey(id);
    }


}
