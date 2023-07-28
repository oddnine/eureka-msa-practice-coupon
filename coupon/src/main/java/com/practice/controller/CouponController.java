package com.practice.controller;

import com.practice.dto.couponhistory.reponse.IssueCouponCheckResponse;
import com.practice.service.IssueCouponHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("coupon/api/v1/coupon")
@Slf4j
public class CouponController {

    private final IssueCouponHistoryService issueCouponHistoryService;

    @PostMapping(path = "/issued-check/{customerId}")
    public IssueCouponCheckResponse isIssued(@PathVariable("customerId") Long customerId) {
        Long issueCouponHistoryId = issueCouponHistoryService.issueCoupon(customerId);
        log.info("쿠폰 발행 {}", issueCouponHistoryId);

        if (issueCouponHistoryId != null)
            return new IssueCouponCheckResponse(true);

        return new IssueCouponCheckResponse(false);
    }
}
