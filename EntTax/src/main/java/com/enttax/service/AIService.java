package com.enttax.service;

import java.util.Map;

/**
 * Created by brainy on 17-6-19.
 */
public interface AIService {

    /**
     * 前几个月
     */
    String PRE_MONTHS = "months";

    String SERIES = "series";

    String LINE = "line";
    String POINT = "point";

    Map doLinearRegByKeyAndCount(String key, Integer count);

    Map getLinearLine(double[][] series);
}
