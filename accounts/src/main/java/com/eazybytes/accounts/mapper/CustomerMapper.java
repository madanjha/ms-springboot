package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(CustomerDto  customerDto, Customer custoemr) {
        customerDto.setName(customerDto.getName());
        customerDto.setEmail(customerDto.getEmail());
        customerDto.setMobileNumber( customerDto.getMobileNumber());
        return customerDto;
    }

    public static Customer mapCustomer(CustomerDto  customerDto,Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
