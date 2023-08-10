# eureka-msa-practice-coupon

회원가입 즉시 웰컴 쿠폰 발행 MSA 구조

--- 
#### 상황 1 (Customer - Coupon)
- JWT 토큰을 적용하지 않고 회원가입 즉시 쿠폰 발행 api 호출

#### 상황 2 (Identity - Coupon)
- JWT 토큰을 Identity 서비스에서 발급
- 게이트웨이에서 토큰 검증
- 쿠폰 서버에서 토큰 해독 후 해당 ID로 쿠폰 발급
