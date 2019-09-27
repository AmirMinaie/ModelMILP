package com.company;

import ilog.concert.IloException;

public class Main {

    public static void main(String[] args) throws IloException {

        Data data = new Data("./Data//InputData.xlsm");
        Module module = new Module(data);
        module.cplex.solve();
        module.cplex.exportModel("m.lp");


    }
}