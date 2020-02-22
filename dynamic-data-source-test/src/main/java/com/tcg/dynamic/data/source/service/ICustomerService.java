package com.tcg.dynamic.data.source.service;

import java.util.List;

import com.tcg.dynamic.data.source.model.Customer;

public interface ICustomerService {

    List<Customer> find(String merchantCode);

    Customer get(String customerName);
}
