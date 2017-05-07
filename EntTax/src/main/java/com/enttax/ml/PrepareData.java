package com.enttax.ml;

/**
 * Created by brainy on 17-5-7.
 */
public interface PrepareData {

    /**
     * 默认数据存放位置
     */
    String DEFAULT_ML_DATA_DIR = "/var/ml/default.txt";

    /**
     * @param fileName
     * @param bMark
     * @return
     */
    int writeDataToDisk(String fileName, int bMark);

    /**
     * @param bMark
     * @return
     */
    int writeDataToDisk(int bMark);
}
