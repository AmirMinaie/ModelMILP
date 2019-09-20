package com.company;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws IloException {

        Data data = new Data("./InputData.xlsx");
        Module module = new Module(data);
        module.cplex.solve();
        System.out.println("sssssssss");


    }
}


class Example_2 {

    public static void solveMe() {
        int n = 400; //cargos
        int m = 300; //compartments

        double[] p = new double[n];
//                {310.0, 380.0, 350.0, 285.0}; //profit
        double[] v = new double[n];
//                {480.0, 650.0, 580.0, 390.0}; //volume per ton of cargo
        double[] a = new double[n];
//                {18.0, 15.0, 23.0, 12.0}; //available weight

        double[] c = new double[m];
//                {10.0, 16.0, 8.0}; //capacity of compartment
        double[] V = new double[m];
//                = {6800.0, 8700.0, 5300.0}; //volume capacity of
        Random r = new Random();
        for (int ni = 0; ni < n; ni++) {
            v[ni] = r.nextInt(200) + 300;
            p[ni] = r.nextInt(200) + 400;
            a[ni] = r.nextInt(20) + 30;
        }
        for (int mi = 0; mi < m; mi++) {
            c[mi] = r.nextInt(5) + 10;
            V[mi] = r.nextInt(3000) + 4000;
        }
        try {
            // define new model
            IloCplex cplex = new IloCplex();
            // variables
            IloNumVar[][] x = new IloNumVar[n][];
            for (int i = 0; i < n; i++) {
                x[i] = cplex.numVarArray(m, 0, Double.MAX_VALUE);
            }
            IloNumVar y = cplex.numVar(0, Double.MAX_VALUE);
            // expressions
            IloLinearNumExpr[] usedWeightCapacity = new IloLinearNumExpr[m];
            IloLinearNumExpr[] usedVolumeCapacity = new IloLinearNumExpr[m];
            for (int j = 0; j < m; j++) {
                usedWeightCapacity[j] = cplex.linearNumExpr();
                usedVolumeCapacity[j] = cplex.linearNumExpr();
                for (int i = 0; i < n; i++) {
                    usedWeightCapacity[j].addTerm(1.0, x[i][j]);
                    usedVolumeCapacity[j].addTerm(v[i], x[i][j]);
                }
            }
            IloLinearNumExpr objective = cplex.linearNumExpr();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    objective.addTerm(p[i], x[i][j]);
                }
            }
            // define objective
            cplex.addMaximize(objective);
            // constraints
            for (int i = 0; i < n; i++) {
                cplex.addLe(cplex.sum(x[i]), a[i]);
            }
            for (int j = 0; j < m; j++) {
                cplex.addLe(usedWeightCapacity[j], c[j]);
                cplex.addLe(usedVolumeCapacity[j], V[j]);
                cplex.addEq(cplex.prod(1 / c[j], usedWeightCapacity[j]), y);
            }

//            cplex.setParam(IloCplex.Param.Simplex.Display, 0);

            // solve model
            if (cplex.solve()) {
                System.out.println("obj = " + cplex.getObjValue());
            } else {
                System.out.println("problem not solved");
            }

            cplex.end();
        } catch (IloException exc) {
            exc.printStackTrace();
        }
    }

}
