package com.enttax.service.showDateService;

import com.enttax.model.Bill;

import java.util.List;

/**
 * Created by lcyanxi on 17-4-7.
 */
public interface ShowDateService {
    List<Bill> select(int pageNum,int pageSize);
}
