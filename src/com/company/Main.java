package com.company;

import ilog.concert.IloException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws IloException, IOException {

        PrintStream printStream = new PrintStream("./Data//out.txt");
        LogStreamAmir logStreamAmir = new LogStreamAmir(System.out, printStream);
        System.setOut(logStreamAmir);
        System.setErr(logStreamAmir);

        String filePath = "";
        filePath = "./Data//InputData.xlsm";
        Data data = new Data(filePath);
        Module module = new Module(data);

        module.cplex.exportModel("./Data//m.lp");
        boolean solve = module.cplex.solve();

        module.SetAmount(solve);
        System.out.println(String.valueOf(solve));
        if (solve)
            System.out.println(String.valueOf(module.cplex.getObjValue()));

        data.WriteData(module);
        printStream.toString();
        int dialogResult = JOptionPane.showConfirmDialog(null, String.valueOf(solve) +"\n" , "Warning", JOptionPane.YES_NO_OPTION);

        if (dialogResult == 0) {
            Desktop.getDesktop().open(new File(filePath));
        }

    }

}