package com.enttax.ml;

/**
 * Created by brainy on 17-5-7.
 */
public interface PrepareData {

    String DEFAULT_TAX_IN_FILE_NAME = "/var/ml/tax_in.txt";
    String DEFAULT_TAX_OUT_FILE_NAME = "/var/ml/tax_out.txt";
    String DEFAULT_TAX_DIFF_FILE_NAME = "/var/ml/tax_diff.txt";
    String DEFAULT_TAX_OUT_AND_MONTH_FILE_NAME = "/var/ml/tax_out_month.txt";


    /**
     * @param fileName
     * @param bMark
     * @return
     */
    int writeDataToDisk(String fileName, int bMark);

    /**
     * @param fileName
     * @return
     */
    int writeDataToDiskAboutDiff(String fileName);

    int writeDataToDiskTaxOutAndMonth(String fileName);
}
