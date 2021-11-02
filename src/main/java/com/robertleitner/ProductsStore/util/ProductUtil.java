package com.robertleitner.ProductsStore.util;

public class ProductUtil {

    private final static String EMPTY = "";

    private ProductUtil() {

    }

    public static boolean isNotEmpty(String value) {
        if (null != value && !EMPTY.equals(value))
            return true;
        return false;
    }

    public static boolean isEmpty(String value) {
        if (null == value || EMPTY.equals(value))
            return true;
        return false;
    }
}
