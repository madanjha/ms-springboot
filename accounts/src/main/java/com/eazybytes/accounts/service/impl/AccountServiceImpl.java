package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constant.AccountConstant;
import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustoemrAlreadyExistException;
import com.eazybytes.accounts.exception.ResourceNoFoundException;
import com.eazybytes.accounts.mapper.AccountMapper;
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
        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
       Optional<Customer> customerOptional =customerRepository.findByMobileNumber(customerDto.getMobileNumber());
       if(customerOptional.isPresent()){
            throw new CustoemrAlreadyExistException("Customer already exist"+customerDto.getMobileNumber());
       }
        Customer savecustomer=customerRepository.save(customer);
        accountRepository.save(createAccount(savecustomer));
    }

    @Override
    public CustomerDto fecthAccounts(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->
                   new ResourceNoFoundException("Customer", "MobileNumber", mobileNumber)
                );
        Accounts accounts=accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->
                     new ResourceNoFoundException("Account", "CustomerId", customer.getCustomerId().toString())
                );
        CustomerDto customerresponse=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerresponse.setAccountDto(AccountMapper.mapToAccountDto(accounts,new  AccountDto()));
        return customerresponse;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated=false;
        AccountDto accountDto=customerDto.getAccountDto();
        if(accountDto != null){
            Accounts accounts=accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    ()->  new ResourceNoFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString()));
            AccountMapper.mapToAccount(accountDto,accounts);
            accounts=accountRepository.save(accounts);
            Long customerId=accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNoFoundException("Customer", "customerId", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated=true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccounts(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNoFoundException("Customer", "MobileNumber", mobileNumber));

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
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
