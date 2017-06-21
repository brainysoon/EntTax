package com.enttax.service.impl;

import com.enttax.dao.LogMapper;
import com.enttax.model.Log;
import com.enttax.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcyanxi on 17-6-21.
 */
@Service
public class LogServiceImpl implements LogService {


    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> showLogInfo() {
        return logMapper.selectAll();
    }
}
