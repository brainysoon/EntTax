package com.enttax.ml.impl;

import com.enttax.ml.PrepareData;
import com.enttax.model.Bill;
import com.enttax.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by brainy on 17-5-7.
 */
@Component
public class PrepareDataImpl implements PrepareData {

    @Autowired
    private BillService billService;

    @Override
    public int writeDataToDisk(String fileName, int bMark) {

        List<Bill> billList = billService.selectByBMark(bMark);

        try {

            File file = new File(fileName);

            if (!file.exists()) {

                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);

            PrintWriter pw = new PrintWriter(fos);

            for (Bill bill : billList) {

                pw.printf("%.6f\n", bill.getBPrice());
            }

            pw.close();

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return billList.size();
    }

    @Override
    public int writeDataToDisk(int bMark) {

        return writeDataToDisk(DEFAULT_ML_DATA_DIR, bMark);
    }
}
