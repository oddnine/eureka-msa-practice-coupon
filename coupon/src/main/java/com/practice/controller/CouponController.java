package com.practice.controller;

import com.practice.dto.couponhistory.reponse.IssueCouponCheckResponse;
import com.practice.service.IssueCouponHistoryService;
import com.practice.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("coupon/api/v1/coupon")
@Slf4j
public class CouponController {

    private final IssueCouponHistoryService issueCouponHistoryService;

    private JwtUtil jwtUtil;

    // 필터 적용 X
    @PostMapping(path = "/issued-check/{customerId}")
    public IssueCouponCheckResponse isIssued(@PathVariable("customerId") Long customerId) {
        Long issueCouponHistoryId = issueCouponHistoryService.issueCoupon(customerId);
        log.info("쿠폰 발행 {}", issueCouponHistoryId);

        if (issueCouponHistoryId != null)
            return new IssueCouponCheckResponse(true);

        return new IssueCouponCheckResponse(false);
    }

    // 필터 적용 O
    @PostMapping(path = "/issue")
    public IssueCouponCheckResponse issueCoupon(@RequestHeader(name = "Authorization") String token) {
        System.out.println(token);
        String usernameFromToken = jwtUtil.getUsernameFromToken(token);
        System.out.println(usernameFromToken);
        Long issueCouponHistoryId = issueCouponHistoryService.issueCoupon(1L);
        log.info("쿠폰 발행 {}", issueCouponHistoryId);

        if (issueCouponHistoryId != null)
            return new IssueCouponCheckResponse(true);

        return new IssueCouponCheckResponse(false);
    }
}
