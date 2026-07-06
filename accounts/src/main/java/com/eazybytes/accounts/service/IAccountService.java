package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fecthAccounts(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccounts(String mobileNumber);
}
