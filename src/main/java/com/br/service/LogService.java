package com.br.service;


import com.br.dao.LogDao;
import com.br.dao.TypeDao;
import com.br.entity.Log;
import com.br.entity.Params;
import com.br.entity.Type;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LogService {
    @Resource
    private LogDao logDao;


    public void add(Log log) {
        logDao.insertSelective(log);
    }

    public PageInfo<Log> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Log> list = logDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        logDao.deleteByPrimaryKey(id);
    }


}
