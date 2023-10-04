package com.br.dao;

import com.br.entity.Log;
import com.br.entity.Params;
import com.br.entity.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LogDao extends Mapper<Log> {

    List<Log> findBySearch(@Param("params") Params params);
}
