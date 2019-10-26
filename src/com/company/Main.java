package com.company;

import ilog.concert.IloException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws IloException, IOException {

        System.setOut(new LogStreamAmir(System.out, new PrintStream("./Data//out.txt")));

        String filePath = "";
        filePath = "./Data//InputData.xlsm";
        Data data = new Data(filePath);
        Module module = new Module(data);
        boolean solve = module.cplex.solve();
        module.SetAmount(solve);
        module.cplex.exportModel("./Data//m.lp");
        System.out.println(String.valueOf(solve));
        if (solve)
            System.out.println(String.valueOf(module.cplex.getObjValue()));

        data.WriteData(module);
        Desktop.getDesktop().open(new File(filePath));

    }

}