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

                    double pw = data.PZ1.get("t" + t) * data.G1.get("l0")
                            + data.PZ2.get("t" + t) * data.G2.get("l0")
                            + data.PZ3.get("t" + t) * (1 - data.G2.get("l0") - data.G1.get("l0"));
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
                                Exp19.addTerm(-1 * data.G1.get("j" + j) * data.RM.get("j" + j),
                                        X.get("i", "d", "t", "k"));

                        }
                        constraints[19][counter] = cplex.addEq(Exp19, 0);

                        IloLinearNumExpr Exp20 = cplex.linearNumExpr();
                        for (int k = 0; k < data.k; k++) {
                            for (int q = 0; q < data.q; q++)
                                Exp20.addTerm(1, XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k));

                            for (int i = 0; i < data.i; i++)
                                Exp20.addTerm(-1 * (1 - data.G1.get("j" + j)) * data.RM.get("j" + j),
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

            //region 23
            constraints[23] = new IloRange[data.m * data.t * data.j];
            counter = 0;
            for (int m = 0; m < data.m; m++)
                for (int t = 0; t < data.t; t++)
                    for (int j = 0; j < data.j; j++) {

                        IloLinearNumExpr Exp23 = cplex.linearNumExpr();
                        for (int k = 0; k < data.k; k++) {
                            for (int s = 0; s < data.s; s++) {
                                Exp23.addTerm(XM.get("j" + j, "s" + s, "m" + m, "t" + t, "k" + k),
                                        1);
                            }

                            for (int i = 0; i < data.i; i++) {
                                Exp23.addTerm(X.get("m" + m, "i" + i, "t" + t, "k" + k),
                                        -1 * data.G2.get("j" + j) * data.RM.get("j" + j));
                            }
                        }
                        cplex.addEq(Exp23, 0);

                    }
            //endregion

            //region 24 - 27
            constraints[24] = new IloRange[data.i * data.t];
            constraints[25] = new IloRange[data.i * data.t];
            constraints[26] = new IloRange[data.i * data.t];
            constraints[27] = new IloRange[data.i * data.t];
            counter = 0;

            for (int i = 0; i < data.i; i++)
                for (int t = 0; t < data.t; t++) {

                    //region 24
                    IloLinearNumExpr Exp24 = cplex.linearNumExpr();
                    Exp24.addTerm(Snew[i][t], 1);
                    Exp24.addTerm(Snew[i][t - 1], -1);
                    for (int k = 0; k < data.k; k++) {
                        for (int f = 0; f < data.f; f++)
                            Exp24.addTerm(X.get("f" + f, "i" + i, "t" + t, "k" + k),
                                    1);
                        for (int z = 0; z < data.z; z++)
                            Exp24.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k),
                                    -1);
                    }
                    constraints[24][counter] = cplex.addEq(Exp24, 0);
                    //endregion

                    //region 25
                    IloLinearNumExpr Exp25 = cplex.linearNumExpr();
                    Exp25.addTerm(-1 * data.L1.get("l0"), Sold[i][t - 1]);
                    Exp25.addTerm(1 * data.L1.get("l0"), Sold[i][t]);
                    for (int k = 0; k < data.k; k++) {
                        for (int z = 0; z < data.z; z++)
                            Exp25.addTerm(-1 * data.L1.get("l0"), X.get("z" + z, "i" + i, "t" + t, "k" + k));
                        for (int n = 0; n < data.n; n++)
                            Exp25.addTerm(1, X.get("i" + i, "n" + n, "t" + t, "k" + k));
                    }
                    constraints[25][counter] = cplex.addEq(Exp25, 0);
                    //endregion

                    //region 26
                    IloLinearNumExpr Exp26 = cplex.linearNumExpr();
                    Exp26.addTerm(-1 * data.L2.get("l0"), Sold[i][t - 1]);
                    Exp26.addTerm(1 * data.L2.get("l0"), Sold[i][t]);
                    for (int k = 0; k < data.k; k++) {
                        for (int z = 0; z < data.z; z++)
                            Exp26.addTerm(1 * data.L2.get("l0"), X.get("z" + z, "i" + i, "t" + t, "k" + k));
                        for (int m = 0; m < data.m; m++)
                            Exp26.addTerm(-1, X.get("i" + i, "m" + m, "t" + t, "k" + k));
                    }
                    constraints[26][counter] = cplex.addEq(Exp26, 0);
                    //endregion


                    //region 27
                    IloLinearNumExpr Exp27 = cplex.linearNumExpr();
                    double L3 = 1 - data.L2.get("l0") - data.L1.get("l0");
                    Exp27.addTerm(-1 * L3, Sold[i][t - 1]);
                    Exp27.addTerm(1 * L3, Sold[i][t]);
                    for (int k = 0; k < data.k; k++) {
                        for (int z = 0; z < data.z; z++)
                            Exp27.addTerm(1 * L3, X.get("z" + z, "i" + i, "t" + t, "k" + k));
                        for (int d = 0; d < data.d; d++)
                            Exp27.addTerm(-1, X.get("i" + i, "d" + d, "t" + t, "k" + k));
                    }
                    constraints[27][counter] = cplex.addEq(Exp27, 0);
                    //endregion

                    counter++;
                }
            //endregion

            //region 28
            constraints[28] = new IloRange[data.f * data.t];

            for (int f = 0; f < data.f; f++)
                for (int t = 0; t < data.t; t++) {

                    IloLinearNumExpr Exp28 = cplex.linearNumExpr();
                    for (int i = 0; i < data.i; i++)
                        for (int k = 0; k < data.k; k++) {
                            Exp28.addTerm(1,
                                    X.get("f" + f, "i" + i, "t" + t, "k" + k));
                        }
                    for (int h = 0; h < data.ho; h++)
                        for (int o = 0; o < data.o; o++)
                            for (int tf = 0; tf < data.t; tf++) {
                                Exp28.addTerm(-1 * data.CA.get("h" + h, "o" + o),
                                        FF[f][h][o][tf]);
                            }
                    constraints[28][counter] = cplex.addLe(Exp28, 0);
                    counter++;
                }
            //endregion

            //region 29
            constraints[29] = new IloRange[data.f * data.t];

            for (int f = 0; f < data.f; f++) {

                IloLinearNumExpr Exp29 = cplex.linearNumExpr();
                for (int h = 0; h < data.ho; h++)
                    for (int t = 0; t < data.t; t++)
                        for (int o = 0; o < data.o; o++) {
                            Exp29.addTerm(1, FF[f][h][o][t]);
                        }
                constraints[29][f] = cplex.addLe(Exp29, 1);
            }
            //endregion

            //region 31
            constraints[31] = new IloRange[data.i * data.t];
            counter = 0;
            for (int i = 0; i < data.i; i++)
                for (int t = 0; t < data.i; t++) {
                    IloLinearNumExpr Exp31 = cplex.linearNumExpr();
                    Exp31.addTerm(1, Snew[i][t]);
                    Exp31.addTerm(1, Sold[i][t]);
                    for (int k = 0; k < data.k; k++)
                        for (int z = 0; z < data.z; z++) {
                            Exp31.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k), 1);
                        }
                    for (int h = 0; h < data.hi; h++)
                        for (int tf = 0; tf < data.t; tf++) {
                            Exp31.addTerm(data.CA.get("i" + i, "h" + h), WH[i][h][tf]);
                        }
                    constraints[31][counter] = cplex.addLe(Exp31, 0);
                    counter++;
                }
            //endregion

            //region 32
            constraints[32] = new IloRange[data.i];
            for (int i = 0; i < data.i; i++) {
                IloLinearNumExpr Exp32 = cplex.linearNumExpr();
                for (int h = 0; h < data.hi; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp32.addTerm(WH[i][h][t], 1);
                cplex.addLe(Exp32, 1);
            }
            //endregion

            //region 34
            constraints[34] = new IloRange[data.t * data.t];
            counter = 0;
            for (int t = 0; t < data.t; t++)
                for (int d = 0; d < data.d; d++) {
                    IloLinearNumExpr Exp34 = cplex.linearNumExpr();

                    for (int i = 0; i < data.i; i++) {
                        for (int k = 0; k < data.k; k++)
                            Exp34.addTerm(X.get("i" + i, "d" + d, "t" + t, "k" + k)
                                    , 1);

                        for (int h = 0; h < data.hd; h++)
                            for (int tf = 0; tf < data.t; tf++)
                                Exp34.addTerm(-1 * data.CA.get("d" + d, "h" + h),
                                        DA[d][h][tf]);
                        constraints[34][counter] = cplex.addLe(Exp34, 0);
                        counter++;
                    }
                }
            //endregion

            //region 35
            constraints[35] = new IloRange[data.d];
            for (int d = 0; d < data.d; d++) {

                IloLinearNumExpr Exp35 = cplex.linearNumExpr();
                for (int h = 0; h < data.hd; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp35.addTerm(DA[d][h][t], 1);
                constraints[35][d] = cplex.addLe(Exp35, 1);
            }
            //endregion

            //region 37
            constraints[37] = new IloRange[data.t * data.n];
            counter = 0;
            for (int t = 0; t < data.t; t++)
                for (int n = 0; n < data.n; n++) {

                    IloLinearNumExpr Exp37 = cplex.linearNumExpr();
                    for (int i = 0; i < data.i; i++)
                        for (int k = 0; k < data.k; k++)
                            Exp37.addTerm(1,
                                    X.get("i" + i, "n" + n, "t" + t, "k" + k));
                    for (int h = 0; h < data.hn; h++)
                        for (int tf = 0; tf < data.t; tf++)
                            Exp37.addTerm(RF[n][h][tf],
                                    -1 * data.CA.get("n" + n, "h" + h));
                    constraints[37][counter] = cplex.addLe(0, Exp37);
                    counter++;
                }
            //endregion

            //region 38
            constraints[38] = new IloRange[data.n];
            for (int n = 0; n < data.n; n++) {
                IloLinearNumExpr Exp38 = cplex.linearNumExpr();

                for (int h = 0; h < data.hn; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp38.addTerm(1, RF[n][h][t]);

                constraints[38][n] = cplex.addLe(1, Exp38);
            }
            //endregion

            //region 40
            constraints[40] = new IloRange[data.t * data.m];
            counter = 0;
            for (int t = 0; t < data.t; t++)
                for (int m = 0; m < data.m; m++) {

                    IloLinearNumExpr Exp40 = cplex.linearNumExpr();
                    for (int i = 0; i < data.i; i++)
                        for (int k = 0; k < data.k; k++)
                            Exp40.addTerm(1,
                                    X.get("i" + i, "m" + m, "t" + t, "k" + k));
                    for (int h = 0; h < data.hm; h++)
                        for (int tf = 0; tf < data.t; tf++)
                            Exp40.addTerm(RM[m][h][tf],
                                    -1 * data.CA.get("m" + m, "h" + h));
                    constraints[40][counter] = cplex.addLe(0, Exp40);
                    counter++;
                }
            //endregion

            //region 41
            constraints[41] = new IloRange[data.m];
            for (int m = 0; m < data.m; m++) {
                IloLinearNumExpr Exp41 = cplex.linearNumExpr();

                for (int h = 0; h < data.hn; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp41.addTerm(1, RM[m][h][t]);

                constraints[41][m] = cplex.addLe(1, Exp41);
            }
            //endregion

            //region 43
            constraints[43] = new IloRange[data.t * data.q];
            counter = 0;
            for (int t = 0; t < data.t; t++)
                for (int q = 0; q < data.q; q++) {

                    IloLinearNumExpr Exp43 = cplex.linearNumExpr();
                    for (int d = 0; d < data.d; d++)
                        for (int k = 0; k < data.k; k++)
                            Exp43.addTerm(1,
                                    X.get("d" + d, "q" + q, "t" + t, "k" + k));
                    for (int h = 0; h < data.hq; h++)
                        for (int tf = 0; tf < data.t; tf++)
                            Exp43.addTerm(Q[q][h][tf],
                                    -1 * data.CA.get("q" + q, "h" + h));
                    constraints[40][counter] = cplex.addLe(0, Exp43);
                    counter++;
                }
            //endregion

            //region 44
            constraints[44] = new IloRange[data.q];
            for (int q = 0; q < data.q; q++) {
                IloLinearNumExpr Exp44 = cplex.linearNumExpr();

                for (int h = 0; h < data.hq; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp44.addTerm(1, Q[q][h][t]);

                constraints[41][q] = cplex.addLe(1, Exp44);
            }
            //endregion

            //region 47
            constraints[47] = new IloRange[data.t];

            for (int t = 0; t < data.t; t++) {
                IloLinearNumExpr Exp47 = cplex.linearNumExpr();
                Exp47.addTerm(SB[t + 1], 1 / (1 + data.r.get("r0")));
                Exp47.addTerm(FC[t], 1);
                Exp47.addTerm(SB[t], -1);
                constraints[47][t] = cplex.addEq(Exp47, data.BU.get("t" + 0));
            }
            //endregion

            //region 48 49
            constraints[48] = new IloRange[data.t * data.z];
            constraints[49] = new IloRange[data.t * data.z];
            counter = 0;
            for (int t = 0; t < data.t; t++)
                for (int z = 0; z < data.z; z++) {

                    IloLinearNumExpr Exp48 = cplex.linearNumExpr();
                    for (int i = 0; i < data.z; i++)
                        for (int k = 0; k < data.k; k++)
                            Exp48.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k), 1);

                    constraints[48][counter] = cplex.addLe(Exp48,
                            data.DE.get("z" + z, "t" + t));

                    IloLinearNumExpr Exp49 = cplex.linearNumExpr();
                    for (int i = 0; i < data.z; i++)
                        for (int k = 0; k < data.k; k++)
                            Exp49.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k), 1);

                    constraints[48][counter] = cplex.addLe(Exp48,
                            data.BE.get("be0") * data.DE.get("z" + z, "t" + t));

                    counter++;
                }
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
