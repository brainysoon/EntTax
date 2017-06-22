package com.enttax.service;

import com.enttax.model.Log;

import java.util.List;

/**
 * Created by lcyanxi on 17-6-21.
 */
public interface LogService {

    /**
     * 显示系统日志
     * @return
     */
    List<Log> showLogInfo();
}
