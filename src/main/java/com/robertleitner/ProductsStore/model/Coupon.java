package com.robertleitner.ProductsStore.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Coupon")
@NamedQuery(name = "findByCouponCode", query = "SELECT coupon FROM Coupon coupon WHERE coupon.discountCode = :couponCode")
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NonNull
    private String discountCode;

    private int percentage;

    public void setPercentage(int percentage) {

        int per;

        per = discountCode.length();

        this.percentage = per;
    }
}
