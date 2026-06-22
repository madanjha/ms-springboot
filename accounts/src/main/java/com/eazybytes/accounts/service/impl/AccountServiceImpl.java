package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constant.AccountConstant;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustoemrAlreadyExistException;
import com.eazybytes.accounts.mapper.CustomerMapper;

import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    /**
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer= CustomerMapper.mapCustomer(customerDto,new Customer());
       Optional<Customer> customerOptional =customerRepository.findByMobileNumber(customerDto.getMobileNumber());
       if(customerOptional.isPresent()){
            throw new CustoemrAlreadyExistException("Customer already exist"+customerDto.getMobileNumber());
       }
        Customer savecustomer=customerRepository.save(customer);
        accountRepository.save(createAccount(savecustomer));
    }

    private Accounts createAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        Long randomAccountno=100000000L+new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountno);
        newAccount.setAccountType(AccountConstant.SAVING);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        return newAccount;
    }
}
