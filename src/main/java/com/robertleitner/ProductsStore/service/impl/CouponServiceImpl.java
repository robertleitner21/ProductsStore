package com.robertleitner.ProductsStore.service.impl;

import static com.robertleitner.ProductsStore.util.ProductUtil.isEmpty;

import com.robertleitner.ProductsStore.exception.ProductException;
import com.robertleitner.ProductsStore.model.Coupon;
import com.robertleitner.ProductsStore.repository.CouponRepo;
import com.robertleitner.ProductsStore.service.CouponService;
import com.robertleitner.ProductsStore.util.ProductUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private static Logger LOGGER = LoggerFactory.getLogger(CouponService.class);

    @Autowired
    private CouponRepo couponRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public long addCoupon(Coupon coupon) throws ProductException {
        if (coupon != null && isValid(coupon) && isNotExisting(coupon)) {

            return couponRepo.save(coupon).getId();
        }
        return -1;
    }

    @Override
    public Coupon getCouponByCode(String couponCode) throws ProductException {

        if (isEmpty(couponCode))
            return null;
        TypedQuery<Coupon> namedQuery = entityManager.createNamedQuery("findByCouponCode", Coupon.class);
        Coupon coupon = null;
        try {
            coupon = namedQuery.setParameter("couponCode", couponCode).getSingleResult();
        } catch (NoResultException e) {
            throw new ProductException("[" + couponCode + "]" + e.getMessage());
        } finally {
            entityManager.close();
        }
        return buildCoupon(coupon);
    }

    @Override
    public List<Coupon> getCoupons() {
        return buildCouponList(couponRepo.findAll());
    }

    @Override
    public void updateCoupon(Coupon coupon) throws ProductException {
        Coupon c = getCouponByCode(coupon.getDiscountCode());
        if (c != null) {
            coupon.setId(c.getId());
            couponRepo.save(coupon);
        }
    }

    @Override
    public void deleteCouponById(long couponId) {
        couponRepo.deleteById(couponId);
    }

    private boolean isValid(Coupon coupon) throws ProductException {
        if (ProductUtil.isEmpty(coupon.getDiscountCode()))
            throw new ProductException("coupon code cannot be empty");
        return true;
    }

    private boolean isNotExisting(Coupon coupon) throws ProductException {
        Coupon c = null;
        try {
            c = getCouponByCode(coupon.getDiscountCode());
        } catch (ProductException e) {
            LOGGER.info(e.getMessage());
        }
        if (c != null && c.equals(coupon)) {
            throw new ProductException("Coupon cannot be added as the coupon already exists");
        }
        return true;
    }

    private Coupon buildCoupon(Coupon c) {
        Coupon coupon = null;
        if (null != c) {
            coupon = new Coupon();
            coupon.setDiscountCode(c.getDiscountCode());
            coupon.setId(c.getId());
            coupon.setPercentage(c.getPercentage());
        }
        return coupon;
    }

    private List<Coupon> buildCouponList(Iterable<Coupon> coupons) {
        List<Coupon> couponList = null;
        if (null != coupons) {
            couponList = new ArrayList<>();
            for (Coupon c : coupons) {
                couponList.add(buildCoupon(c));
            }
        }
        return couponList;
    }
}
