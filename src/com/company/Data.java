package com.company;

import MultiMap.HashMapAmir;
import MultiMap.MapAmir;
import ilog.concert.IloNumVar;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Data {

    private String FilePath;

    //region Sets
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
    public int e = 0;
    public int p = 0;

    //endregion

    //region Parameter
    public HashMapAmir<String, Double> FA = new HashMapAmir<String, Double>(4, "FA");
    public HashMapAmir<String, Double> FW = new HashMapAmir<String, Double>(2, "FW");
    public HashMapAmir<String, Double> FD = new HashMapAmir<String, Double>(2, "FD");
    public HashMapAmir<String, Double> FR = new HashMapAmir<String, Double>(2, "FR");
    public HashMapAmir<String, Double> FM = new HashMapAmir<String, Double>(2, "FM");
    public HashMapAmir<String, Double> FQ = new HashMapAmir<String, Double>(2, "FQ");
    public HashMapAmir<String, Double> DS = new HashMapAmir<String, Double>(2, "DS");
    public HashMapAmir<String, Double> C = new HashMapAmir<>(3, "C");
    public HashMapAmir<String, Double> OC = new HashMapAmir<String, Double>(3, "OC");
    public HashMapAmir<String, Double> OCI = new HashMapAmir<String, Double>(2, "OCI");
    public HashMapAmir<String, Double> OCD = new HashMapAmir<String, Double>(2, "OCD");
    public HashMapAmir<String, Double> OCN = new HashMapAmir<String, Double>(2, "OCN");
    public HashMapAmir<String, Double> OCM = new HashMapAmir<String, Double>(2, "OCM");
    public HashMapAmir<String, Double> PS = new HashMapAmir<String, Double>(3, "PS");
    public HashMapAmir<String, Double> PQ = new HashMapAmir<String, Double>(2, "PQ");
    public HashMapAmir<String, Double> PZ = new HashMapAmir<String, Double>(2, "PZ");
    public HashMapAmir<String, Double> PG = new HashMapAmir<String, Double>(2, "PG");
    public HashMapAmir<String, Double> PM = new HashMapAmir<String, Double>(2, "PM");
    public HashMapAmir<String, Double> PB = new HashMapAmir<String, Double>(2, "PB");
    public HashMapAmir<String, Double> ER = new HashMapAmir<String, Double>(2, "ER");
    public HashMapAmir<String, Double> RM = new HashMapAmir<String, Double>(2, "RM");
    public HashMapAmir<String, Double> CA = new HashMapAmir<String, Double>(1, "CA");
    public HashMapAmir<String, Double> CO = new HashMapAmir<String, Double>(3, "CO");
    public HashMapAmir<String, Double> BU = new HashMapAmir<String, Double>(1, "BU");
    public HashMapAmir<String, Double> R = new HashMapAmir<String, Double>(1, "R");
    public HashMapAmir<String, Double> G = new HashMapAmir<String, Double>(2, "G");
    public HashMapAmir<String, Double> RO = new HashMapAmir<>(2, "RO");
    public HashMapAmir<String, Double> L1 = new HashMapAmir<>(1, "L1");
    public HashMapAmir<String, Double> L2 = new HashMapAmir<>(1, "L2");
    public HashMapAmir<String, Double> DE = new HashMapAmir<>(3, "DE");
    public HashMapAmir<String, Double> RE = new HashMapAmir<>(1, "RE");
    public HashMapAmir<String, Double> CU = new HashMapAmir<>(1, "CU");
    public HashMapAmir<String, Double> BE = new HashMapAmir<>(3, "BE");
    public HashMapAmir<String, Double> par = new HashMapAmir<>(1, "par");
    public HashMapAmir<String, Double> vp = new HashMapAmir<>(1, "vp");
    public HashMapAmir<String, Double> wp = new HashMapAmir<>(1, "wp");

    private boolean SaveZero;
    //endregion

    public Data(String FilePath, boolean saveZero) {
        this.SaveZero = saveZero;
        this.FilePath = FilePath;
    }

    public Data(String FilePath) {
        this.SaveZero = false;
        this.FilePath = FilePath;
    }


    public void ReadData() {
        try {

            FileInputStream fis = new FileInputStream(FilePath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheetAt(0);

            for (int l = 1; l <= 20; l++) {
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
                    case "e":
                        e = temp;
                        break;
                    case "p":
                        p = temp;
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
                            case "C":
                                read(C, ii, sheet);
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
                            case "PG":
                                read(PG, ii, sheet);
                                break;
                            case "PM":
                                read(PM, ii, sheet);
                                break;
                            case "PB":
                                read(PB, ii, sheet);
                                break;
                            case "ER":
                                read(ER, ii, sheet);
                                break;
                            case "RM":
                                read(RM, ii, sheet);
                                break;
                            case "CA":
                                read(CA, ii, sheet);
                                break;
                            case "CO":
                                read(CO, ii, sheet);
                                break;
                            case "BU":
                                read(BU, ii, sheet);
                                break;
                            case "R":
                                read(R, ii, sheet);
                                break;
                            case "G":
                                read(G, ii, sheet);
                                break;
                            case "RO":
                                read(RO, ii, sheet);
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
                            case "RE":
                                read(RE, ii, sheet);
                                break;
                            case "BE":
                                read(BE, ii, sheet);
                                break;
                            case "CU":
                                read(CU, ii, sheet);
                                break;
                            case "PA":
                                read(par, ii, sheet);
                                break;
                            case "VP":
                                read(vp, ii, sheet);
                                break;
                            case "WP":
                                read(wp, ii, sheet);
                                break;
                        }
                    }
                }
            }

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void WriteData(Module module) {
        this.SaveZero = par.get("q0").equals(1.0);
        FileInputStream fis = null;
        Workbook wb = null;
        FileOutputStream fos = null;
        try {

            fis = new FileInputStream(FilePath);
            wb = WorkbookFactory.create(fis);
            fis.close();
            Sheet sheet = null;

            try {
                wb.removeSheetAt(wb.getSheetIndex("outPut"));
            } catch (Exception e) {
            }
            sheet = wb.createSheet("outPut");


            writevalibel(sheet, module.WH);
            writevalibel(sheet, module.FF);
            writevalibel(sheet, module.Q);
            writevalibel(sheet, module.DA);
            writevalibel(sheet, module.RF);
            writevalibel(sheet, module.RM);
            writevalibel(sheet, module.TR1);
            writevalibel(sheet, module.TR2);
            writevalibel(sheet, module.TR3);
            writevalibel(sheet, module.X);
            writevalibel(sheet, module.XM);
            writevalibel(sheet, module.Sh);
            writevalibel(sheet, module.Sm);
            writevalibel(sheet, module.Sl);
            writevalibel(sheet, module.Sn);
            writevalibel(sheet, module.NTC);
            writevalibel(sheet, module.ENCT);
            writevalibel(sheet, module.ENC);
            writevalibel(sheet, module.TFC);
            writevalibel(sheet, module.TOC);
            writevalibel(sheet, module.TTC);
            writevalibel(sheet, module.TPC);
            writevalibel(sheet, module.BEN);
            writevalibel(sheet, module.TEN);
            writevalibel(sheet, module.PEN);
            writevalibel(sheet, module.EN);
            writevalibel(sheet, module.FC);
            writevalibel(sheet, module.P);
            writevalibel(sheet, module.At);
            writevalibel(sheet, module.W);


            fos = new FileOutputStream(FilePath);
            wb.write(fos);
            fos.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

            try {
                fos.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            try {
                fos.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }

        }
    }

    private void writevalibel(Sheet sheet, HashMapAmir<String, IloNumVar> ff) {
        int c = 0;
        if (sheet.getRow(1) != null)
            c = sheet.getRow(1).getLastCellNum();

        Row row = sheet.getRow(0);
        if (row == null)
            row = sheet.createRow(0);
        row.createCell(c).setCellValue(ff.getName());

        int r = 1;
        for (MapAmir m : ff.getValus()) {
            if (this.SaveZero) {
                for (int i = 0; i < m.getKey().length; i++) {
                    row = sheet.getRow(r);
                    if (row == null)
                        row = sheet.createRow(r);
                    row.createCell(i + c).setCellValue(m.getKey()[i] + "");
                }
                sheet.getRow(r).createCell(c + m.getKey().length).setCellValue(m.getAmount());

                r++;
            } else {
                if (Math.round(m.getAmount()) != 0) {
                    for (int i = 0; i < m.getKey().length; i++) {
                        row = sheet.getRow(r);
                        if (row == null)
                            row = sheet.createRow(r);
                        row.createCell(i + c).setCellValue(m.getKey()[i] + "");
                    }
                    sheet.getRow(r).createCell(c + m.getKey().length).setCellValue(m.getAmount());

                    r++;
                }
            }
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
            Par.put(sheet.getRow(l).getCell(Par.getNumberKey() + ii), key);

        }
    }
}