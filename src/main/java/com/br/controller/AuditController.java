package com.br.controller;

import com.br.common.Result;
import com.br.entity.Audit;
import com.br.entity.Params;
import com.br.service.AuditService;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/audit")
public class AuditController {

    @Resource
    private AuditService auditService;

    @PostMapping
    public Result save(@RequestBody Audit audit) {
        if (audit.getId() == null) {
            auditService.add(audit);
        } else {
            auditService.update(audit);
        }
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Audit> info = auditService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        auditService.delete(id);
        return Result.success();
    }

}