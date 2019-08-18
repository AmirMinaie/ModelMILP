package com.company;

import MultiMap.HashMapAmir;
import ilog.concert.*;
import ilog.cplex.IloCplex;

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
    private HashMapAmir<String, IloNumVar> P;

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

            //region Constraints 2
            IloLinearNumExpr Con2 = cplex.linearNumExpr();

            Con2.addTerm(1, TFC);
            Con2.addTerm(1, TTC);
            Con2.addTerm(1, TOC);
            Con2.addTerm(1, TPC);
            Con2.addTerm(-1, BEN);
            Con2.addTerm(-1, NTC);

            constraints[2] = new IloRange[1];
            constraints[2][0] = cplex.addEq(Con2, 0);
            //endregion

            //region Constraints 3
            IloLinearNumExpr Con3 = cplex.linearNumExpr();
            Con3.addTerm(-1, TFC);

            double r = 1 - data.r.get("r1");
            for (int t = 0; t < data.t; t++) {
                double rt = Math.pow(r, -1 * t);
                Con3.addTerm(rt, FC[t]);
            }

            constraints[3] = new IloRange[1];
            constraints[3][0] = cplex.addEq(Con3, 0);
            //endregion

            //region Constraints 4
            constraints[4] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {
                IloLinearNumExpr Exp = cplex.linearNumExpr();
                Exp.addTerm(-1, FC[t]);

                for (int f = 0; f < data.f; f++)
                    for (int ho = 0; ho < data.ho; ho++)
                        for (int o = 0; o < data.o; o++)
                            Exp.addTerm(FF[f][ho][o][t], data.FA.get("f" + f, "h" + ho, "o" + o, "t" + t));

                for (int i = 0; i < data.i; i++)
                    for (int hi = 0; hi < data.hi; hi++)
                        Exp.addTerm(WH[i][hi][t], data.FW.get("i" + i, "h" + hi, "t" + t));

                for (int d = 0; d < data.d; d++)
                    for (int hd = 0; hd < data.hd; hd++)
                        Exp.addTerm(DA[d][hd][t], data.FD.get("d" + d, "h" + hd, "t" + t));

                for (int n = 0; n < data.n; n++)
                    for (int hn = 0; hn < data.hd; hn++)
                        Exp.addTerm(RF[n][hn][t], data.FR.get("n" + n, "hn" + hn, "t" + t));

                for (int m = 0; m < data.m; m++)
                    for (int hm = 0; hm < data.hm; hm++)
                        Exp.addTerm(RM[m][hm][t], data.FM.get("m" + m, "hm" + hm, "t" + t));

                for (int q = 0; q < data.q; q++)
                    for (int hq = 0; hq < data.hq; hq++)
                        Exp.addTerm(Q[q][hq][t], data.FQ.get("q" + q, "hq" + hq, "t" + t));

                constraints[4][t] = cplex.addEq(Exp, 0);
            }
            //endregion

            //region Constraints 5
            constraints[5] = new IloRange[1];

            IloLinearNumExpr Exp5 = cplex.lqNumExpr();
            Exp5.addTerm(-1, TTC);

            for (int t = 0; t < data.t; t++)
                for (int k = 0; k < data.k; k++) {

                    for (int j = 0; j < data.j; j++)
                        for (int s = 0; s < data.s; s++)
                            for (int f = 0; f < data.f; f++)
                                Exp5.addTerm(XM.get("j" + j, "s" + s, "f" + f, "t" + t, "K" + k),
                                        data.cj.get("k" + k, "j" + j, "t" + t) *
                                                data.DS.get("s" + s, "f" + f));

                    for (int f = 0; f < data.f; f++)
                        for (int i = 0; i < data.i; i++)
                            Exp5.addTerm(X.get("f" + f, "i" + i, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("f" + f, "i" + i));

                    for (int i = 0; i < data.i; i++)
                        for (int z = 0; z < data.z; z++) {
                            Exp5.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "z" + z));
                            Exp5.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "z" + z));
                        }

                    for (int i = 0; i < data.i; i++)
                        for (int d = 0; d < data.d; d++)
                            Exp5.addTerm(X.get("i" + i, "d" + d, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "d" + d));

                    for (int i = 0; i < data.i; i++)
                        for (int n = 0; n < data.n; n++) {
                            Exp5.addTerm(X.get("i" + i, "n" + n, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "n" + n));
                            Exp5.addTerm(X.get("n" + n, "i" + i, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "n" + n));
                        }

                    for (int i = 0; i < data.i; i++)
                        for (int m = 0; m < data.m; m++) {
                            Exp5.addTerm(X.get("i" + i, "m" + m, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "m" + m));

                            Exp5.addTerm(X.get("m" + m, "i" + i, "t" + t, "k" + k),
                                    data.c.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "m" + m));
                        }

                    for (int j = 0; j < data.j; j++) {
                        for (int d = 0; d < data.d; d++) {

                            for (int f = 0; f < data.f; f++)
                                Exp5.addTerm(XM.get("j" + j, "d" + d, "f" + f, "t" + t, "K" + k),
                                        data.cj.get("k" + k, "j" + j, "t" + t) *
                                                data.DS.get("d" + d, "f" + f));

                            for (int q = 0; q < data.q; q++)
                                Exp5.addTerm(XM.get("j" + j, "d" + d, "q" + q, "t" + t, "K" + k),
                                        data.cj.get("k" + k, "j" + j, "t" + t) *
                                                data.DS.get("d" + d, "q" + q));


                        }

                        for (int s = 0; s < data.s; s++)
                            for (int m = 0; m < data.m; m++)
                                Exp5.addTerm(XM.get("j" + j, "s" + s, "m" + m, "t" + t, "K" + k),
                                        data.cj.get("k" + k, "j" + j, "t" + t) *
                                                data.DS.get("s" + s, "m" + m));
                    }
                }

            constraints[4][0] = cplex.addEq(Exp5, 0);
            //endregion

            //region Constraints 6
            constraints[6] = new IloRange[1];
            IloLinearNumExpr EXP6 = cplex.linearNumExpr();
            EXP6.addTerm(TOC, -1);

            for (int t = 0; t < data.t; t++) {

                double rt = Math.pow(1 - data.r.get("r0"), -1 * t);

                for (int f = 0; f < data.f; f++)
                    for (int o = 0; o < data.o; o++)
                        EXP6.addTerm(P.get("f" + f, "o" + o, "t" + t),
                                data.OC.get("o" + o, "t" + t) * rt);

                for (int i = 0; i < data.i; i++)
                    for (int k = 0; k < data.k; k++) {

                        for (int z = 0; z < data.z; z++) {
                            EXP6.addTerm(X.get("Z" + z, "i" + i, "t" + t, "k" + k), rt * data.OCI.get("t" + t));
                            EXP6.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k), rt * data.OCI.get("t" + t));
                            EXP6.addTerm(Sold[i][t], rt * data.OCI.get("t" + t));
                            EXP6.addTerm(Snew[i][t], rt * data.OCI.get("t" + t));
                        }

                        for (int d = 0; d < data.d; d++)
                            EXP6.addTerm(X.get("i" + i, "d" + d, "t" + t, "k" + k),
                                    rt * data.OCD.get("t" + t));

                        for (int n = 0; n < data.n; n++)
                            EXP6.addTerm(X.get("i" + i, "n" + n, "t" + t, "k" + k),
                                    rt * data.OCN.get("t" + t));

                        for (int m = 0; m < data.m; m++)
                            EXP6.addTerm(X.get("i" + i, "m" + m, "t" + t, "k" + k),
                                    rt * data.OCM.get("t" + t));

                    }
            }

            constraints[6][0] = cplex.addEq(EXP6, 0);
            //endregion




        } catch (
                IloException e) {
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
                        for (int k = 0; k < data.k; k++)
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "f" + f, "i" + i, "t" + t, "k" + k);

                for (int i = 0; i < data.i; i++)
                    for (int z = 0; z < data.z; z++)
                        for (int k = 0; k < data.k; k++) {
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "z" + z, "t" + t, "k" + k);
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "z" + z, "i" + i, "t" + t, "k" + k);
                        }

                for (int i = 0; i < data.i; i++)
                    for (int k = 0; k < data.k; k++) {
                        for (int d = 0; d < data.d; d++)
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "d" + d, "t" + t, "k" + k);

                        for (int m = 0; m < data.m; m++) {
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "m" + m, "t" + t, "k" + k);
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "m" + m, "i" + i, "t" + t, "k" + k);
                        }
                        for (int n = 0; n < data.n; n++) {
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "i" + i, "n" + n, "t" + t, "k" + k);
                            X.put(cplex.numVar(0, Double.MAX_VALUE), "n" + n, "i" + i, "t" + t, "k" + k);
                        }
                    }

                for (int s = 0; s < data.s; s++)
                    for (int k = 0; k < data.k; k++) {
                        for (int f = 0; f < data.f; f++)
                            XM.put(cplex.numVar(0, Double.MAX_VALUE), "s" + s, "f" + f, "t" + t, "k" + k);

                        for (int m = 0; m < data.f; m++)
                            XM.put(cplex.numVar(0, Double.MAX_VALUE), "s" + s, "m" + m, "t" + t, "k" + k);
                    }

                for (int d = 0; d < data.d; d++)
                    for (int k = 0; k < data.k; k++) {
                        for (int f = 0; f < data.f; f++)
                            XM.put(cplex.numVar(0, Double.MAX_VALUE), "d" + d, "f" + f, "t" + t, "k" + k);

                        for (int q = 0; q < data.q; q++)
                            XM.put(cplex.numVar(0, Double.MAX_VALUE), "d" + d, "q" + q, "t" + t, "k" + k);
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
