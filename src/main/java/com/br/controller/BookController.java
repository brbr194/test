package com.br.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.br.common.AutoLog;
import com.br.common.Result;
import com.br.entity.Admin;
import com.br.entity.Book;
import com.br.entity.Params;
import com.br.service.BookService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Resource
    private BookService bookService;

    @GetMapping("/search")
    @AutoLog("查询图书信息")
    public Result findBySearch(Params params) {
        PageInfo<Book> info = bookService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    @AutoLog("更新图书信息")
    public Result save(@RequestBody Book book){
        if(book.getId() == null){
            bookService.add(book);
        }else{
            bookService.update(book);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @AutoLog("删除图书信息")
    public Result delete(@PathVariable Integer id){
        bookService.delete(id);
        return Result.success();
    }

    @GetMapping("/echarts/bie")
    public Result bie(){
        // 查询出所有图书
        List<Book> list = bookService.findAll();
        Map<String, Long> collect = list.stream()
                .filter(x -> ObjectUtil.isNotNull(x.getTypeName()))
                .collect(Collectors.groupingBy(Book::getTypeName, Collectors.counting()));
        //最后返回的数据结构
        List<Map<String, Object>> mapList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(collect)){
            for(String key : collect.keySet()){
                Map<String, Object> map = new HashMap<>();
                map.put("name", key);
                map.put("value",collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

    @GetMapping("/echarts/bar")
    public Result bar(){
        // 查询出所有图书
        List<Book> list = bookService.findAll();
        Map<String, Long> collect = list.stream()
                .filter(x -> ObjectUtil.isNotNull(x.getTypeName()))
                .collect(Collectors.groupingBy(Book::getTypeName, Collectors.counting()));
        //最后返回的数据结构
        List<String> xAxis = new ArrayList<>();
        List<Long> yAxis = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(collect)){
            for(String key : collect.keySet()){
                xAxis.add(key);
                yAxis.add(collect.get(key));
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("xAxis", xAxis);
        map.put("yAxis", yAxis);
        return Result.success(map);
    }
}
