package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constant.AccountConstant;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@Tag(
        name="CRUD REST APIs for Account microservices",
        description="CRUD REST API in EazyBank to CREATE, UAPDATE, FETCH, AND DELETE account details"
)
@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountService iAccountService;
    @Operation(
            summary="Create Account REST API",
            description="REST API to create new Customer  Account inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstant.STATUS_201,
                AccountConstant.MESSAGE_201));
    }
    @Operation(
            summary="Fetch Account REST API",
            description="REST API to Fetch Customer  Account details inside EazyBank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digit")
                                                               String mobileNumber){
        CustomerDto customerDto = iAccountService.fecthAccounts(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @Operation(
            summary="Fetch Account REST API",
            description="REST API to Fetch Customer  Account details inside EazyBank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isupdated=iAccountService.updateAccount(customerDto);
        if(isupdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200,AccountConstant.STATUS_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstant.STATUS_417,AccountConstant.MESSAGE_417_UPDATE));
        }

    }
    @Operation(
            summary="Fetch Account REST API",
            description="REST API to Fetch Customer  Account details inside EazyBank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digit")
                                                         String mobileNumber){
        boolean isDeleted=false;
        isDeleted=iAccountService.deleteAccounts(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto(AccountConstant.STATUS_200,AccountConstant.STATUS_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstant.STATUS_417,AccountConstant.MESSAGE_417_DELETE));
        }
    }


}
