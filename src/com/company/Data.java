package com.company;

import MultiMap.HashMapAmir;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Data {


    private final String FilePath;

    public int s = 0;
    public int f = 0;
    public int q = 0;
    public int d = 0;
    public int n = 0;
    public int m = 0;
    public int i = 0;
    public int z = 0;
    public int j = 0;
    public int ho = 0;
    public int hi = 0;
    public int hd = 0;
    public int hn = 0;
    public int hm = 0;
    public int hq = 0;
    public int t = 0;
    public int o = 0;
    public int k = 0;

    public HashMapAmir<String, Double> FA = new HashMapAmir<String, Double>(4, "FA");
    public HashMapAmir<String, Double> FW = new HashMapAmir<String, Double>(3, "FW");
    public HashMapAmir<String, Double> FD = new HashMapAmir<String, Double>(3, "FD");
    public HashMapAmir<String, Double> FR = new HashMapAmir<String, Double>(3, "FR");
    public HashMapAmir<String, Double> FM = new HashMapAmir<String, Double>(3, "FM");
    public HashMapAmir<String, Double> FQ = new HashMapAmir<String, Double>(3, "FQ");
    public HashMapAmir<String, Double> DS = new HashMapAmir<String, Double>(2, "DS");
    public HashMapAmir<String, Double> c = new HashMapAmir<String, Double>(2, "c");
    public HashMapAmir<String, Double> cj = new HashMapAmir<String, Double>(2, "cj");
    public HashMapAmir<String, Double> OC = new HashMapAmir<String, Double>(1, "OC");
    public HashMapAmir<String, Double> OCI = new HashMapAmir<String, Double>(1, "OCI");
    public HashMapAmir<String, Double> OCD = new HashMapAmir<String, Double>(1, "OCD");
    public HashMapAmir<String, Double> OCN = new HashMapAmir<String, Double>(1, "OCN");
    public HashMapAmir<String, Double> OCM = new HashMapAmir<String, Double>(1, "OCM");
    public HashMapAmir<String, Double> PS = new HashMapAmir<String, Double>(1, "PS");
    public HashMapAmir<String, Double> PQ = new HashMapAmir<String, Double>(1, "PQ");
    public HashMapAmir<String, Double> PZ = new HashMapAmir<String, Double>(1, "PZ");
    public HashMapAmir<String, Double> PZ1 = new HashMapAmir<String, Double>(1, "PZ1");
    public HashMapAmir<String, Double> PZ2 = new HashMapAmir<String, Double>(1, "PZ2");
    public HashMapAmir<String, Double> PZ3 = new HashMapAmir<String, Double>(2, "PZ3");
    public HashMapAmir<String, Double> A = new HashMapAmir<String, Double>(1, "A");
    public HashMapAmir<String, Double> RM = new HashMapAmir<String, Double>(1, "RM");
    public HashMapAmir<String, Double> CA = new HashMapAmir<String, Double>(1, "CA");
    public HashMapAmir<String, Double> Cai = new HashMapAmir<String, Double>(1, "Cai");
    public HashMapAmir<String, Double> CAD = new HashMapAmir<String, Double>(1, "CAD");
    public HashMapAmir<String, Double> CAN = new HashMapAmir<String, Double>(1, "CAN");
    public HashMapAmir<String, Double> CAM = new HashMapAmir<String, Double>(1, "CAM");
    public HashMapAmir<String, Double> CAQ = new HashMapAmir<String, Double>(1, "CAQ");
    public HashMapAmir<String, Double> BU = new HashMapAmir<String, Double>(1, "BU");
    public HashMapAmir<String, Double> r = new HashMapAmir<String, Double>(1, "r");
    public HashMapAmir<String, Double> G1 = new HashMapAmir<String, Double>(1, "G1");
    public HashMapAmir<String, Double> G2 = new HashMapAmir<>(1, "G2");
    public HashMapAmir<String, Double> L1 = new HashMapAmir<>(1, "L1");
    public HashMapAmir<String, Double> L2 = new HashMapAmir<>(1, "L2");
    public HashMapAmir<String, Double> DE = new HashMapAmir<>(2, "DE");

    public Data(String FilePath) {
        this.FilePath = FilePath;
    }

    public void ReadData() {
        try {

            FileInputStream fis = new FileInputStream(FilePath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheetAt(0);

            for (int l = 1; l <= sheet.getLastRowNum(); l++) {
                int temp = (int) sheet.getRow(l).getCell(2).getNumericCellValue();
                switch (sheet.getRow(l).getCell(1).getStringCellValue().toLowerCase()) {

                    case "s":
                        s = temp;
                        break;
                    case "f":
                        f = temp;
                        break;
                    case "q":
                        q = temp;
                        break;
                    case "d":
                        d = temp;
                        break;
                    case "n":
                        n = temp;
                        break;
                    case "m":
                        m = temp;
                        break;
                    case "i":
                        i = temp;
                        break;
                    case "z":
                        z = temp;
                        break;
                    case "j":
                        j = temp;
                        break;
                    case "ho":
                        ho = temp;
                        break;
                    case "hi":
                        hi = temp;
                        break;
                    case "hd":
                        hd = temp;
                        break;
                    case "hn":
                        hn = temp;
                        break;
                    case "hm":
                        hm = temp;
                        break;
                    case "hq":
                        hq = temp;
                        break;
                    case "t":
                        t = temp;
                        break;
                    case "o":
                        o = temp;
                        break;
                    case "k":
                        k = temp;
                        break;
                }
            }

            sheet = wb.getSheetAt(1);

            int numberCoulemn = sheet.getRow(0).getLastCellNum();
            for (int ii = 0; ii < numberCoulemn; ii++) {
                if (sheet.getRow(0).getCell(ii) != null) {
                    String temp = sheet.getRow(0).getCell(ii).toString();
                    if (temp != "") {
                        switch (temp.toUpperCase()) {
                            case "FA":
                                read(FA, ii, sheet);
                                break;
                            case "FW":
                                read(FW, ii, sheet);
                                break;
                            case "FD":
                                read(FD, ii, sheet);
                                break;
                            case "FR":
                                read(FR, ii, sheet);
                                break;
                            case "FM":
                                read(FM, ii, sheet);
                                break;
                            case "FQ":
                                read(FQ, ii, sheet);
                                break;
                            case "DS":
                                read(DS, ii, sheet);
                                break;
                            case "c":
                                read(c, ii, sheet);
                                break;
                            case "OC":
                                read(OC, ii, sheet);
                                break;
                            case "OCI":
                                read(OCI, ii, sheet);
                                break;
                            case "OCD":
                                read(OCD, ii, sheet);
                                break;
                            case "OCN":
                                read(OCN, ii, sheet);
                                break;
                            case "OCM":
                                read(OCM, ii, sheet);
                                break;
                            case "PS":
                                read(PS, ii, sheet);
                                break;
                            case "PQ":
                                read(PQ, ii, sheet);
                                break;
                            case "PZ":
                                read(PZ, ii, sheet);
                                break;
                            case "PZ1":
                                read(PZ1, ii, sheet);
                                break;
                            case "PZ2":
                                read(PZ2, ii, sheet);
                                break;
                            case "PZ3":
                                read(PZ3, ii, sheet);
                                break;
                            case "A":
                                read(A, ii, sheet);
                                break;
                            case "RM":
                                read(RM, ii, sheet);
                                break;
                            case "CA":
                                read(CA, ii, sheet);
                                break;
                            case "Cai":
                                read(Cai, ii, sheet);
                                break;
                            case "CAD":
                                read(CAD, ii, sheet);
                                break;
                            case "CAN":
                                read(CAN, ii, sheet);
                                break;
                            case "CAM":
                                read(CAM, ii, sheet);
                                break;
                            case "CAQ":
                                read(CAQ, ii, sheet);
                                break;
                            case "BU":
                                read(BU, ii, sheet);
                                break;
                            case "r":
                                read(r, ii, sheet);
                                break;
                            case "G1":
                                read(G1, ii, sheet);
                                break;
                            case "G2":
                                read(G2, ii, sheet);
                                break;
                            case "L1":
                                read(L1, ii, sheet);
                                break;
                            case "L2":
                                read(L2, ii, sheet);
                                break;
                            case "DE":
                                read(DE, ii, sheet);
                                break;
                            case "CJ":
                                read(cj, ii, sheet);
                                break;

                        }
                    }
                }
            }

            int qq = 1;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void read(HashMapAmir Par, int ii, Sheet sheet) {
        int lastRow = sheet.getLastRowNum();
        for (int l = 2; l <= lastRow; l++) {

            if (sheet.getRow(l).getCell(Par.getNumberKey() + ii) == null || sheet.getRow(l).getCell(Par.getNumberKey() + ii).toString() == "")
                break;

            String[] key = new String[Par.getNumberKey()];
            for (int pp = 0; pp < key.length; pp++) {
                key[pp] = String.format(sheet.getRow(1).getCell(ii + pp).toString() + (int) sheet.getRow(l).getCell(ii + pp).getNumericCellValue() + "");
            }
            Par.put(sheet.getRow(l).getCell(Par.getNumberKey() + ii).getNumericCellValue(), key);
        }
        int hh = 00;
    }
}