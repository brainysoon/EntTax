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
    public int writeDataToDiskAboutDiff(String fileName) {

        List<Bill> billList = billService.selectByBMark(1);

        try {

            File file = new File(fileName);

            if (!file.exists()) {

                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);

            PrintWriter pw = new PrintWriter(fos);

            for (Bill bill : billList) {

                List<Bill> bills = billService.selectByBName(bill.getBName());

                if (bills.size() < 2) {

                    return -1;
                }

                Bill billIn = bills.get(0);
                Bill billOut = bills.get(1);

                Double diff = billOut.getBPrice() - billIn.getBPrice();


                pw.printf("%.6f %d\n", diff, billIn.getBMonth());
            }

            pw.close();

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return billList.size();
    }

    @Override
    public int writeDataToDiskTaxOutAndMonth(String fileName) {

        List<Bill> billList = billService.selectByBMark(2);

        try {


            File file = new File(fileName);

            if (!file.exists()) {

                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);

            PrintWriter pw = new PrintWriter(fos);

            for (Bill bill : billList) {

                pw.printf("%.6f %d\n", bill.getBPrice(), bill.getBMonth());
            }

            pw.close();

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return billList.size();
    }
}
