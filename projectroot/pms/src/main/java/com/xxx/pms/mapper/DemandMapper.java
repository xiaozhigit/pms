package com.xxx.pms.mapper;

import com.xxx.pms.entity.Demand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface DemandMapper extends Mapper<Demand> {

    void updateByPrimarykeyBatch(@Param("demandLists") List<Demand> demandLists);
}
