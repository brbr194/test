package com.br.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.br.common.Result;
import com.br.dao.NoticeDao;
import com.br.entity.Admin;
import com.br.entity.Notice;
import com.br.entity.Params;
import com.br.entity.Type;
import com.br.exception.CustomException;
import com.br.service.NoticeService;
import com.br.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {

   @Resource
    private NoticeService noticeService;

   @PostMapping
    public Result update(@RequestBody Notice notice){
       if(ObjectUtil.isEmpty(notice.getId())) {
           noticeService.add(notice);
       }else {
           noticeService.update(notice);
       }
        return Result.success();
   }
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Notice> info = noticeService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
       noticeService.delete(id);
       return Result.success();
    }

    @GetMapping
    public Result findTop(){
       List<Notice> list =  noticeService.findTop();
       return Result.success(list);
    }

}
