package com.enttax.service;

import com.enttax.model.Bill;
import org.apache.shiro.session.Session;

import java.io.InputStream;
import java.util.List;

/**
 * Created by brainy on 17-5-17.
 */
public interface ExcelService {
    /**
     * 进销项数据
     */
    String TAX_IN = "进项数据";
    String TAX_OUT = "销项数据";

    /**
     * @param bMark
     * @param sheetAt
     * @param is
     * @param extName
     * @return
     * @throws Exception
     */
    List<Bill> readExcelFromInputStream(int bMark, int sheetAt, InputStream is, String extName) throws Exception;

    /**
     * @param key
     * @param bills
     * @return
     */
    long pushExcelToCache(String key, List<Bill> bills);

    /**
     * @param key
     * @return
     */
    List<Bill> readExcelFromRedis(String key);

    int moveCacheToDataBase(String key, Session session);

    /**
     * 显示进销项数据
     *
     * @return
     */
    List<Bill> showData();

    /**
     * @param key
     * @param size
     * @return
     */
    long pushRecordToRedis(String key, Integer size);

    /**
     * @param key
     * @return
     */
    Integer readRecordFromRedis(String key);
}
