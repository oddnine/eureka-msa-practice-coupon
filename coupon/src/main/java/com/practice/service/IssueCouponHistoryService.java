package com.practice.service;

import com.practice.domain.IssueCouponHistory;
import com.practice.repository.IssueCouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class IssueCouponHistoryService {

    private final IssueCouponRepository issueCouponRepository;

    public Long issueCoupon(Long customerId) {
        IssueCouponHistory issueCouponHistory = issueCouponRepository.save(
                IssueCouponHistory.builder()
                        .customerId(customerId)
                        .isUsed(false)
                        .createdAt(LocalDateTime.now())
                        .build());

        return issueCouponHistory.getId();
    }
}
