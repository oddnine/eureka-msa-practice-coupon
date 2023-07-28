package com.practice.dto.couponhistory.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueCouponCheckResponse {
    private Boolean isIssued;
}
