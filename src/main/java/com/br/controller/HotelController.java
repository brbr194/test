package com.br.controller;

import com.br.common.Result;
import com.br.entity.Hotel;
import com.br.entity.Params;
import com.br.service.HotelService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@CrossOrigin
@RestController
@RequestMapping("/hotel")
public class HotelController {

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @Resource
    private HotelService hotelService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        log.info("拦截器已放行，开始调用");
        PageInfo<Hotel> info = hotelService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Hotel hotel){
        if(hotel.getId() == null){
            hotelService.add(hotel);
        }else{
            hotelService.update(hotel);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        hotelService.delete(id);
        return Result.success();
    }

}
