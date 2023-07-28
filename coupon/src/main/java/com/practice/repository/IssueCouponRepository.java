package com.practice.repository;

import com.practice.domain.IssueCouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCouponRepository extends JpaRepository<IssueCouponHistory, Long> {
}
