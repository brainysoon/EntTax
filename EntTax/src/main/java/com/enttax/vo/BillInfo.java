package com.enttax.vo;

import org.apache.ibatis.type.Alias;

/**
 * Created by lcyanxi on 17-6-7.
 */
@Alias("BillInfo")
public class BillInfo {
    private String bType;
    //用于月度统计月份
    private Integer bMonth;
    //用于年度统计年份
    private String bYear;
    private Double totalPrice;

    private String bName;

    public String getbType() {
        return bType;
    }

    public Integer getbMonth() {
        return bMonth;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getbYear() {
        return bYear;
    }

    public String getbName() {
        return bName;
    }

    @Override
    public String toString() {
        return "BillInfo{" +
                "bType='" + bType + '\'' +
                ", bMonth=" + bMonth +
                ", bYear='" + bYear + '\'' +
                ", totalPrice=" + totalPrice +
                ", bName='" + bName + '\'' +
                '}';
    }
}
