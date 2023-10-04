package com.br.controller;

import cn.hutool.core.date.DateUtil;
import com.br.common.Result;
import com.br.dao.HotelDao;
import com.br.entity.Hotel;
import com.br.entity.Reserve;
import com.br.entity.Params;
import com.br.service.ReserveService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@CrossOrigin
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private static final Logger log = LoggerFactory.getLogger(ReserveController.class);

    @Resource
    private ReserveService reserveService;
    @Resource
    private HotelDao hotelDao;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Reserve> info = reserveService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Reserve reserve){
        // 1. 酒店剩余房间是否为0，大于0的时候，才可以被预定
        Hotel hotel = hotelDao.selectByPrimaryKey(reserve.getHotelId());
        if(hotel.getNum() == 0){
            return Result.error("酒店该房间以预定完，请预定其他房间");
        }
        reserve.setTime(DateUtil.now());
        // 2. 往预定表里插入一条预定记录
        reserveService.add(reserve);
        // 3. 对应的酒店房间剩余数量减一
        hotel.setNum(hotel.getNum() - 1);
        hotelDao.updateByPrimaryKeySelective(hotel);


        return Result.success();
    }

    @PostMapping("/back")
    public Result back(@RequestBody Reserve reserve){
        //删除预定信息
        reserveService.delete(reserve.getId());
        //房间数量加一
        Hotel hotel = hotelDao.selectByPrimaryKey(reserve.getHotelId());
        hotel.setNum(hotel.getNum() + 1);
        hotelDao.updateByPrimaryKeySelective(hotel);

        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        reserveService.delete(id);
        return Result.success();
    }

}
