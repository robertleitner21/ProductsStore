package com.robertleitner.ProductsStore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertleitner.ProductsStore.model.Coupon;
import com.robertleitner.ProductsStore.model.Product;
import com.robertleitner.ProductsStore.service.CouponService;
import com.robertleitner.ProductsStore.service.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductControllerTest.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    CouponService couponService;

    @MockBean
    CouponController target;

    List<Coupon> couponList;

    Coupon coupon;


    @Before
    public void setUp() throws Exception {
        coupon = new Coupon();
        coupon.setId(1);
        coupon.setDiscountCode("598124322");

        couponList = new ArrayList<>();
        couponList.add(coupon);

    }

    @Test
    public void testGetAllCoupons() throws Exception {
        given(target.receiveCoupons()).willReturn(couponList);

        mockMvc.perform(get("/v1/getCoupon/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }



}