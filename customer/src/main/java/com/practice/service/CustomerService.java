package com.practice.service;

import com.practice.dto.customer.request.CustomerRegistrationRequest;
import com.practice.dto.coupon.response.IssueCouponCheckResponse;
import com.practice.domain.Customer;
import com.practice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final WebClient.Builder webClientBuilder;
    private final static String GATEWAY_URL = "http://localhost:8000";

    public Long register(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .build();

        customerRepository.save(customer);

        return customer.getId();
    }

    public String issueWelcomeCoupon(Long customerId) {
        IssueCouponCheckResponse issueCouponCheckResponse = webClientBuilder
                .build()
                .post()
                .uri(GATEWAY_URL + "/coupon/api/v1/coupon/issued-check/{customerId}", customerId)
                .retrieve()
                .bodyToMono(IssueCouponCheckResponse.class)
                .block();

        if (issueCouponCheckResponse.getIsIssued())
            return "쿠폰이 발행되었습니다.";
        else
            return "쿠폰이 발행되지 않았습니다.";
    }
}
