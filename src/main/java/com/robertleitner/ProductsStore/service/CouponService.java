package com.robertleitner.ProductsStore.service;

import com.robertleitner.ProductsStore.exception.ProductException;
import com.robertleitner.ProductsStore.model.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CouponService {

    public long addCoupon(Coupon coupon) throws ProductException;

    public Coupon getCouponByCode(String couponCode) throws ProductException;

    public List<Coupon> getCoupons();

    public void updateCoupon(Coupon coupon) throws ProductException;

    public void deleteCouponById(long couponId);

}
