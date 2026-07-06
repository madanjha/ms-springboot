package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts,Long> {
    Optional<Accounts> findByCustomerId(Long  customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
