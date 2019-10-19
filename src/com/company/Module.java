package com.company;

import MultiMap.HashMapAmir;
import MultiMap.MapAmir;
import ilog.concert.*;
import ilog.cplex.IloCplex;

import java.util.ArrayList;

public class Module {


    //region Varibles
    public HashMapAmir<String, IloNumVar> WH;
    public HashMapAmir<String, IloNumVar> FF;
    public HashMapAmir<String, IloNumVar> Q;
    public HashMapAmir<String, IloNumVar> DA;
    public HashMapAmir<String, IloNumVar> RF;
    public HashMapAmir<String, IloNumVar> RM;
    public HashMapAmir<String, IloNumVar> TR1;
    public HashMapAmir<String, IloNumVar> TR2;
    public HashMapAmir<String, IloNumVar> TR3;
    public HashMapAmir<String, IloNumVar> X;
    public HashMapAmir<String, IloNumVar> XM;
    public HashMapAmir<String, IloNumVar> Sh;
    public HashMapAmir<String, IloNumVar> Sm;
    public HashMapAmir<String, IloNumVar> Sl;
    public HashMapAmir<String, IloNumVar> Sn;
    public HashMapAmir<String, IloNumVar> NTC;
    public HashMapAmir<String, IloNumVar> ENCT;
    public HashMapAmir<String, IloNumVar> ENC;
    public HashMapAmir<String, IloNumVar> TFC;
    public HashMapAmir<String, IloNumVar> TOC;
    public HashMapAmir<String, IloNumVar> TTC;
    public HashMapAmir<String, IloNumVar> TPC;
    public HashMapAmir<String, IloNumVar> BEN;
    public HashMapAmir<String, IloNumVar> TEN;
    public HashMapAmir<String, IloNumVar> PEN;
    public HashMapAmir<String, IloNumVar> EN;
    public HashMapAmir<String, IloNumVar> FC;
    public HashMapAmir<String, IloNumVar> P;
    public HashMapAmir<String, IloNumVar> At;
    public HashMapAmir<String, IloNumVar> W;
    public Double BN = Double.MAX_VALUE;
    public IloCplex cplex;
    public IloLinearNumExpr obj;
    //endregion

    public IloRange[][] constraints;
    private Data data;
    public ArrayList<HashMapAmir> ListVarible = new ArrayList<>();

    public Module(Data data) {


        this.data = data;
        data.ReadData();
        initVariables();
        initConstraints();
    }


    public void initConstraints() {
        try {

            //region Objects Value
            obj = cplex.linearNumExpr();
            obj.addTerm(1, NTC.get("q0"));
            obj.addTerm(1, ENCT.get("q0"));
            cplex.addMinimize(obj, "object");
            //endregion

            //region 2
            IloLinearNumExpr Con2 = cplex.linearNumExpr();

            Con2.addTerm(1, TFC.get("q0"));
            Con2.addTerm(1, TTC.get("q0"));
            Con2.addTerm(1, TOC.get("q0"));
            Con2.addTerm(1, TPC.get("q0"));
            Con2.addTerm(-1, BEN.get("q0"));
            Con2.addTerm(-1, NTC.get("q0"));

            constraints[2] = new IloRange[1];
            constraints[2][0] = cplex.addEq(Con2, 0, "constraint_2");
            //endregion

            //region 3
            IloLinearNumExpr Con3 = cplex.linearNumExpr();
            Con3.addTerm(-1, TFC.get("q0"));

            double r = 1 + data.R.get("r0");
            for (int t = 0; t < data.t; t++) {
                double rt = Math.pow(r, -1 * t);
                Con3.addTerm(rt, FC.get("t" + t));
            }

            constraints[3] = new IloRange[1];
            constraints[3][0] = cplex.addEq(Con3, 0, "constraint_3");
            //endregion

            //region 4
            constraints[4] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {
                IloLinearNumExpr Exp = cplex.linearNumExpr();
                Exp.addTerm(-1, FC.get("t" + t));

                for (int f = 0; f < data.f; f++)
                    for (int ho = 0; ho < data.ho; ho++)
                        for (int o = 0; o < data.o; o++)
                            Exp.addTerm(FF.get("f" + f, "h" + ho, "o" + o, "t" + t), data.FA.get("f" + f, "h" + ho, "t" + t, "o" + o));

                for (int i = 0; i < data.i; i++)
                    for (int hi = 0; hi < data.hi; hi++)
                        Exp.addTerm(WH.get("i" + i, "h" + hi, "t" + t), data.FW.get("i" + i, "h" + hi, "t" + t));


                for (int d = 0; d < data.d; d++)
                    for (int hd = 0; hd < data.hd; hd++)
                        Exp.addTerm(
                                DA.get("d" + d, "h" + hd, "t" + t), data.FD.get("d" + d, "h" + hd, "t" + t));

                for (int n = 0; n < data.n; n++)
                    for (int hn = 0; hn < data.hd; hn++)
                        Exp.addTerm(RF.get("n" + n, "h" + hn, "t" + t), data.FR.get("n" + n, "h" + hn, "t" + t));

                for (int m = 0; m < data.m; m++)
                    for (int hm = 0; hm < data.hm; hm++)
                        Exp.addTerm(RM.get("m" + m, "h" + hm, "t" + t)
                                , data.FM.get("m" + m, "h" + hm, "t" + t));

                for (int q = 0; q < data.q; q++)
                    for (int hq = 0; hq < data.hq; hq++)
                        Exp.addTerm(Q.get("q" + q, "h" + hq, "t" + t)
                                , data.FQ.get("q" + q, "h" + hq, "t" + t));

                constraints[4][t] = cplex.addEq(Exp, 0, GenConstrint(4, "t", t));
            }
            //endregion

            //region 5
            constraints[5] = new IloRange[1];
            IloLinearNumExpr Exp5 = cplex.lqNumExpr();
            Exp5.addTerm(-1, TTC.get("q0"));

            for (int t = 0; t < data.t; t++) {
                double tr = Math.pow((1 + data.R.get("r0")), -1 * t);
                for (int k = 0; k < data.k; k++) {

                    for (int j = 0; j < data.j; j++)
                        for (int s = 0; s < data.s; s++)
                            for (int f = 0; f < data.f; f++)

                                Exp5.addTerm(XM.get("j" + j, "s" + s, "f" + f, "t" + t, "k" + k),
                                        tr * data.CJ.get("j" + j, "k" + k, "t" + t) *
                                                data.DS.get("s" + s, "f" + f));

                    for (int f = 0; f < data.f; f++)
                        for (int i = 0; i < data.i; i++)

                            Exp5.addTerm(X.get("f" + f, "i" + i, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("f" + f, "i" + i));

                    for (int i = 0; i < data.i; i++)
                        for (int z = 0; z < data.z; z++) {

                            Exp5.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "z" + z));

                            Exp5.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "z" + z));
                        }

                    for (int i = 0; i < data.i; i++)
                        for (int d = 0; d < data.d; d++)

                            Exp5.addTerm(X.get("i" + i, "d" + d, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "d" + d));

                    for (int i = 0; i < data.i; i++)
                        for (int n = 0; n < data.n; n++) {

                            Exp5.addTerm(X.get("i" + i, "n" + n, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "n" + n));

                            Exp5.addTerm(X.get("n" + n, "i" + i, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "n" + n));
                        }

                    for (int i = 0; i < data.i; i++)
                        for (int m = 0; m < data.m; m++) {
                            Exp5.addTerm(X.get("i" + i, "m" + m, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "m" + m));

                            Exp5.addTerm(X.get("m" + m, "i" + i, "t" + t, "k" + k),
                                    tr * data.C.get("k" + k, "t" + t) *
                                            data.DS.get("i" + i, "m" + m));
                        }

                    for (int j = 0; j < data.j; j++) {
                        for (int d = 0; d < data.d; d++) {

                            for (int f = 0; f < data.f; f++)
                                Exp5.addTerm(XM.get("j" + j, "d" + d, "f" + f, "t" + t, "k" + k),
                                        tr * data.CJ.get("j" + j, "k" + k, "t" + t) *
                                                data.DS.get("d" + d, "f" + f));

                            for (int q = 0; q < data.q; q++)
                                Exp5.addTerm(XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k),
                                        tr * data.CJ.get("j" + j, "k" + k, "t" + t) *
                                                data.DS.get("d" + d, "q" + q));


                        }

                        for (int s = 0; s < data.s; s++)
                            for (int m = 0; m < data.m; m++)
                                Exp5.addTerm(XM.get("j" + j, "s" + s, "m" + m, "t" + t, "k" + k),
                                        tr * data.CJ.get("j" + j, "k" + k, "t" + t) *
                                                data.DS.get("s" + s, "m" + m));
                    }
                }
            }
            constraints[5][0] = cplex.addEq(Exp5, 0, "constraint_5");
            //endregion

            //region 6
            constraints[6] = new IloRange[1];
            IloLinearNumExpr EXP6 = cplex.linearNumExpr();
            EXP6.addTerm(TOC.get("q0"), -1);

            for (int t = 0; t < data.t; t++) {

                double rt = Math.pow(1 + data.R.get("r0"), -1 * t);

                for (int f = 0; f < data.f; f++)
                    for (int o = 0; o < data.o; o++)
                        EXP6.addTerm(P.get("f" + f, "o" + o, "t" + t),
                                data.OC.get("o" + o, "t" + t) * rt);

                for (int i = 0; i < data.i; i++)
                    for (int k = 0; k < data.k; k++) {

                        for (int z = 0; z < data.z; z++) {
                            EXP6.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k), rt * data.OCI.get("t" + t));
                            EXP6.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k), rt * data.OCI.get("t" + t));
                            EXP6.addTerm(Sh.get("i" + i, "t" + t), rt * data.OCI.get("t" + t));
                            EXP6.addTerm(Sm.get("i" + i, "t" + t), rt * data.OCI.get("t" + t));
                            EXP6.addTerm(Sl.get("i" + i, "t" + t), rt * data.OCI.get("t" + t));
                            EXP6.addTerm(Sn.get("i" + i, "t" + t), rt * data.OCI.get("t" + t));
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

            constraints[6][0] = cplex.addEq(EXP6, 0, "constraint_6");
            //endregion

            //region 7
            constraints[7] = new IloRange[1];
            IloLinearNumExpr Exp7 = cplex.linearNumExpr();

            Exp7.addTerm(TPC.get("q0"), -1);

            for (int t = 0; t < data.t; t++)
                for (int k = 0; k < data.k; k++) {
                    double tr = Math.pow(1 + data.R.get("r0"), -1 * t);
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
            constraints[7][0] = cplex.addEq(Exp7, 0, "constraint_7");

            //endregion

            //region 8
            constraints[8] = new IloRange[1];
            IloLinearNumExpr Exp8 = cplex.linearNumExpr();
            Exp8.addTerm(BEN.get("q0"), -1);

            for (int t = 0; t < data.t; t++) {
                double tr = Math.pow(1 + data.R.get("r0"), -t);
                for (int k = 0; k < data.k; k++) {

                    for (int i = 0; i < data.i; i++)
                        for (int z = 0; z < data.z; z++)
                            Exp8.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k)
                                    , tr * data.PZ.get("t" + t));

                    for (int j = 0; j < data.j; j++)
                        for (int d = 0; d < data.d; d++)
                            for (int q = 0; q < data.q; q++)
                                Exp8.addTerm(XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k)
                                        , tr * data.PQ.get("j" + j, "t" + t));


                }
            }

            constraints[8][0] = cplex.addEq(Exp8, 0, "constraint_8");
            //endregion

            //region 9
            constraints[9] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {

                constraints[9][t] = cplex.addEq(cplex.sum(TEN.get("t" + t), PEN.get("t" + t), cplex.prod(-1, EN.get("t" + t)))
                        , 0, "constraint_9");
            }
            //endregion

            //region 10
            constraints[10] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {
                IloLinearNumExpr Exp10 = cplex.linearNumExpr();
                Exp10.addTerm(-1, TEN.get("t" + t));

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
                            Exp10.addTerm(X.get("f" + f, "i" + i, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("f" + f, "i" + i));


                    for (int i = 0; i < data.i; i++)
                        for (int z = 0; z < data.z; z++) {
                            Exp10.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("i" + i, "z" + z));

                            Exp10.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("i" + i, "z" + z));

                        }

                    for (int i = 0; i < data.i; i++)
                        for (int d = 0; d < data.d; d++)
                            Exp10.addTerm(X.get("i" + i, "d" + d, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("i" + i, "d" + d));

                    for (int i = 0; i < data.i; i++)
                        for (int n = 0; n < data.n; n++) {
                            Exp10.addTerm(X.get("i" + i, "n" + n, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("i" + i, "n" + n));
                            Exp10.addTerm(X.get("n" + n, "i" + i, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("i" + i, "n" + n));

                        }

                    for (int i = 0; i < data.i; i++)
                        for (int m = 0; m < data.m; m++) {
                            Exp10.addTerm(X.get("i" + i, "m" + m, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("i" + i, "m" + m));

                            Exp10.addTerm(X.get("m" + m, "i" + i, "t" + t, "k" + k),
                                    data.A.get("k" + k) * data.DS.get("i" + i, "m" + m));
                        }

                }

                constraints[10][t] = cplex.addEq(Exp10, 0, "constraint_10(t" + t + ")");
            }
            //endregion

            //region 11
            constraints[11] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {

                IloLinearNumExpr Exp11 = cplex.linearNumExpr();
                Exp11.addTerm(PEN.get("t" + t), -1);

                for (int o = 0; o < data.o; o++)
                    for (int f = 0; f < data.f; f++)
                        Exp11.addTerm(P.get("f" + f, "o" + o, "t" + t), data.A.get("o" + o));

                constraints[11][0] = cplex.addEq(Exp11, 0, "constraint_11");
            }
            //endregion

            //region 12
            constraints[12] = new IloRange[1];
            IloLinearNumExpr Exp12 = cplex.linearNumExpr();
            Exp12.addTerm(-1, ENCT.get("q0"));
            for (int t = 0; t < data.t; t++)
                Exp12.addTerm(Math.pow(1 + data.R.get("r0"), t), ENC.get("t" + t));
            constraints[12][0] = cplex.addEq(Exp12, 0, "constraint_12");
            //endregion

            //region 13
            constraints[13] = new IloRange[data.t];

            for (int t = 0; t < data.t; t++) {
                IloLinearNumExpr Exp13 = cplex.linearNumExpr();
                Exp13.addTerm(-1, ENC.get("t" + t));

                for (int e = 0; e < data.e; e++)
                    Exp13.addTerm(At.get("e" + e, "t" + t), data.RE.get("e" + e));

                constraints[13][t] = cplex.addEq(Exp13, 0, "constraint_13(t" + t + ")");
            }
            //endregion

            //region 14
            constraints[14] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {
                IloLinearNumExpr Exp14 = cplex.linearNumExpr();
                Exp14.addTerm(-1, EN.get("t" + t));

                for (int e = 0; e < data.e; e++)
                    Exp14.addTerm(At.get("e" + e, "t" + t), 1);

                constraints[14][t] = cplex.addEq(Exp14, 0, "constraint_14(t" + t + ")");
            }
            //endregion

            //region 15
            constraints[15] = new IloRange[2 * data.t];
            for (int t = 0; t < data.t; t++) {

                constraints[15][t] = cplex.addLe(At.get("e0", "t" + t), data.CU.get("e0"),
                        "constraint_15_0(t" + t + ")");

                IloLinearNumExpr Exp15 = cplex.linearNumExpr();
                Exp15.addTerm(1, At.get("e0", "t" + t));
                Exp15.addTerm(-1 * data.CU.get("e0"), W.get("e0", "t" + t));
                constraints[15][t + 1] = cplex.addGe(0, Exp15, "constraint_15_1(t" + t + ")");
            }
            //endregion

            //region 16
            constraints[16] = new IloRange[(2 * data.e - 4) * data.t];
            int counter = 0;
            for (int e = 1; e < data.e - 1; e++)
                for (int t = 0; t < data.t; t++) {

                    IloLinearNumExpr Exp151 = cplex.linearNumExpr();
                    Exp151.addTerm(-1, At.get("e" + e, "t" + t));
                    Exp151.addTerm(data.CU.get("e" + e) - data.CU.get("e" + (e - 1)),
                            W.get("e" + (e - 1), "t" + t));
                    constraints[16][counter * 2] = cplex.addGe(Exp151, 0, GenConstrint(15, "q", counter * 2));

                    IloLinearNumExpr Exp152 = cplex.linearNumExpr();
                    Exp152.addTerm(-1, At.get("e" + e, "t" + t));
                    Exp152.addTerm(data.CU.get("e" + e) - data.CU.get("e" + (e - 1))
                            , W.get("e" + e, "t" + t));
                    constraints[16][counter * 2 + 1] = cplex.addLe(Exp152, 0, GenConstrint(15, "q", counter * 2 + 1));
                    counter++;
                }
            //endregion

            //region 17
            constraints[17] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {

                IloLinearNumExpr Exp17 = cplex.linearNumExpr();
                Exp17.addTerm(-1, At.get("e" + (data.e - 1), "t" + t));
                Exp17.addTerm(BN, W.get("e" + (data.e - 2), "t" + t));
                constraints[17][t] = cplex.addGe(Exp17, 0, "constraint_17(t" + t + ")");
            }
            //endregion

            //region 18
            constraints[18] = new IloRange[data.j * data.f * data.t];
            counter = 0;
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
                        constraints[18][counter] = cplex.addEq(Exp18, 0, GenConstrint(18, "q", counter));
                    }
            //endregion

            //region 19 - 20
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
                                        X.get("i" + i, "d" + d, "t" + t, "k" + k));

                        }
                        constraints[19][counter] = cplex.addEq(Exp19, 0, GenConstrint(19, "q", counter));

                        IloLinearNumExpr Exp20 = cplex.linearNumExpr();
                        for (int k = 0; k < data.k; k++) {
                            for (int q = 0; q < data.q; q++)
                                Exp20.addTerm(1, XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k));

                            for (int i = 0; i < data.i; i++)
                                Exp20.addTerm(-1 * (1 - data.G1.get("j" + j)) * data.RM.get("j" + j),
                                        X.get("i" + i, "d" + d, "t" + t, "k" + k));

                        }
                        constraints[20][counter] = cplex.addEq(Exp20, 0, GenConstrint(20, "q", counter));
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
                    constraints[21][counter] = cplex.addEq(Exp21, 0, GenConstrint(21, "q", counter));
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
                    constraints[22][counter] = cplex.addEq(Exp22, 0, GenConstrint(22, "q", counter));
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
                        constraints[23][counter] = cplex.addEq(Exp23, 0, GenConstrint(23, "q", counter));
                        counter++;
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
                    Exp24.addTerm(Sn.get("i" + i, "t" + t), 1);
                    if (t != 0) {
                        Exp24.addTerm(Sn.get("i" + i, "t" + (t - 1)), -1);
                    }
                    for (int k = 0; k < data.k; k++) {

                        for (int f = 0; f < data.f; f++)
                            Exp24.addTerm(X.get("f" + f, "i" + i, "t" + t, "k" + k), 1);

                        for (int m = 0; m < data.m; m++)
                            Exp24.addTerm(X.get("m" + m, "i" + i, "t" + t, "k" + k), 1);

                        for (int n = 0; n < data.n; n++)
                            Exp24.addTerm(X.get("n" + n, "i" + i, "t" + t, "k" + k), 1);

                        for (int z = 0; z < data.z; z++)
                            Exp24.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k), -1);
                    }
                    constraints[24][counter] = cplex.addEq(Exp24, 0, GenConstrint(24, "q", counter));
                    //endregion

                    //region 25
                    IloLinearNumExpr Exp25 = cplex.linearNumExpr();
                    if (t != 0)
                        Exp25.addTerm(1, Sh.get("i" + i, "t" + (t - 1)));
                    Exp25.addTerm(-1, Sh.get("i" + i, "t" + t));
                    for (int k = 0; k < data.k; k++) {
                        for (int z = 0; z < data.z; z++)
                            Exp25.addTerm(-1 * data.L1.get("l0"), X.get("z" + z, "i" + i, "t" + t, "k" + k));
                        for (int n = 0; n < data.n; n++)
                            Exp25.addTerm(1, X.get("i" + i, "n" + n, "t" + t, "k" + k));
                    }
                    constraints[25][counter] = cplex.addEq(Exp25, 0, GenConstrint(25, "q", counter));
                    //endregion

                    //region 26
                    IloLinearNumExpr Exp26 = cplex.linearNumExpr();
                    if (t != 0)
                        Exp26.addTerm(1, Sm.get("i" + i, "t" + (t - 1)));
                    Exp26.addTerm(-1, Sm.get("i" + i, "t" + t));
                    for (int k = 0; k < data.k; k++) {
                        for (int z = 0; z < data.z; z++)
                            Exp26.addTerm(1 * data.L2.get("l0"), X.get("z" + z, "i" + i, "t" + t, "k" + k));
                        for (int m = 0; m < data.m; m++)
                            Exp26.addTerm(-1, X.get("i" + i, "m" + m, "t" + t, "k" + k));
                    }
                    constraints[26][counter] = cplex.addEq(Exp26, 0, GenConstrint(26, "q", counter));
                    //endregion

                    //region 27
                    IloLinearNumExpr Exp27 = cplex.linearNumExpr();
                    double L3 = 1 - data.L2.get("l0") - data.L1.get("l0");
                    if (t != 0)
                        Exp27.addTerm(1, Sl.get("i" + i, "t" + (t - 1)));
                    Exp27.addTerm(-1, Sl.get("i" + i, "t" + t));
                    for (int k = 0; k < data.k; k++) {
                        for (int z = 0; z < data.z; z++)
                            Exp27.addTerm(1 * L3, X.get("z" + z, "i" + i, "t" + t, "k" + k));
                        for (int d = 0; d < data.d; d++)
                            Exp27.addTerm(-1, X.get("i" + i, "d" + d, "t" + t, "k" + k));
                    }
                    constraints[27][counter] = cplex.addEq(Exp27, 0, GenConstrint(27, "q", counter));
                    //endregion

                    counter++;
                }
            //endregion

            //region 28
            constraints[28] = new IloRange[data.f * data.t];
            counter = 0;
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
                            for (int tf = 0; tf <= t; tf++) {
                                Exp28.addTerm(-1 * data.CO.get("ho" + h, "o" + o),
                                        FF.get("f" + f, "h" + h, "o" + o, "t" + tf));
                            }
                    constraints[28][counter] = cplex.addLe(Exp28, 0, GenConstrint(28, "q", counter));
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
                            Exp29.addTerm(1,
                                    FF.get("f" + f, "h" + h, "o" + o, "t" + t));
                        }
                constraints[29][f] = cplex.addLe(Exp29, 1, GenConstrint(29, "q", f));
            }
            //endregion

            //region 30
            constraints[30] = new IloRange[data.i * data.t];
            counter = 0;
            for (int i = 0; i < data.i; i++)
                for (int t = 0; t < data.t; t++) {
                    IloLinearNumExpr Exp30 = cplex.linearNumExpr();
                    Exp30.addTerm(1, Sn.get("i" + i, "t" + t));
                    Exp30.addTerm(1, Sh.get("i" + i, "t" + t));
                    Exp30.addTerm(1, Sm.get("i" + i, "t" + t));
                    Exp30.addTerm(1, Sl.get("i" + i, "t" + t));

                    for (int k = 0; k < data.k; k++)
                        for (int z = 0; z < data.z; z++) {
                            Exp30.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k), 1);
                            Exp30.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k), 1);

                        }
                    for (int h = 0; h < data.hi; h++)
                        for (int tf = 0; tf <= t; tf++) {
                            Exp30.addTerm(-1 * data.CA.get("hi" + h), WH.get("i" + i, "h" + h, "t" + tf));
                        }
                    constraints[30][counter] = cplex.addLe(Exp30, 0, GenConstrint(30, "q", counter));
                    counter++;
                }
            //endregion

            //region 32
            constraints[32] = new IloRange[data.i];
            for (int i = 0; i < data.i; i++) {
                IloLinearNumExpr Exp32 = cplex.linearNumExpr();
                for (int h = 0; h < data.hi; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp32.addTerm(WH.get("i" + i, "h" + h, "t" + t), 1);
                constraints[32][i] = cplex.addLe(Exp32, 1, GenConstrint(32, "i", i));
            }
            //endregion

            //region 34
            constraints[34] = new IloRange[data.d * data.t];
            counter = 0;
            for (int t = 0; t < data.t; t++)
                for (int d = 0; d < data.d; d++) {
                    IloLinearNumExpr Exp34 = cplex.linearNumExpr();

                    for (int i = 0; i < data.i; i++) {
                        for (int k = 0; k < data.k; k++)
                            Exp34.addTerm(X.get("i" + i, "d" + d, "t" + t, "k" + k)
                                    , 1);

                        for (int h = 0; h < data.hd; h++)
                            for (int tf = 0; tf <= t; tf++)
                                Exp34.addTerm(-1 * data.CA.get("hd" + h),
                                        DA.get("d" + d, "h" + h, "t" + tf));
                    }
                    constraints[34][counter] = cplex.addLe(Exp34, 0, GenConstrint(34, "q", counter));
                    counter++;

                }
            //endregion

            //region 35
            constraints[35] = new IloRange[data.d];
            for (int d = 0; d < data.d; d++) {

                IloLinearNumExpr Exp35 = cplex.linearNumExpr();
                for (int h = 0; h < data.hd; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp35.addTerm(DA.get("d" + d, "h" + h, "t" + t), 1);
                constraints[35][d] = cplex.addLe(Exp35, 1, GenConstrint(35, "d", d));
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
                        for (int tf = 0; tf <= t; tf++)
                            Exp37.addTerm(RF.get("n" + n, "h" + h, "t" + tf),
                                    -1 * data.CA.get("hn" + h));
                    constraints[37][counter] = cplex.addGe(0, Exp37, GenConstrint(37, "q", counter));
                    counter++;
                }
            //endregion

            //region 38
            constraints[38] = new IloRange[data.n];
            for (int n = 0; n < data.n; n++) {
                IloLinearNumExpr Exp38 = cplex.linearNumExpr();

                for (int h = 0; h < data.hn; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp38.addTerm(1, RF.get("n" + n, "h" + h, "t" + t));

                constraints[38][n] = cplex.addLe(Exp38, 1, GenConstrint(38, "n", n));
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
                        for (int tf = 0; tf <= t; tf++)
                            Exp40.addTerm(RM.get("m" + m, "h" + h, "t" + tf),
                                    -1 * data.CA.get("hm" + h));
                    constraints[40][counter] = cplex.addGe(0, Exp40, GenConstrint(40, "q", counter));
                    counter++;
                }
            //endregion

            //region 41
            constraints[41] = new IloRange[data.m];
            for (int m = 0; m < data.m; m++) {
                IloLinearNumExpr Exp41 = cplex.linearNumExpr();

                for (int h = 0; h < data.hn; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp41.addTerm(1, RM.get("m" + m, "h" + h, "t" + t));

                constraints[41][m] = cplex.addLe(Exp41, 1, GenConstrint(41, "m", m));
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
                            for (int j = 0; j < data.j; j++)
                                Exp43.addTerm(1, XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k));
                    for (int h = 0; h < data.hq; h++)
                        for (int tf = 0; tf <= t; tf++)
                            Exp43.addTerm(Q.get("q" + q, "h" + h, "t" + tf)
                                    ,
                                    -1 * data.CA.get("hq" + h));
                    constraints[43][counter] = cplex.addGe(0, Exp43, GenConstrint(43, "q", counter));
                    counter++;
                }
            //endregion

            //region 44
            constraints[44] = new IloRange[data.q];
            for (int q = 0; q < data.q; q++) {
                IloLinearNumExpr Exp44 = cplex.linearNumExpr();

                for (int h = 0; h < data.hq; h++)
                    for (int t = 0; t < data.t; t++)
                        Exp44.addTerm(1, Q.get("q" + q, "h" + h, "t" + t)
                        );

                constraints[44][q] = cplex.addLe(Exp44, 1, GenConstrint(44, "q", q));
            }
            //endregion

            //region 46 prim
            constraints[46] = new IloRange[data.t];
            for (int t = 0; t < data.t; t++) {
                IloLinearNumExpr Exp46 = cplex.linearNumExpr();
                Exp46.addTerm(FC.get("t" + t), 1);
                Double BU = data.BU.get("t" + t);
                for (int g = 0; g < t; g++) {
                    Double rate = Math.pow(1 + data.R.get("r0"), g);
                    Exp46.addTerm(rate, FC.get("t" + g));
                    BU += rate * data.BU.get("t" + g);
                }
                constraints[46][t] = cplex.addLe(Exp46, BU, GenConstrint(46, "t", t));
            }
            //endregion

            //region 48 49
            constraints[48] = new IloRange[data.t * data.z];
            constraints[49] = new IloRange[data.t * data.z];
            counter = 0;
            for (int t = 0; t < data.t; t++)
                for (int z = 0; z < data.z; z++) {

                    IloLinearNumExpr Exp48 = cplex.linearNumExpr();
                    for (int i = 0; i < data.i; i++)
                        for (int k = 0; k < data.k; k++)
                            Exp48.addTerm(X.get("i" + i, "z" + z, "t" + t, "k" + k), 1);

                    constraints[48][counter] = cplex.addGe(Exp48,
                            data.DE.get("z" + z, "t" + t), GenConstrint(48, "q", counter));

                    IloLinearNumExpr Exp49 = cplex.linearNumExpr();
                    for (int i = 0; i < data.i; i++)
                        for (int k = 0; k < data.k; k++)
                            Exp49.addTerm(X.get("z" + z, "i" + i, "t" + t, "k" + k), 1);

                    constraints[49][counter] = cplex.addGe(Exp49,
                            data.BE.get("be0") * data.DE.get("z" + z, "t" + t), GenConstrint(49, "q", counter));

                    counter++;
                }
            //endregion

            //region 50 52 54
            constraints[50] = new IloRange[1];
            constraints[52] = new IloRange[1];
            constraints[54] = new IloRange[1];

            IloLinearNumExpr Exp50 = cplex.linearNumExpr();
            IloLinearNumExpr Exp52 = cplex.linearNumExpr();
            IloLinearNumExpr Exp54 = cplex.linearNumExpr();

            for (int k = 0; k < data.k; k++) {
                Exp50.addTerm(TR1.get("k" + k), 1);
                Exp52.addTerm(TR2.get("k" + k), 1);
                Exp54.addTerm(TR3.get("k" + k), 1);
            }

            constraints[50][0] = cplex.addEq(Exp50, 1, "constraints 50");
            constraints[52][0] = cplex.addEq(Exp52, 1, "constraints 52");
            constraints[54][0] = cplex.addEq(Exp54, 1, "constraints 54");
            //endregion

            //region 51 53 55
            constraints[51] = new IloRange[data.k];
            constraints[53] = new IloRange[data.k];
            constraints[55] = new IloRange[data.k];

            for (int k = 0; k < data.k; k++) {

                //region 51
                IloLinearNumExpr Exp51 = cplex.linearNumExpr();
                for (int j = 0; j < data.j; j++)
                    for (int t = 0; t < data.t; t++) {

                        for (int s = 0; s < data.s; s++)
                            for (int f = 0; f < data.f; f++)
                                Exp51.addTerm(XM.get("j" + j, "s" + s, "f" + f, "t" + t, "k" + k), 1);

                        for (int s = 0; s < data.s; s++)
                            for (int m = 0; m < data.m; m++)
                                Exp51.addTerm(XM.get("j" + j, "s" + s, "m" + m, "t" + t, "k" + k), 1);

                        for (int d = 0; d < data.d; d++)
                            for (int f = 0; f < data.f; f++)
                                Exp51.addTerm(XM.get("j" + j, "d" + d, "f" + f, "t" + t, "k" + k), 1);
                    }
                Exp51.addTerm(TR1.get("k" + k), -1 * BN);
                constraints[51][k] = cplex.addLe(Exp51, 0, GenConstrint(51, "k", k));
                //endregion

                //region 53
                IloLinearNumExpr Exp53 = cplex.linearNumExpr();
                for (int t = 0; t < data.t; t++) {

                    for (int n = 0; n < data.n; n++)
                        for (int i = 0; i < data.i; i++) {
                            Exp53.addTerm(1, X.get("i" + i, "n" + n, "t" + t, "k" + k));
                            Exp53.addTerm(1, X.get("n" + n, "i" + i, "t" + t, "k" + k));
                        }

                    for (int m = 0; m < data.m; m++)
                        for (int i = 0; i < data.i; i++) {
                            Exp53.addTerm(1, X.get("i" + i, "m" + m, "t" + t, "k" + k));
                            Exp53.addTerm(1, X.get("m" + m, "i" + i, "t" + t, "k" + k));
                        }

                    for (int z = 0; z < data.z; z++)
                        for (int i = 0; i < data.i; i++) {
                            Exp53.addTerm(1, X.get("i" + i, "z" + z, "t" + t, "k" + k));
                            Exp53.addTerm(1, X.get("z" + z, "i" + i, "t" + t, "k" + k));
                        }


                    for (int f = 0; f < data.f; f++)
                        for (int i = 0; i < data.i; i++) {
                            Exp53.addTerm(1, X.get("f" + f, "i" + i, "t" + t, "k" + k));
                        }
                }
                Exp53.addTerm(TR2.get("k" + k), -1 * BN);
                constraints[53][k] = cplex.addLe(Exp53, 0, GenConstrint(53, "k", k));
                //endregion

                //region 55
                IloLinearNumExpr Exp55 = cplex.linearNumExpr();
                for (int j = 0; j < data.j; j++)
                    for (int d = 0; d < data.d; d++)
                        for (int q = 0; q < data.q; q++)
                            for (int t = 0; t < data.t; t++)
                                Exp55.addTerm(XM.get("j" + j, "d" + d, "q" + q, "t" + t, "k" + k), 1);
                Exp55.addTerm(TR3.get("k" + k), -1 * BN);
                constraints[55][k] = cplex.addLe(Exp55, 0, GenConstrint(55, "k", k));
                //endregion

            }
            //endregion

            //region 56
            constraints[56] = new IloRange[data.f * data.t * data.o];
            counter = 0;
            for (int f = 0; f < data.f; f++)
                for (int t = 0; t < data.t; t++) {

                    IloLinearNumExpr Exp56 = cplex.linearNumExpr();
                    for (int o = 0; o < data.o; o++)
                        Exp56.addTerm(P.get("f" + f, "o" + o, "t" + t), 1);

                    for (int i = 0; i < data.i; i++)
                        for (int k = 0; k < data.k; k++)
                            Exp56.addTerm(X.get("f" + f, "i" + i, "t" + t, "k" + k), -1);
                    constraints[56][counter] = cplex.addEq(Exp56, 0, GenConstrint(56, "q", counter));
                    counter++;
                }
            //endregion

            //region 57
            constraints[57] = new IloRange[data.o * data.t * data.f];
            counter = 0;
            for (int o = 0; o < data.o; o++)
                for (int t = 0; t < data.t; t++)
                    for (int f = 0; f < data.f; f++) {

                        IloLinearNumExpr Exp57 = cplex.linearNumExpr();
                        Exp57.addTerm(1, P.get("f" + f, "o" + o, "t" + t));

                        for (int h = 0; h < data.ho; h++)
                            for (int tf = 0; tf <= t; tf++) {
                                Exp57.addTerm(-1 * BN,
                                        FF.get("f" + f, "h" + h, "o" + o, "t" + tf));
                            }
                        constraints[57][counter] = cplex.addLe(Exp57, 0, GenConstrint(57, "q", counter));
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
            constraints = new IloRange[58][];
            NTC = new HashMapAmir<>(1, "NTC");
            NTC.put(cplex.numVar(-1 * Double.MAX_VALUE, Double.MAX_VALUE, "NTC"), "q0");
            ENCT = new HashMapAmir<>(1, "ENCT");
            ENCT.put(cplex.numVar(0, Double.MAX_VALUE, "ENCT"), "q0");
            TFC = new HashMapAmir<>(1, "TFC");
            TFC.put(cplex.numVar(0, Double.MAX_VALUE, "TFC"), "q0");
            TOC = new HashMapAmir<>(1, "TOC");
            TOC.put(cplex.numVar(0, Double.MAX_VALUE, "TOC"), "q0");
            TTC = new HashMapAmir<>(1, "TTC");
            TTC.put(cplex.numVar(0, Double.MAX_VALUE, "TTC"), "q0");
            TPC = new HashMapAmir<>(1, "TPC");
            TPC.put(cplex.numVar(0, Double.MAX_VALUE, "TPC"), "q0");
            BEN = new HashMapAmir<>(1, "BEN");
            BEN.put(cplex.numVar(0, Double.MAX_VALUE, "BEN"), "q0");
            TEN = new HashMapAmir<>(1, "TEN");
            for (int t = 0; t < data.t; t++)
                TEN.put(cplex.numVar(0, Double.MAX_VALUE, "TEN(t" + t + ")"), "t" + t);
            PEN = new HashMapAmir<>(1, "PEN");
            for (int t = 0; t < data.t; t++)
                PEN.put(cplex.numVar(0, Double.MAX_VALUE, "PEN(t" + t + ")"), "t" + t);

            EN = new HashMapAmir<>(1, "EN");
            for (int t = 0; t < data.t; t++)
                EN.put(cplex.numVar(0, Double.MAX_VALUE, "EN(t" + t + ")"), "t" + t);


            FC = new HashMapAmir<>(1, "FC");
            for (int t = 0; t < data.t; t++)
                FC.put(cplex.numVar(0, Double.MAX_VALUE, GenName("FC", "t", t)), "t" + t);

            ENC = new HashMapAmir<>(1, "ENC");
            for (int t = 0; t < data.t; t++)
                ENC.put(cplex.numVar(0, Double.MAX_VALUE, GenName("ENC", "t", t)), "t" + t);


            // variable
            At = new HashMapAmir<>(2, "At");
            for (int e = 0; e < data.e; e++)
                for (int t = 0; t < data.t; t++)
                    At.put(cplex.numVar(0, Double.MAX_VALUE, GenName("At", "te", e, t)), "e" + e, "t" + t);

            W = new HashMapAmir<>(2, "W");
            for (int e = 0; e < data.e - 1; e++)
                for (int t = 0; t < data.t; t++)
                    W.put(cplex.boolVar(GenName("W", "et", e, t)), "e" + e, "t" + t);

            WH = new HashMapAmir<>(3, "WH");
            for (int i = 0; i < data.i; i++)
                for (int h = 0; h < data.hi; h++)
                    for (int t = 0; t < data.t; t++)
                        WH.put(cplex.boolVar(GenName("WH", "iht", i, h, t)), "i" + i, "h" + h, "t" + t);


            FF = new HashMapAmir<>(4, "FF");
            for (int f = 0; f < data.f; f++)
                for (int h = 0; h < data.ho; h++)
                    for (int o = 0; o < data.o; o++)
                        for (int t = 0; t < data.t; t++)
                            FF.put(cplex.boolVar(GenName("FF", "fhot", f, h, o, t)), "f" + f, "h" + h, "o" + o, "t" + t);

            Q = new HashMapAmir<>(3, "Q");
            for (int q = 0; q < data.q; q++)
                for (int h = 0; h < data.hq; h++)
                    for (int t = 0; t < data.t; t++)
                        Q.put(cplex.boolVar(GenName("Q", "qht", q, h, t)), "q" + q, "h" + h, "t" + t);

            DA = new HashMapAmir<>(3, "DA");
            for (int d = 0; d < data.d; d++)
                for (int j = 0; j < data.hd; j++)
                    for (int t = 0; t < data.t; t++)
                        DA.put(cplex.boolVar(GenName("DA", "djt", d, j, t)), "d" + d, "h" + j, "t" + t);

            RF = new HashMapAmir<>(3, "RF");
            for (int n = 0; n < data.n; n++)
                for (int j = 0; j < data.hn; j++)
                    for (int t = 0; t < data.t; t++)
                        RF.put(cplex.boolVar(GenName("RF", "dht", n, j, t)), "n" + n, "h" + j, "t" + t);

            RM = new HashMapAmir<>(3, "RM");

            for (int m = 0; m < data.m; m++)
                for (int j = 0; j < data.hm; j++)
                    for (int t = 0; t < data.t; t++)
                        RM.put(cplex.boolVar(GenName("RM", "mht", m, j, t)), "m" + m, "h" + j, "t" + t);

            TR1 = new HashMapAmir<>(1, "TR1");
            for (int k = 0; k < data.k; k++)
                TR1.put(cplex.boolVar(GenName("TR1", "k", k)), "k" + k);

            TR2 = new HashMapAmir<>(1, "TR2");
            for (int k = 0; k < data.k; k++)
                TR2.put(cplex.boolVar(GenName("TR2", "k", k)), "k" + k);

            TR3 = new HashMapAmir<>(1, "TR3");
            for (int k = 0; k < data.k; k++)
                TR3.put(cplex.boolVar(GenName("TR3", "k", k)), "k" + k);

            X = new HashMapAmir<>(4, "X");
            XM = new HashMapAmir<>(5, "XM");
            P = new HashMapAmir<>(3, "P");

            for (int t = 0; t < data.t; t++) {

                for (int f = 0; f < data.f; f++)
                    for (int i = 0; i < data.i; i++)
                        for (int k = 0; k < data.k; k++)
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "fitk", f, i, t, k)), "f" + f, "i" + i, "t" + t, "k" + k);

                for (int f = 0; f < data.f; f++)
                    for (int o = 0; o < data.o; o++)
                        P.put(cplex.numVar(0, Double.MAX_VALUE, GenName("P", "fot", f, o, t)), "f" + f, "o" + o, "t" + t);

                for (int i = 0; i < data.i; i++)
                    for (int z = 0; z < data.z; z++)
                        for (int k = 0; k < data.k; k++) {
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "iztk", i, z, t, k)), "i" + i, "z" + z, "t" + t, "k" + k);
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "zitk", z, i, t, k)), "z" + z, "i" + i, "t" + t, "k" + k);
                        }

                for (int i = 0; i < data.i; i++)
                    for (int k = 0; k < data.k; k++) {
                        for (int d = 0; d < data.d; d++)
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "idtk", i, d, t, k)), "i" + i, "d" + d, "t" + t, "k" + k);

                        for (int m = 0; m < data.m; m++) {
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "imtk", i, m, t, k)), "i" + i, "m" + m, "t" + t, "k" + k);
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "mitk", m, i, t, k)), "m" + m, "i" + i, "t" + t, "k" + k);
                        }
                        for (int n = 0; n < data.n; n++) {
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "intk", i, n, t, k)), "i" + i, "n" + n, "t" + t, "k" + k);
                            X.put(cplex.numVar(0, Double.MAX_VALUE, GenName("X", "nitk", n, i, t, k)), "n" + n, "i" + i, "t" + t, "k" + k);
                        }
                    }

                for (int j = 0; j < data.j; j++) {
                    for (int s = 0; s < data.s; s++)
                        for (int k = 0; k < data.k; k++) {
                            for (int f = 0; f < data.f; f++)
                                XM.put(cplex.numVar(0, Double.MAX_VALUE, GenName("XM", "jsftk", j, s, f, t, k)), "j" + j, "s" + s, "f" + f, "t" + t, "k" + k);

                            for (int m = 0; m < data.m; m++)
                                XM.put(cplex.numVar(0, Double.MAX_VALUE, GenName("XM", "jsmtk", j, s, m, t, k)), "j" + j, "s" + s, "m" + m, "t" + t, "k" + k);
                        }

                    for (int d = 0; d < data.d; d++)
                        for (int k = 0; k < data.k; k++) {
                            for (int f = 0; f < data.f; f++)
                                XM.put(cplex.numVar(0, Double.MAX_VALUE, GenName("XM", "jdftk", j, d, f, t, k)), "j" + j, "d" + d, "f" + f, "t" + t, "k" + k);

                            for (int q = 0; q < data.q; q++)
                                XM.put(cplex.numVar(0, Double.MAX_VALUE, GenName("XM", "jdqtk", j, d, q, t, k)), "j" + j, "d" + d, "q" + q, "t" + t, "k" + k);
                        }
                }
            }

            Sh = new HashMapAmir<>(2, "Sh");
            Sm = new HashMapAmir<>(2, "Sm");
            Sl = new HashMapAmir<>(2, "Sl");
            Sn = new HashMapAmir<>(2, "Sn");
            for (int i = 0; i < data.i; i++)
                for (int t = 0; t < data.t; t++) {

                    Sh.put(cplex.numVar(0, Double.MAX_VALUE, GenName("Sh", "it", i, t)), "i" + i, "t" + t);
                    Sm.put(cplex.numVar(0, Double.MAX_VALUE, GenName("Sm", "it", i, t)), "i" + i, "t" + t);
                    Sl.put(cplex.numVar(0, Double.MAX_VALUE, GenName("Sl", "it", i, t)), "i" + i, "t" + t);

                    Sn.put(cplex.numVar(0, Double.MAX_VALUE, GenName("Sn", "it", i, t)), "i" + i, "t" + t);
                }

        } catch (
                IloException e) {
            e.printStackTrace();
        }
    }

    private String GenName(String Name, String index, int... i) {
        if (i.length != index.length())
            throw new NullPointerException(Name + " " + index + "  " + i.toString());

        String re = Name + "(";
        for (int j = 0; j < index.length() - 1; j++) {
            re += index.charAt(j) + "" + i[j] + ",";
        }
        re +=
                index.charAt(index.length() - 1) + "" + i[index.length() - 1] + ")";
        return re;
    }

    private String GenConstrint(int c, String name, int in) {
        String re = String.format("constraint_%d_(%s%d)", c, name, in);
        return re;
    }

    public void SetAmount(boolean b) {

        if (b) {
            try {

                set(WH);
                set(FF);
                set(Q);
                set(DA);
                set(RF);
                set(RM);
                set(TR1);
                set(TR2);
                set(TR3);
                set(X);
                set(XM);
                set(Sn);
                set(Sh);
                set(Sm);
                set(Sl);
                set(NTC);
                set(ENC);
                set(TFC);
                set(TOC);
                set(TTC);
                set(TPC);
                set(BEN);
                set(TEN);
                set(PEN);
                set(EN);
                set(FC);
                set(P);
                set(At);
                set(W);
                set(ENCT);
            } catch (IloException e) {
                e.printStackTrace();
            }
        }
    }

    private void set(HashMapAmir<String, IloNumVar> wh) throws IloException {
        for (MapAmir<String, IloNumVar> o : wh.getValus())
            o.setAmount(this.cplex.getValue(o.getValue()));
    }
}






































