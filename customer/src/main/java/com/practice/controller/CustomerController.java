package com.practice.controller;

import com.practice.dto.customer.request.CustomerRegistrationRequest;
import com.practice.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("customer/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 회원가입 동시에 쿠폰 서버에서 웰컴 쿠폰 발행
    @PostMapping
    public String register(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("고객 회원가입 요청: {}", customerRegistrationRequest);
        Long customerId = customerService.register(customerRegistrationRequest);
        return customerService.issueWelcomeCoupon(customerId);
    }
}
