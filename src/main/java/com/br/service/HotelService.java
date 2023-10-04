package com.br.service;

import cn.hutool.core.collection.CollectionUtil;
import com.br.dao.HotelDao;
import com.br.entity.Hotel;
import com.br.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class HotelService {
    @Resource
    private HotelDao hotelDao;


    public PageInfo<Hotel> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Hotel> list = hotelDao.findBySearch(params);
        if(CollectionUtil.isEmpty(list)){
            return PageInfo.of(new ArrayList<>());
        }
        return PageInfo.of(list);

    }

    public void add(Hotel hotel) {
        hotelDao.insertSelective(hotel);
    }

    public void update(Hotel hotel) {
        hotelDao.updateByPrimaryKeySelective(hotel);
    }

    public void delete(Integer id) {
        hotelDao.deleteByPrimaryKey(id);
    }
}
