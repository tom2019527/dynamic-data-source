package com.tcg.dynamic.data.source;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.tcg.dynamic.data.source.model.Customer;
import com.tcg.dynamic.data.source.service.ICustomerService;

@SpringBootTest(classes = DynamicDataSourceApplication.class)
public class DynamicDataSourceTest {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceTest.class);
    
    @Autowired
    private ICustomerService service;
    
    @Test
    public void test() {
        Customer customer = service.get("dfstar@123456");
        log.info("customer={}", JSON.toJSONString(customer));
        
        List<Customer> list = service.find("dfstar");
        log.info("list={}", JSON.toJSONString(list));
    }
}
