package com.br.controller;


import com.br.common.Result;
import com.br.entity.Log;
import com.br.entity.Params;
import com.br.service.LogService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);


    @Resource
    private LogService logService;


    @PostMapping
    public Result save(@RequestBody Log log){
       logService.add(log);

        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Log> info = logService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        logService.delete(id);
        return Result.success();
    }

}
