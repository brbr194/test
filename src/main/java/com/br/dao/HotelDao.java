package com.br.dao;

import com.br.entity.Hotel;
import com.br.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface HotelDao extends Mapper<Hotel> {


    List<Hotel> findBySearch(@Param("params") Params params);
}
