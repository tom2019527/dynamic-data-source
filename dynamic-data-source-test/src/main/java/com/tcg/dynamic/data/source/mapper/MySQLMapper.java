package com.tcg.dynamic.data.source.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tcg.dynamic.data.source.annotation.DataSourceType;
import com.tcg.dynamic.data.source.model.DB;
import com.tcg.dynamic.data.source.model.SysConfig;

@Repository
public interface MySQLMapper {
    @DataSourceType("master")
    @Select("select * from sys_config")
    List<SysConfig> find();

    @DataSourceType("slave")
    @Select("select db from db")
    List<DB> get();
}
