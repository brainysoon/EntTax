package com.enttax.vo;

import org.apache.ibatis.type.Alias;

/**
 * Created by lcyanxi on 17-6-7.
 */
@Alias("BillInfo")
public class BillInfo {
    private String bType;
    private Integer bMonth;
    private Double totalPrice;

    public String getbType() {
        return bType;
    }

    public Integer getbMonth() {
        return bMonth;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "BillInfo{" +
                "bType='" + bType + '\'' +
                ", bMonth=" + bMonth +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
