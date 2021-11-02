package com.robertleitner.ProductsStore.controller;

import com.robertleitner.ProductsStore.exception.ProductException;
import com.robertleitner.ProductsStore.model.Coupon;
import com.robertleitner.ProductsStore.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping(value = "/coupon")
    @ResponseBody
    public long addCoupon(@RequestBody Coupon coupon) throws ProductException {
        return couponService.addCoupon(coupon);
    }

    @GetMapping(value = "/getCoupon/{couponCode}")
    @ResponseBody
    public Coupon receiveCouponByCode(@PathVariable("couponCode") String couponCode) throws ProductException {
        return couponService.getCouponByCode(couponCode);
    }

    @GetMapping(value = "/coupon/all")
    @ResponseBody
    public List<Coupon> receiveCoupons() {
        return couponService.getCoupons();
    }

    @PutMapping(value = "/updateCoupon")
    @ResponseBody
    public void updateCoupon(@RequestBody Coupon coupon) throws ProductException {
        couponService.updateCoupon(coupon);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteCouponById(@PathVariable("couponId") long couponId) {
        couponService.deleteCouponById(couponId);
    }
}
