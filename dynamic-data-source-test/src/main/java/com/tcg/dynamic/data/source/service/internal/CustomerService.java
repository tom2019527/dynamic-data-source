package com.tcg.dynamic.data.source.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcg.dynamic.data.source.mapper.CustomerMapper;
import com.tcg.dynamic.data.source.model.Customer;
import com.tcg.dynamic.data.source.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Override
    // @DataSourceType("slave")
    public List<Customer> find(String merchantCode) {
        return mapper.find(merchantCode);
    }

    @Override
    // @DataSourceType("master")
    public Customer get(String customerName) {
        return mapper.get(customerName);
    }
}
