package com.learn.lambda;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User : Rui
 * Date : 2017/11/8
 * Time : 10:08
 **/
public class LambdaProduct {
    private static List<Product> products = Arrays.asList(
            new Product(1, "10", BigDecimal.valueOf(9.01)),
            new Product(2, "10", BigDecimal.valueOf(8.01)),
            new Product(3, "20", BigDecimal.valueOf(19.01)),
            new Product(4, "20", BigDecimal.valueOf(18.01)),
            new Product(5, "30", BigDecimal.valueOf(9.01)),
            new Product(6, "40", BigDecimal.valueOf(9.01)));

    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        products.sort(Comparator.comparing(Product::getAmt));
        Map<String, List<Product>> map1 = products.stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getType,
                                Collectors.toList()));
        for (Map.Entry<String, List<Product>> entry : map1.entrySet()) {
            list.add(entry.getValue().get(0));
        }
        System.out.print("abc");
//        List<Product> list = products.stream().collect(Collectors.groupingBy(
//                Product::getType
//        ));
    }
}
