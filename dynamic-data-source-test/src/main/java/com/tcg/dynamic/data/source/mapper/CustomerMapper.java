package com.tcg.dynamic.data.source.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.tcg.dynamic.data.source.annotation.DataSourceType;
import com.tcg.dynamic.data.source.model.Customer;

@Repository
public interface CustomerMapper {
    @DataSourceType("slave")
    @Select("select customer_id, customer_name from us_customer where customer_name like '${merchantCode}%' order by create_time desc fetch first 10 rows only")
    List<Customer> find(@Param("merchantCode")String merchantCode);

    @DataSourceType("master")
    @Select("select customer_id, customer_name from us_customer where customer_name = #{customerName}")
    Customer get(@Param("customerName") String customerName);
}
