package com.company;

import MultiMap.HashMapAmir;
import ilog.concert.*;
import ilog.cplex.*;

public class Module {


    private Data data;
    public IloIntVar[][][] WH;
    public IloIntVar[][][][] FF;
    public IloIntVar[][][] Q;
    public IloIntVar[][][] DA;
    public IloIntVar[][][] RF;
    public IloIntVar[][][] RM;
    public IloIntVar[] TR1;
    public IloIntVar[] TR2;
    public IloIntVar[] TR3;
    public IloNumVar[] SB;
    public HashMapAmir<String, IloNumVar> X;
    public HashMapAmir<String, IloNumVar> XM;
    public IloNumVar[][] Sold;
    public IloNumVar[][] Snew;
    private IloCplex cplex;
    private IloLinearNumExpr obj;
    private IloNumVar NTC;
    private IloNumVar ENC;

    public IloRange[][] constraints = new IloRange[51][];
    private IloNumVar TFC;
    private IloNumVar TOC;
    private IloNumVar TTC;
    private IloNumVar TPC;
    private IloNumVar BEN;
    private IloNumVar[] FC;

    public Module(Data data) {
        this.data = data;
        data.ReadData();
        initVariables();
        initConstraints();
    }

    public void initConstraints() {
        try {
            // Objects Value
            obj = cplex.linearNumExpr();
            obj.addTerm(1, NTC);
            obj.addTerm(1, ENC);
            cplex.addMinimize(obj);

            //region con2
            IloLinearNumExpr Con2 = cplex.linearNumExpr();

            Con2.addTerm(1, TFC);
            Con2.addTerm(1, TTC);
            Con2.addTerm(1, TOC);
            Con2.addTerm(1, TPC);
            Con2.addTerm(-1, BEN);
            Con2.addTerm(-1, NTC);

            constraints[1] = new IloRange[1];
            constraints[1][0] = cplex.addEq(Con2, 0);
            //endregion

            //Constraints 3
            IloLinearNumExpr Con3 = cplex.linearNumExpr();
            Con3.addTerm(-1, TFC);

            double r = 1 - data.r.get("r1");
            for (int t = 0; t < data.t; t++) {
                double rt = Math.pow(r, -1 * t);
                Con3.addTerm(rt, FC[t]);
            }

            cplex.addEq(Con3, 0)

        } catch (IloException e) {
            e.printStackTrace();
        }
    }

    public void initVariables() {
        try {
            cplex = new IloCplex();
            NTC = cplex.numVar(-1 * Double.MAX_VALUE, Double.MAX_VALUE);
            ENC = cplex.numVar(0, Double.MAX_VALUE);

            // variable
            WH = new IloIntVar[data.i][data.hi][];
            for (int i = 0; i < data.i; i++)
                for (int j = 0; j < data.hi; j++)
                    WH[i][j] = cplex.boolVarArray(data.t);


            FF = new IloIntVar[data.f][data.ho][data.o][];
            for (int i = 0; i < data.f; i++)
                for (int h = 0; h < data.ho; h++)
                    for (int o = 0; o < data.o; o++)
                        FF[i][h][o] = cplex.boolVarArray(data.t);

            Q = new IloIntVar[data.q][data.hq][];
            for (int q = 0; q < data.q; q++)
                for (int j = 0; j < data.hq; j++)
                    Q[q][j] = cplex.boolVarArray(data.t);

            DA = new IloIntVar[data.d][data.hd][];
            for (int d = 0; d < data.d; d++)
                for (int j = 0; j < data.hd; j++)
                    DA[d][j] = cplex.boolVarArray(data.t);

            RF = new IloIntVar[data.n][data.hn][];
            for (int n = 0; n < data.n; n++)
                for (int j = 0; j < data.hn; j++)
                    RF[n][j] = cplex.boolVarArray(data.t);

            RM = new IloIntVar[data.m][data.hm][];
            for (int m = 0; m < data.m; m++)
                for (int j = 0; j < data.hm; j++)
                    RM[m][j] = cplex.boolVarArray(data.t);

            TR1 = cplex.boolVarArray(data.k);
            TR2 = cplex.boolVarArray(data.k);
            TR3 = cplex.boolVarArray(data.k);
            SB = cplex.numVarArray(data.t, 0, Double.MAX_VALUE);

            X = new HashMapAmir<>(3, "X");
            XM = new HashMapAmir<>(4, "XM");

            for (int t = 0; t < data.t; t++) {

                for (int f = 0; f < data.f; f++)
                    for (int i = 0; i < data.i; i++)
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "f" + f, "i" + i, "t" + t);

                for (int i = 0; i < data.i; i++)
                    for (int z = 0; z < data.z; z++) {
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "z" + z, "t" + t);
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "z" + z, "i" + i, "t" + t);
                    }

                for (int i = 0; i < data.i; i++) {
                    for (int d = 0; d < data.d; d++)
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "d" + d, "t" + t);

                    for (int m = 0; m < data.m; m++) {
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "m" + m, "t" + t);
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "m" + m, "i" + i, "t" + t);
                    }
                    for (int n = 0; n < data.n; n++) {
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "n" + n, "t" + t);
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "n" + n, "i" + i, "t" + t);
                    }
                }

                for (int s = 0; s < data.s; s++) {
                    for (int f = 0; f < data.f; f++)
                        XM.put(cplex.numVar(0, Double.MAX_VALUE), "s" + s, "f" + f, "t" + t);

                    for (int m = 0; m < data.f; m++)
                        XM.put(cplex.numVar(0, Double.MAX_VALUE), "s" + s, "m" + m, "t" + t);

                }

                for (int d = 0; d < data.d; d++) {
                    for (int f = 0; f < data.f; f++)
                        XM.put(cplex.numVar(0, Double.MAX_VALUE), "d" + d, "f" + f, "t" + t);

                    for (int q = 0; q < data.q; q++)
                        XM.put(cplex.numVar(0, Double.MAX_VALUE), "d" + d, "q" + q, "t" + t);
                }
            }

            Sold = new IloNumVar[data.i][];
            Snew = new IloNumVar[data.i][];
            for (int i = 0; i < data.i; i++) {
                Sold[i] = cplex.numVarArray(data.t, 0, Double.MAX_VALUE);
                Snew[i] = cplex.numVarArray(data.t, 0, Double.MAX_VALUE);
            }

        } catch (
                IloException e) {
            e.printStackTrace();
        }
    }
}
