package com.br.service;


import com.br.dao.TypeDao;
import com.br.entity.Admin;
import com.br.entity.Params;
import com.br.entity.Type;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TypeService {
    @Resource
    private TypeDao typeDao;


    public void add(Type type) {
        typeDao.insertSelective(type);
    }

    public void update(Type type) {
        typeDao.updateByPrimaryKeySelective(type);
    }


    public PageInfo<Type> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Type> list = typeDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        typeDao.deleteByPrimaryKey(id);
    }

    public List<Type> findAll(){
        return typeDao.selectAll();
    }
}
