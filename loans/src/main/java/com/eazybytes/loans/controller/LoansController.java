package com.eazybytes.loans.controller;

import com.eazybytes.loans.dto.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoansController {

    public ResponseEntity<ResponseDto> createLoans(){
        return null;
    }
}
