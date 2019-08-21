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
    public IloCplex cplex;
    public IloLinearNumExpr obj;
    public IloNumVar NTC;
    public IloNumVar ENC;
    public IloNumVar[] Delta;
    public IloIntVar W[];

    public IloRange[][] constraints = new IloRange[51][];
    public IloNumVar TFC;
    public IloNumVar TOC;
    public IloNumVar TTC;
    public IloNumVar TPC;
    public IloNumVar BEN;
    public IloNumVar[] FC;
    public HashMapAmir<String, IloNumVar> P;
    public IloNumVar TEN;
    public IloNumVar PEN;
    public IloNumVar EN;

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

            //region Constraints 7
            constraints[7] = new IloRange[1];
            IloLinearNumExpr Exp7 = cplex.linearNumExpr();

            Exp7.addTerm(TPC, -1);

            for (int t = 0; t < data.t; t++)
                for (int k = 0; k < data.k; k++) {
                    double tr = Math.pow(1 - data.r.get("r0"), -1 * t);
                    for (int j = 0; j < data.j; j++)
                        for (int s = 0; s < data.s; s++) {
                            for (int f = 0; f < data.k; f++)
                                Exp7.addTerm(XM.get("j" + j, "s" + s, "f" + f, "t" + t, "k" + k),
                                        tr * data.PS.get("j" + j, "s" + s, "t" + t));

                            for (int m = 0; m < data.m; m++)
                                Exp7.addTerm(XM.get("j" + j, "s" + s, "m" + m, "t" + t, "k" + k),
                                        tr * data.PS.get("j" + j, "s" + s, "t" + t));
                        }

                    double pw = data.PZ1.get("t" + t) * data.L1.get("l0")
                            + data.PZ2.get("t" + t) * data.L2.get("l0")
                            + data.PZ3.get("t" + t) * (1 - data.L2.get("l0") - data.L1.get("l0"));
                    for (int i = 0; i < data.i; i++)
                        for (int z = 0; z < data.z; z++)
                            Exp7.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k),
                                    pw * tr);

                }
            constraints[7][0] = cplex.addEq(Exp7, 0);

            //endregion

            //region constraints 8
            constraints[8] = new IloRange[1];
            IloLinearNumExpr Exp8 = cplex.linearNumExpr();
            Exp8.addTerm(BEN, -1);

            for (int t = 0; t < data.t; t++) {
                double tr = Math.pow(1 - data.r.get("r0"), -t);
                for (int k = 0; k < data.k; k++) {

                    for (int i = 0; i < data.i; i++)
                        for (int z = 0; z < data.z; z++)
                            Exp8.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k)
                                    , tr * data.PZ.get("t" + t));

                    for (int j = 0; j < data.j; j++)
                        for (int d = 0; d < data.d; d++)
                            for (int q = 0; q < data.q; q++)
                                Exp8.addTerm(XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k)
                                        , tr * data.PQ.get("t" + t, "j" + j));


                }
            }

            constraints[8][0] = cplex.addEq(Exp8, 0);
            //endregion

            //region constraints 9
            constraints[9] = new IloRange[1];
            constraints[9][0] = cplex.addEq(cplex.sum(TEN, PEN, cplex.prod(-1, EN))
                    , 0);
            //endregion

            //region constraints 10
            constraints[10] = new IloRange[1];
            IloLinearNumExpr Exp10 = cplex.linearNumExpr();
            Exp10.addTerm(-1, TEN);

            for (int t = 0; t < data.t; t++)
                for (int k = 0; k < data.k; k++) {

                    for (int j = 0; j < data.j; j++) {
                        for (int s = 0; s < data.s; s++)
                            for (int f = 0; f < data.f; f++)
                                Exp10.addTerm(XM.get("j" + j, "s" + s, "f" + f, "t" + t, "k" + k)
                                        , data.A.get("k" + k) * data.DS.get("s" + s, "f" + f));

                        for (int d = 0; d < data.d; d++)
                            for (int f = 0; f < data.f; f++)
                                Exp10.addTerm(XM.get("j" + j, "d" + d, "f" + f, "t" + t, "k" + k)
                                        , data.A.get("k" + k) * data.DS.get("d" + d, "f" + f));

                        for (int d = 0; d < data.d; d++)
                            for (int q = 0; q < data.q; q++)
                                Exp10.addTerm(XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k)
                                        , data.A.get("k" + k) * data.DS.get("d" + d, "q" + q));

                        for (int s = 0; s < data.s; s++)
                            for (int m = 0; m < data.m; m++)
                                Exp10.addTerm(XM.get("j" + j, "s" + s, "m" + m, "t" + t, "k" + k)
                                        , data.A.get("k" + k) * data.DS.get("s" + s, "m" + m));

                    }

                    for (int f = 0; f < data.f; f++)
                        for (int i = 0; i < data.i; i++)
                            Exp10.addTerm(X.get("f" + f, "i" + i, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("f" + f, "i" + i));


                    for (int i = 0; i < data.i; i++)
                        for (int z = 0; z < data.z; z++) {
                            Exp10.addTerm(X.get("i" + i, "z" + z, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("i" + i, "z" + z));

                            Exp10.addTerm(X.get("z" + z, "i" + i, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("i" + i, "z" + z));

                        }

                    for (int i = 0; i < data.i; i++)
                        for (int d = 0; d < data.d; d++)
                            Exp10.addTerm(X.get("i" + i, "d" + d, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("i" + i, "d" + d));

                    for (int i = 0; i < data.i; i++)
                        for (int n = 0; n < data.n; n++) {
                            Exp10.addTerm(X.get("i" + i, "n" + n, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("i" + i, "n" + n));
                            Exp10.addTerm(X.get("n" + n, "i" + i, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("i" + i, "n" + n));

                        }

                    for (int i = 0; i < data.i; i++)
                        for (int m = 0; m < data.m; m++) {
                            Exp10.addTerm(X.get("i" + i, "m" + m, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("i" + i, "m" + m));

                            Exp10.addTerm(X.get("m" + m, "i" + i, "t" + t, "K" + k),
                                    data.A.get("k") * data.DS.get("i" + i, "m" + m));
                        }

                }

            constraints[10][0] = cplex.addEq(Exp10, 0);
            //endregion

            //region constraints 11
            constraints[11] = new IloRange[1];
            IloLinearNumExpr Exp11 = cplex.linearNumExpr();
            Exp11.addTerm(PEN, -1);

            for (int o = 0; o < data.o; o++)
                for (int f = 0; f < data.f; f++)
                    for (int t = 0; t < data.t; t++)
                        Exp11.addTerm(P.get("f" + f, "o" + o, "t" + t), data.A.get("o" + o));

            constraints[11][0] = cplex.addEq(Exp11, 0);
            //endregion

            //region constraints 12
            constraints[12] = new IloRange[1];
            IloLinearNumExpr Exp12 = cplex.linearNumExpr();
            Exp12.addTerm(-1, ENC);
            for (int ta = 0; ta < data.ta; ta++)
                Exp12.addTerm(data.RE.get("ta" + ta), Delta[ta]);

            constraints[12][0] = cplex.addEq(Exp12, 0);
            //endregion

            //region constraints 13
            constraints[13] = new IloRange[1];
            IloLinearNumExpr Exp13 = cplex.linearNumExpr();
            Exp13.addTerm(-1, EN);

            for (int ta = 0; ta < data.ta; ta++)
                Exp13.addTerm(Delta[ta], 1);

            constraints[13][0] = cplex.addEq(Exp13, 0);
            //endregion

            //region Constraints 14
            constraints[14] = new IloRange[2];
            constraints[14][0] = cplex.addLe(Delta[0], data.CU.get("ta0"));

            IloLinearNumExpr Exp14 = cplex.linearNumExpr();
            Exp14.addTerm(1, Delta[1]);
            Exp14.addTerm(-1 * data.CU.get("ta1"), W[1]);
            constraints[14][1] = cplex.addGe(0, Exp14);
            //endregion

            //region constraints 15
            constraints[15] = new IloRange[2 * data.ta - 4];
            for (int ta = 1; ta < data.ta - 1; ta++) {

                IloLinearNumExpr Exp151 = cplex.linearNumExpr();
                Exp151.addTerm(-1, Delta[ta]);
                Exp151.addTerm(data.CU.get("ta" + ta) - data.CU.get("ta" + (ta - 1)),
                        W[ta - 1]);
                constraints[15][2 * ta - 2] = cplex.addGe(Exp151, 0);

                IloLinearNumExpr Exp152 = cplex.linearNumExpr();
                Exp152.addTerm(-1, Delta[ta]);
                Exp152.addTerm(data.CU.get("ta" + ta) - data.CU.get("ta" + (ta - 1))
                        , W[ta]);
                constraints[15][2 * ta - 2] = cplex.addLe(Exp152, 0);
            }
            //endregion

            //region constraints 17
            constraints[17] = new IloRange[1];
            IloLinearNumExpr Exp17 = cplex.linearNumExpr();
            Exp17.addTerm(-1, Delta[data.ta]);
            Exp17.addTerm(Double.MIN_VALUE, W[data.ta - 1]);
            constraints[17][0] = cplex.addGe(Exp17, 0);
            //endregion

            //region constraints 18
            constraints[18] = new IloRange[data.j * data.f * data.t];
            int counter = 0;
            for (int j = 0; j < data.j; j++)
                for (int f = 0; f < data.f; f++)
                    for (int t = 0; t < data.t; t++) {
                        IloLinearNumExpr Exp18 = cplex.linearNumExpr();

                        for (int k = 0; k < data.k; k++) {
                            for (int s = 0; s < data.s; s++)
                                Exp18.addTerm(XM.get("j" + j, "s" + s, "f" + f, "t" + t, "k" + k), 1);

                            for (int d = 0; d < data.d; d++)
                                Exp18.addTerm(XM.get("j" + j, "d" + d, "f" + f, "t" + t, "k" + k), 1);

                            for (int i = 0; i < data.i; i++)
                                Exp18.addTerm(X.get("f" + f, "i" + i, "t" + t, "k" + k), -1 * data.RM.get("j" + j));
                        }
                        constraints[18][counter] = cplex.addEq(Exp18, 0);
                    }
            //endregion

            //region constraints 19 - 20
            constraints[19] = new IloRange[data.j * data.t * data.d];
            constraints[20] = new IloRange[data.j * data.t * data.d];
            counter = 0;
            for (int j = 0; j < data.j; j++)
                for (int t = 0; t < data.t; t++)
                    for (int d = 0; d < data.d; d++) {

                        IloLinearNumExpr Exp19 = cplex.linearNumExpr();
                        for (int k = 0; k < data.k; k++) {
                            for (int f = 0; f < data.f; f++)
                                Exp19.addTerm(1, XM.get("j" + j, "d" + d, "f" + f, "t" + t, "k" + k));

                            for (int i = 0; i < data.i; i++)
                                Exp19.addTerm(-1 * data.L1.get("j" + j) * data.RM.get("j" + j),
                                        X.get("i", "d", "t", "k"));

                        }
                        constraints[19][counter] = cplex.addEq(Exp19, 0);

                        IloLinearNumExpr Exp20 = cplex.linearNumExpr();
                        for (int k = 0; k < data.k; k++) {
                            for (int q = 0; q < data.q; q++)
                                Exp20.addTerm(1, XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k));

                            for (int i = 0; i < data.i; i++)
                                Exp20.addTerm(-1 * (1 - data.L1.get("j" + j)) * data.RM.get("j" + j),
                                        X.get("i", "d", "t", "k"));

                        }
                        constraints[20][counter] = cplex.addEq(Exp20, 0);
                        counter++;
                    }
            //endregion

            //region 21
            constraints[21] = new IloRange[data.t * data.n];
            counter = 0;

            for (int t = 0; t < data.t; t++)
                for (int n = 0; n < data.n; n++) {

                    IloLinearNumExpr Exp21 = cplex.linearNumExpr();
                    for (int k = 0; k < data.k; k++)
                        for (int i = 0; i < data.i; i++) {
                            Exp21.addTerm(X.get("i" + i, "n" + n, "t" + t, "k" + k), 1);
                            Exp21.addTerm(X.get("n" + n, "i" + i, "t" + t, "k" + k), -1);
                        }
                    constraints[21][counter] = cplex.addEq(Exp21, 0);
                    counter++;
                }
            //endregion

            //region 22
            constraints[22] = new IloRange[data.t * data.m];
            counter = 0;

            for (int t = 0; t < data.t; t++)
                for (int m = 0; m < data.m; m++) {
                    IloLinearNumExpr Exp22 = cplex.linearNumExpr();
                    for (int k = 0; k < data.k; k++)
                        for (int i = 0; i < data.i; i++) {
                            Exp22.addTerm(X.get("i" + i, "m" + m, "t" + t, "k" + k), 1);
                            Exp22.addTerm(X.get("m" + m, "i" + i, "t" + t, "k" + k), -1);
                        }
                    constraints[22][counter] = cplex.addEq(Exp22, 0);
                    counter++;
                }
            //endregion
// TODO: 8/22/2019 23 
            constraints[23] = new IloRange[data.m * data.t * data.j];
            counter = 0;
            for (int m = 0; m < data.m; m++)
                for (int t = 0; t < data.t; t++)
                    for (int j = 0; j < data.j; j++) {
                        IloLinearNumExpr Exp23= cplex.linearNumExpr();

                        for (int i = 0; i < ; i++) {
                            
                        }
                        
                    }


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
            Delta = cplex.numVarArray(data.ta, 0, Double.MAX_VALUE);
            W = cplex.boolVarArray(data.ta);

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
