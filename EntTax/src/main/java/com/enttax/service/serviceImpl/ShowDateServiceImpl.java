package com.enttax.service.serviceImpl;

import com.enttax.dao.BillMapper;
import com.enttax.model.Bill;
import com.enttax.service.ShowDateService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcyanxi on 17-4-7.
 */
@Service
public class ShowDateServiceImpl implements ShowDateService {
    @Autowired
    private BillMapper billMapper;

    public List<Bill> select(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Bill> bills= billMapper.selectAll();
        return bills;
    }
}
