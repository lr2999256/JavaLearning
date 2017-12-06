package com.learn.lambda;

import java.math.BigDecimal;

/**
 * User : Rui
 * Date : 2017/11/8
 * Time : 10:08
 **/
public class Product {
    private int id;

    private String type;

    private BigDecimal amt;

    public Product(){

    }

    public Product(int id, String type, BigDecimal amt) {
        this.id = id;
        this.type = type;
        this.amt = amt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}
