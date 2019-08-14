package com.company;

import MultiMap.HashMapAmir;
import ilog.concert.*;
import ilog.cplex.*;

public class Module {

    private final Data data;

    public Module(Data data) {
        this.data = data;
        data.ReadData();
    }

    public void a() {
        IloCplex cplex;
        try {
            cplex = new IloCplex();

            // variable
            IloIntVar[][][] WH = new IloIntVar[data.i][data.hi][];
            for (int i = 0; i < data.i; i++)
                for (int j = 0; j < data.hi; j++)
                    WH[i][j] = cplex.boolVarArray(data.t);


            IloIntVar[][][][] FF = new IloIntVar[data.f][data.ho][data.o][];
            for (int i = 0; i < data.f; i++)
                for (int h = 0; h < data.ho; h++)
                    for (int o = 0; o < data.o; o++)
                        FF[i][h][o] = cplex.boolVarArray(data.t);

            IloIntVar[][][] Q = new IloIntVar[data.q][data.hq][];
            for (int q = 0; q < data.q; q++)
                for (int j = 0; j < data.hq; j++)
                    Q[q][j] = cplex.boolVarArray(data.t);

            IloIntVar[][][] DA = new IloIntVar[data.d][data.hd][];
            for (int d = 0; d < data.d; d++)
                for (int j = 0; j < data.hd; j++)
                    DA[d][j] = cplex.boolVarArray(data.t);

            IloIntVar[][][] RF = new IloIntVar[data.n][data.hn][];
            for (int n = 0; n < data.n; n++)
                for (int j = 0; j < data.hn; j++)
                    RF[n][j] = cplex.boolVarArray(data.t);

            IloIntVar[][][] RM = new IloIntVar[data.m][data.hm][];
            for (int m = 0; m < data.m; m++)
                for (int j = 0; j < data.hm; j++)
                    RM[m][j] = cplex.boolVarArray(data.t);

            IloIntVar[] TR1 = cplex.boolVarArray(data.k);
            IloIntVar[] TR2 = cplex.boolVarArray(data.k);
            IloIntVar[] TR3 = cplex.boolVarArray(data.k);

            IloNumVar[][][][] XM = new IloNumVar[data.j][data.s][data.f][];
            for (int j = 0; j < data.j; j++)
                for (int s = 0; s < data.s; s++)
                    for (int f = 0; f < data.f; f++)
                        XM[j][s][f] = cplex.numVarArray(data.t, 0, Double.MAX_VALUE);

            HashMapAmir<String, IloNumVar> X = new HashMapAmir<>(3, "X");
            for (int f = 0; f < data.f; f++)
                for (int i = 0; i < data.i; i++)
                    for (int t = 0; t < data.t; t++)
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "f" + f, "i" + i, "t" + t);

            for (int i = 0; i < data.i; i++)
                for (int z = 0; z < data.z; z++)
                    for (int t = 0; t < data.t; t++) {
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "z" + z, "t" + t);
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "z" + z, "i" + i, "t" + t);
                    }

            for (int i = 0; i < data.i; i++)
                for (int d = 0; d < data.d; d++)
                    for (int t = 0; t < data.t; t++)
                        X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "d" + d, "t" + t);


        } catch (
                IloException e) {
            e.printStackTrace();
        }
    }
}
