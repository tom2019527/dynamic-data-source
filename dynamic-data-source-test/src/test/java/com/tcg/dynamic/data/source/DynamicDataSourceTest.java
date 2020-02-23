package com.tcg.dynamic.data.source;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.tcg.dynamic.data.source.mapper.MySQLMapper;
import com.tcg.dynamic.data.source.model.Customer;
import com.tcg.dynamic.data.source.model.DB;
import com.tcg.dynamic.data.source.model.SysConfig;
import com.tcg.dynamic.data.source.service.ICustomerService;

@SpringBootTest(classes = DynamicDataSourceApplication.class)
public class DynamicDataSourceTest {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceTest.class);

    @Autowired
    private ICustomerService service;

    @Autowired
    private MySQLMapper mapper;

//    @Test
    public void testOracle() {
        Customer customer = service.get("dfstar@123456");
        log.info("customer={}", JSON.toJSONString(customer));

        List<Customer> list = service.find("dfstar");
        log.info("list={}", JSON.toJSONString(list));
    }

    @Test
    public void testMySQL() {
        List<SysConfig> list1 = mapper.find();
        log.info("list1={}", JSON.toJSONString(list1));

        List<DB> list2 = mapper.get();
        log.info("list2={}", JSON.toJSONString(list2));
    }
}
