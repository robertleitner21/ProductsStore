package com.robertleitner.ProductsStore.repository;

import com.robertleitner.ProductsStore.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

}
