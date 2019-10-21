\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj: NTC + qENCT
Subject To
 constraint_2:        - NTC + TFC + TTC + TOC + TPC - BEN  = 0
 constraint_3:        - TFC + FC(t0) + 0.909090909090909 FC(t1)
                      + 0.826446280991735 FC(t2)  = 0
 constraint_4_(t0):   - FC(t0) + 1000 FF(f0,h0,o0,t0) + 1000 FF(f0,h0,o1,t0)
                      + 1000 FF(f0,h0,o2,t0) + 1000 FF(f0,h0,o3,t0)
                      + 2000 FF(f1,h0,o0,t0) + 2000 FF(f1,h0,o1,t0)
                      + 2000 FF(f1,h0,o2,t0) + 2000 FF(f1,h0,o3,t0)
                      + 2000 WH(i0,h0,t0) + 2000 DA(d0,j0,t0)
                      + 2000 RF(d0,h0,t0) + 2000 RM(m0,h0,t0)
                      + 2000 Q(q0,h0,t0)  = 0
 constraint_4_(t1):   - FC(t1) + 2000 FF(f0,h0,o0,t1) + 2000 FF(f0,h0,o1,t1)
                      + 2000 FF(f0,h0,o2,t1) + 2000 FF(f0,h0,o3,t1)
                      + 2000 FF(f1,h0,o0,t1) + 2000 FF(f1,h0,o1,t1)
                      + 2000 FF(f1,h0,o2,t1) + 2000 FF(f1,h0,o3,t1)
                      + 2000 WH(i0,h0,t1) + 2000 DA(d0,j0,t1)
                      + 2000 RF(d0,h0,t1) + 2000 RM(m0,h0,t1)
                      + 2000 Q(q0,h0,t1)  = 0
 constraint_4_(t2):   - FC(t2) + 100 FF(f0,h0,o0,t2) + 100 FF(f0,h0,o1,t2)
                      + 100 FF(f0,h0,o2,t2) + 100 FF(f0,h0,o3,t2)
                      + 2000 FF(f1,h0,o0,t2) + 2000 FF(f1,h0,o1,t2)
                      + 2000 FF(f1,h0,o2,t2) + 2000 FF(f1,h0,o3,t2)
                      + 2000 WH(i0,h0,t2) + 2000 DA(d0,j0,t2)
                      + 2000 RF(d0,h0,t2) + 2000 RM(m0,h0,t2)
                      + 2000 Q(q0,h0,t2)  = 0
 constraint_5:        - TTC + 170 XM(j0,s0,f0,t0,k0) + 60 XM(j0,s0,f1,t0,k0)
                      + 150 X(f0,i0,t0,k0) + 20 X(f1,i0,t0,k0)
                      + 90 X(i0,z0,t0,k0) + 90 X(z0,i0,t0,k0)
                      + 100 X(i0,d0,t0,k0) + 100 X(i0,n0,t0,k0)
                      + 100 X(n0,i0,t0,k0) + 60 X(i0,m0,t0,k0)
                      + 60 X(m0,i0,t0,k0) + 110 XM(j0,d0,f0,t0,k0)
                      + 90 XM(j0,d0,f1,t0,k0) + 60 XM(j0,d0,q0,t0,k0)
                      + 40 XM(j0,s0,m0,t0,k0) + 85 XM(j0,s0,f0,t0,k1)
                      + 30 XM(j0,s0,f1,t0,k1) + 75 X(f0,i0,t0,k1)
                      + 10 X(f1,i0,t0,k1) + 45 X(i0,z0,t0,k1)
                      + 45 X(z0,i0,t0,k1) + 50 X(i0,d0,t0,k1)
                      + 50 X(i0,n0,t0,k1) + 50 X(n0,i0,t0,k1)
                      + 30 X(i0,m0,t0,k1) + 30 X(m0,i0,t0,k1)
                      + 55 XM(j0,d0,f0,t0,k1) + 45 XM(j0,d0,f1,t0,k1)
                      + 30 XM(j0,d0,q0,t0,k1) + 20 XM(j0,s0,m0,t0,k1)
                      + 340 XM(j0,s0,f0,t0,k2) + 120 XM(j0,s0,f1,t0,k2)
                      + 300 X(f0,i0,t0,k2) + 40 X(f1,i0,t0,k2)
                      + 180 X(i0,z0,t0,k2) + 180 X(z0,i0,t0,k2)
                      + 200 X(i0,d0,t0,k2) + 200 X(i0,n0,t0,k2)
                      + 200 X(n0,i0,t0,k2) + 120 X(i0,m0,t0,k2)
                      + 120 X(m0,i0,t0,k2) + 220 XM(j0,d0,f0,t0,k2)
                      + 180 XM(j0,d0,f1,t0,k2) + 120 XM(j0,d0,q0,t0,k2)
                      + 80 XM(j0,s0,m0,t0,k2)
                      + 154.545454545455 XM(j0,s0,f0,t1,k0)
                      + 54.5454545454545 XM(j0,s0,f1,t1,k0)
                      + 136.363636363636 X(f0,i0,t1,k0)
                      + 18.1818181818182 X(f1,i0,t1,k0)
                      + 81.8181818181818 X(i0,z0,t1,k0)
                      + 81.8181818181818 X(z0,i0,t1,k0)
                      + 90.9090909090909 X(i0,d0,t1,k0)
                      + 90.9090909090909 X(i0,n0,t1,k0)
                      + 90.9090909090909 X(n0,i0,t1,k0)
                      + 54.5454545454545 X(i0,m0,t1,k0)
                      + 54.5454545454545 X(m0,i0,t1,k0)
                      + 100 XM(j0,d0,f0,t1,k0)
                      + 81.8181818181818 XM(j0,d0,f1,t1,k0)
                      + 54.5454545454545 XM(j0,d0,q0,t1,k0)
                      + 36.3636363636364 XM(j0,s0,m0,t1,k0)
                      + 77.2727272727273 XM(j0,s0,f0,t1,k1)
                      + 27.2727272727273 XM(j0,s0,f1,t1,k1)
                      + 68.1818181818182 X(f0,i0,t1,k1)
                      + 9.09090909090909 X(f1,i0,t1,k1)
                      + 40.9090909090909 X(i0,z0,t1,k1)
                      + 40.9090909090909 X(z0,i0,t1,k1)
                      + 45.4545454545455 X(i0,d0,t1,k1)
                      + 45.4545454545455 X(i0,n0,t1,k1)
                      + 45.4545454545455 X(n0,i0,t1,k1)
                      + 27.2727272727273 X(i0,m0,t1,k1)
                      + 27.2727272727273 X(m0,i0,t1,k1) + 50 XM(j0,d0,f0,t1,k1)
                      + 40.9090909090909 XM(j0,d0,f1,t1,k1)
                      + 27.2727272727273 XM(j0,d0,q0,t1,k1)
                      + 18.1818181818182 XM(j0,s0,m0,t1,k1)
                      + 309.090909090909 XM(j0,s0,f0,t1,k2)
                      + 109.090909090909 XM(j0,s0,f1,t1,k2)
                      + 272.727272727273 X(f0,i0,t1,k2)
                      + 36.3636363636364 X(f1,i0,t1,k2)
                      + 163.636363636364 X(i0,z0,t1,k2)
                      + 163.636363636364 X(z0,i0,t1,k2)
                      + 181.818181818182 X(i0,d0,t1,k2)
                      + 181.818181818182 X(i0,n0,t1,k2)
                      + 181.818181818182 X(n0,i0,t1,k2)
                      + 109.090909090909 X(i0,m0,t1,k2)
                      + 109.090909090909 X(m0,i0,t1,k2)
                      + 200 XM(j0,d0,f0,t1,k2)
                      + 163.636363636364 XM(j0,d0,f1,t1,k2)
                      + 109.090909090909 XM(j0,d0,q0,t1,k2)
                      + 72.7272727272727 XM(j0,s0,m0,t1,k2)
                      + 140.495867768595 XM(j0,s0,f0,t2,k0)
                      + 49.5867768595041 XM(j0,s0,f1,t2,k0)
                      + 123.96694214876 X(f0,i0,t2,k0)
                      + 16.5289256198347 X(f1,i0,t2,k0)
                      + 74.3801652892562 X(i0,z0,t2,k0)
                      + 74.3801652892562 X(z0,i0,t2,k0)
                      + 82.6446280991735 X(i0,d0,t2,k0)
                      + 82.6446280991735 X(i0,n0,t2,k0)
                      + 82.6446280991735 X(n0,i0,t2,k0)
                      + 49.5867768595041 X(i0,m0,t2,k0)
                      + 49.5867768595041 X(m0,i0,t2,k0)
                      + 90.9090909090909 XM(j0,d0,f0,t2,k0)
                      + 74.3801652892562 XM(j0,d0,f1,t2,k0)
                      + 49.5867768595041 XM(j0,d0,q0,t2,k0)
                      + 33.0578512396694 XM(j0,s0,m0,t2,k0)
                      + 70.2479338842975 XM(j0,s0,f0,t2,k1)
                      + 24.7933884297521 XM(j0,s0,f1,t2,k1)
                      + 61.9834710743802 X(f0,i0,t2,k1)
                      + 8.26446280991735 X(f1,i0,t2,k1)
                      + 37.1900826446281 X(i0,z0,t2,k1)
                      + 37.1900826446281 X(z0,i0,t2,k1)
                      + 41.3223140495868 X(i0,d0,t2,k1)
                      + 41.3223140495868 X(i0,n0,t2,k1)
                      + 41.3223140495868 X(n0,i0,t2,k1)
                      + 24.7933884297521 X(i0,m0,t2,k1)
                      + 24.7933884297521 X(m0,i0,t2,k1)
                      + 45.4545454545455 XM(j0,d0,f0,t2,k1)
                      + 37.1900826446281 XM(j0,d0,f1,t2,k1)
                      + 24.7933884297521 XM(j0,d0,q0,t2,k1)
                      + 16.5289256198347 XM(j0,s0,m0,t2,k1)
                      + 280.99173553719 XM(j0,s0,f0,t2,k2)
                      + 99.1735537190083 XM(j0,s0,f1,t2,k2)
                      + 247.933884297521 X(f0,i0,t2,k2)
                      + 33.0578512396694 X(f1,i0,t2,k2)
                      + 148.760330578512 X(i0,z0,t2,k2)
                      + 148.760330578512 X(z0,i0,t2,k2)
                      + 165.289256198347 X(i0,d0,t2,k2)
                      + 165.289256198347 X(i0,n0,t2,k2)
                      + 165.289256198347 X(n0,i0,t2,k2)
                      + 99.1735537190083 X(i0,m0,t2,k2)
                      + 99.1735537190083 X(m0,i0,t2,k2)
                      + 181.818181818182 XM(j0,d0,f0,t2,k2)
                      + 148.760330578512 XM(j0,d0,f1,t2,k2)
                      + 99.1735537190083 XM(j0,d0,q0,t2,k2)
                      + 66.1157024793388 XM(j0,s0,m0,t2,k2)  = 0
 constraint_6:        - TOC + 6 X(i0,z0,t0,k0) + 6 X(z0,i0,t0,k0)
                      + 6 X(i0,d0,t0,k0) + 6 X(i0,n0,t0,k0) + 6 X(i0,m0,t0,k0)
                      + 6 X(i0,z0,t0,k1) + 6 X(z0,i0,t0,k1) + 6 X(i0,d0,t0,k1)
                      + 6 X(i0,n0,t0,k1) + 6 X(i0,m0,t0,k1) + 6 X(i0,z0,t0,k2)
                      + 6 X(z0,i0,t0,k2) + 6 X(i0,d0,t0,k2) + 6 X(i0,n0,t0,k2)
                      + 6 X(i0,m0,t0,k2) + 5.45454545454545 X(i0,z0,t1,k0)
                      + 5.45454545454545 X(z0,i0,t1,k0)
                      + 5.45454545454545 X(i0,d0,t1,k0)
                      + 5.45454545454545 X(i0,n0,t1,k0)
                      + 5.45454545454545 X(i0,m0,t1,k0)
                      + 5.45454545454545 X(i0,z0,t1,k1)
                      + 5.45454545454545 X(z0,i0,t1,k1)
                      + 5.45454545454545 X(i0,d0,t1,k1)
                      + 5.45454545454545 X(i0,n0,t1,k1)
                      + 5.45454545454545 X(i0,m0,t1,k1)
                      + 5.45454545454545 X(i0,z0,t1,k2)
                      + 5.45454545454545 X(z0,i0,t1,k2)
                      + 5.45454545454545 X(i0,d0,t1,k2)
                      + 5.45454545454545 X(i0,n0,t1,k2)
                      + 5.45454545454545 X(i0,m0,t1,k2)
                      + 4.95867768595041 X(i0,z0,t2,k0)
                      + 4.95867768595041 X(z0,i0,t2,k0)
                      + 4.95867768595041 X(i0,d0,t2,k0)
                      + 4.95867768595041 X(i0,n0,t2,k0)
                      + 4.95867768595041 X(i0,m0,t2,k0)
                      + 4.95867768595041 X(i0,z0,t2,k1)
                      + 4.95867768595041 X(z0,i0,t2,k1)
                      + 4.95867768595041 X(i0,d0,t2,k1)
                      + 4.95867768595041 X(i0,n0,t2,k1)
                      + 4.95867768595041 X(i0,m0,t2,k1)
                      + 4.95867768595041 X(i0,z0,t2,k2)
                      + 4.95867768595041 X(z0,i0,t2,k2)
                      + 4.95867768595041 X(i0,d0,t2,k2)
                      + 4.95867768595041 X(i0,n0,t2,k2)
                      + 4.95867768595041 X(i0,m0,t2,k2) + 6 P(f0,o0,t0)
                      + 6 P(f0,o1,t0) + 6 P(f0,o2,t0) + 6 P(f0,o3,t0)
                      + 6 P(f1,o0,t0) + 6 P(f1,o1,t0) + 6 P(f1,o2,t0)
                      + 6 P(f1,o3,t0) + 18 Sh(i0,t0) + 18 Sm(i0,t0)
                      + 18 Sl(i0,t0) + 18 Sn(i0,t0)
                      + 5.45454545454545 P(f0,o0,t1)
                      + 5.45454545454545 P(f0,o1,t1)
                      + 5.45454545454545 P(f0,o2,t1)
                      + 5.45454545454545 P(f0,o3,t1)
                      + 5.45454545454545 P(f1,o0,t1)
                      + 5.45454545454545 P(f1,o1,t1)
                      + 5.45454545454545 P(f1,o2,t1)
                      + 5.45454545454545 P(f1,o3,t1)
                      + 16.3636363636364 Sh(i0,t1) + 16.3636363636364 Sm(i0,t1)
                      + 16.3636363636364 Sl(i0,t1) + 16.3636363636364 Sn(i0,t1)
                      + 82.6446280991735 P(f0,o0,t2)
                      + 82.6446280991735 P(f0,o1,t2)
                      + 82.6446280991735 P(f0,o2,t2)
                      + 82.6446280991735 P(f0,o3,t2)
                      + 82.6446280991735 P(f1,o0,t2)
                      + 82.6446280991735 P(f1,o1,t2)
                      + 82.6446280991735 P(f1,o2,t2)
                      + 82.6446280991735 P(f1,o3,t2)
                      + 14.8760330578512 Sh(i0,t2) + 14.8760330578512 Sm(i0,t2)
                      + 14.8760330578512 Sl(i0,t2) + 14.8760330578512 Sn(i0,t2)
                       = 0
 constraint_7:        - TPC + 10 XM(j0,s0,f0,t0,k0) + 10 XM(j0,s0,f1,t0,k0)
                      + 18.8 X(z0,i0,t0,k0) + 10 XM(j0,s0,m0,t0,k0)
                      + 10 XM(j0,s0,f0,t0,k1) + 10 XM(j0,s0,f1,t0,k1)
                      + 18.8 X(z0,i0,t0,k1) + 10 XM(j0,s0,m0,t0,k1)
                      + 10 XM(j0,s0,f0,t0,k2) + 10 XM(j0,s0,f1,t0,k2)
                      + 18.8 X(z0,i0,t0,k2) + 10 XM(j0,s0,m0,t0,k2)
                      + 9.09090909090909 XM(j0,s0,f0,t1,k0)
                      + 9.09090909090909 XM(j0,s0,f1,t1,k0)
                      + 17.0909090909091 X(z0,i0,t1,k0)
                      + 9.09090909090909 XM(j0,s0,m0,t1,k0)
                      + 9.09090909090909 XM(j0,s0,f0,t1,k1)
                      + 9.09090909090909 XM(j0,s0,f1,t1,k1)
                      + 17.0909090909091 X(z0,i0,t1,k1)
                      + 9.09090909090909 XM(j0,s0,m0,t1,k1)
                      + 9.09090909090909 XM(j0,s0,f0,t1,k2)
                      + 9.09090909090909 XM(j0,s0,f1,t1,k2)
                      + 17.0909090909091 X(z0,i0,t1,k2)
                      + 9.09090909090909 XM(j0,s0,m0,t1,k2)
                      + 8.26446280991735 XM(j0,s0,f0,t2,k0)
                      + 8.26446280991735 XM(j0,s0,f1,t2,k0)
                      + 15.5371900826446 X(z0,i0,t2,k0)
                      + 8.26446280991735 XM(j0,s0,m0,t2,k0)
                      + 8.26446280991735 XM(j0,s0,f0,t2,k1)
                      + 8.26446280991735 XM(j0,s0,f1,t2,k1)
                      + 15.5371900826446 X(z0,i0,t2,k1)
                      + 8.26446280991735 XM(j0,s0,m0,t2,k1)
                      + 8.26446280991735 XM(j0,s0,f0,t2,k2)
                      + 8.26446280991735 XM(j0,s0,f1,t2,k2)
                      + 15.5371900826446 X(z0,i0,t2,k2)
                      + 8.26446280991735 XM(j0,s0,m0,t2,k2)  = 0
 constraint_8:        - BEN + 16 X(i0,z0,t0,k0) + 13 XM(j0,d0,q0,t0,k0)
                      + 16 X(i0,z0,t0,k1) + 13 XM(j0,d0,q0,t0,k1)
                      + 16 X(i0,z0,t0,k2) + 13 XM(j0,d0,q0,t0,k2)
                      + 14.5454545454545 X(i0,z0,t1,k0)
                      + 11.8181818181818 XM(j0,d0,q0,t1,k0)
                      + 14.5454545454545 X(i0,z0,t1,k1)
                      + 11.8181818181818 XM(j0,d0,q0,t1,k1)
                      + 14.5454545454545 X(i0,z0,t1,k2)
                      + 11.8181818181818 XM(j0,d0,q0,t1,k2)
                      + 13.2231404958678 X(i0,z0,t2,k0)
                      + 10.7438016528926 XM(j0,d0,q0,t2,k0)
                      + 13.2231404958678 X(i0,z0,t2,k1)
                      + 10.7438016528926 XM(j0,d0,q0,t2,k1)
                      + 13.2231404958678 X(i0,z0,t2,k2)
                      + 10.7438016528926 XM(j0,d0,q0,t2,k2)  = 0
 constraint_9:        TEN(t0) + PEN(t0) - qEN(t0)  = 0
 constraint_9:        TEN(t1) + PEN(t1) - qEN(t1)  = 0
 constraint_9:        TEN(t2) + PEN(t2) - qEN(t2)  = 0
 constraint_10(t0):   1.7 XM(j0,s0,f0,t0,k0) + 0.6 XM(j0,s0,f1,t0,k0)
                      + 1.5 X(f0,i0,t0,k0) + 0.2 X(f1,i0,t0,k0)
                      + 0.9 X(i0,z0,t0,k0) + 0.9 X(z0,i0,t0,k0)
                      + X(i0,d0,t0,k0) + X(i0,n0,t0,k0) + X(n0,i0,t0,k0)
                      + 0.6 X(i0,m0,t0,k0) + 0.6 X(m0,i0,t0,k0)
                      + 1.1 XM(j0,d0,f0,t0,k0) + 0.9 XM(j0,d0,f1,t0,k0)
                      + 0.6 XM(j0,d0,q0,t0,k0) + 0.4 XM(j0,s0,m0,t0,k0)
                      + 3.4 XM(j0,s0,f0,t0,k1) + 1.2 XM(j0,s0,f1,t0,k1)
                      + 3 X(f0,i0,t0,k1) + 0.4 X(f1,i0,t0,k1)
                      + 1.8 X(i0,z0,t0,k1) + 1.8 X(z0,i0,t0,k1)
                      + 2 X(i0,d0,t0,k1) + 2 X(i0,n0,t0,k1) + 2 X(n0,i0,t0,k1)
                      + 1.2 X(i0,m0,t0,k1) + 1.2 X(m0,i0,t0,k1)
                      + 2.2 XM(j0,d0,f0,t0,k1) + 1.8 XM(j0,d0,f1,t0,k1)
                      + 1.2 XM(j0,d0,q0,t0,k1) + 0.8 XM(j0,s0,m0,t0,k1)
                      + 5.1 XM(j0,s0,f0,t0,k2) + 1.8 XM(j0,s0,f1,t0,k2)
                      + 4.5 X(f0,i0,t0,k2) + 0.6 X(f1,i0,t0,k2)
                      + 2.7 X(i0,z0,t0,k2) + 2.7 X(z0,i0,t0,k2)
                      + 3 X(i0,d0,t0,k2) + 3 X(i0,n0,t0,k2) + 3 X(n0,i0,t0,k2)
                      + 1.8 X(i0,m0,t0,k2) + 1.8 X(m0,i0,t0,k2)
                      + 3.3 XM(j0,d0,f0,t0,k2) + 2.7 XM(j0,d0,f1,t0,k2)
                      + 1.8 XM(j0,d0,q0,t0,k2) + 1.2 XM(j0,s0,m0,t0,k2)
                      - TEN(t0)  = 0
 constraint_10(t1):   1.7 XM(j0,s0,f0,t1,k0) + 0.6 XM(j0,s0,f1,t1,k0)
                      + 1.5 X(f0,i0,t1,k0) + 0.2 X(f1,i0,t1,k0)
                      + 0.9 X(i0,z0,t1,k0) + 0.9 X(z0,i0,t1,k0)
                      + X(i0,d0,t1,k0) + X(i0,n0,t1,k0) + X(n0,i0,t1,k0)
                      + 0.6 X(i0,m0,t1,k0) + 0.6 X(m0,i0,t1,k0)
                      + 1.1 XM(j0,d0,f0,t1,k0) + 0.9 XM(j0,d0,f1,t1,k0)
                      + 0.6 XM(j0,d0,q0,t1,k0) + 0.4 XM(j0,s0,m0,t1,k0)
                      + 3.4 XM(j0,s0,f0,t1,k1) + 1.2 XM(j0,s0,f1,t1,k1)
                      + 3 X(f0,i0,t1,k1) + 0.4 X(f1,i0,t1,k1)
                      + 1.8 X(i0,z0,t1,k1) + 1.8 X(z0,i0,t1,k1)
                      + 2 X(i0,d0,t1,k1) + 2 X(i0,n0,t1,k1) + 2 X(n0,i0,t1,k1)
                      + 1.2 X(i0,m0,t1,k1) + 1.2 X(m0,i0,t1,k1)
                      + 2.2 XM(j0,d0,f0,t1,k1) + 1.8 XM(j0,d0,f1,t1,k1)
                      + 1.2 XM(j0,d0,q0,t1,k1) + 0.8 XM(j0,s0,m0,t1,k1)
                      + 5.1 XM(j0,s0,f0,t1,k2) + 1.8 XM(j0,s0,f1,t1,k2)
                      + 4.5 X(f0,i0,t1,k2) + 0.6 X(f1,i0,t1,k2)
                      + 2.7 X(i0,z0,t1,k2) + 2.7 X(z0,i0,t1,k2)
                      + 3 X(i0,d0,t1,k2) + 3 X(i0,n0,t1,k2) + 3 X(n0,i0,t1,k2)
                      + 1.8 X(i0,m0,t1,k2) + 1.8 X(m0,i0,t1,k2)
                      + 3.3 XM(j0,d0,f0,t1,k2) + 2.7 XM(j0,d0,f1,t1,k2)
                      + 1.8 XM(j0,d0,q0,t1,k2) + 1.2 XM(j0,s0,m0,t1,k2)
                      - TEN(t1)  = 0
 constraint_10(t2):   1.7 XM(j0,s0,f0,t2,k0) + 0.6 XM(j0,s0,f1,t2,k0)
                      + 1.5 X(f0,i0,t2,k0) + 0.2 X(f1,i0,t2,k0)
                      + 0.9 X(i0,z0,t2,k0) + 0.9 X(z0,i0,t2,k0)
                      + X(i0,d0,t2,k0) + X(i0,n0,t2,k0) + X(n0,i0,t2,k0)
                      + 0.6 X(i0,m0,t2,k0) + 0.6 X(m0,i0,t2,k0)
                      + 1.1 XM(j0,d0,f0,t2,k0) + 0.9 XM(j0,d0,f1,t2,k0)
                      + 0.6 XM(j0,d0,q0,t2,k0) + 0.4 XM(j0,s0,m0,t2,k0)
                      + 3.4 XM(j0,s0,f0,t2,k1) + 1.2 XM(j0,s0,f1,t2,k1)
                      + 3 X(f0,i0,t2,k1) + 0.4 X(f1,i0,t2,k1)
                      + 1.8 X(i0,z0,t2,k1) + 1.8 X(z0,i0,t2,k1)
                      + 2 X(i0,d0,t2,k1) + 2 X(i0,n0,t2,k1) + 2 X(n0,i0,t2,k1)
                      + 1.2 X(i0,m0,t2,k1) + 1.2 X(m0,i0,t2,k1)
                      + 2.2 XM(j0,d0,f0,t2,k1) + 1.8 XM(j0,d0,f1,t2,k1)
                      + 1.2 XM(j0,d0,q0,t2,k1) + 0.8 XM(j0,s0,m0,t2,k1)
                      + 5.1 XM(j0,s0,f0,t2,k2) + 1.8 XM(j0,s0,f1,t2,k2)
                      + 4.5 X(f0,i0,t2,k2) + 0.6 X(f1,i0,t2,k2)
                      + 2.7 X(i0,z0,t2,k2) + 2.7 X(z0,i0,t2,k2)
                      + 3 X(i0,d0,t2,k2) + 3 X(i0,n0,t2,k2) + 3 X(n0,i0,t2,k2)
                      + 1.8 X(i0,m0,t2,k2) + 1.8 X(m0,i0,t2,k2)
                      + 3.3 XM(j0,d0,f0,t2,k2) + 2.7 XM(j0,d0,f1,t2,k2)
                      + 1.8 XM(j0,d0,q0,t2,k2) + 1.2 XM(j0,s0,m0,t2,k2)
                      - TEN(t2)  = 0
 constraint_11:       0.1 P(f0,o0,t0) + 0.1 P(f0,o1,t0) + 0.1 P(f0,o2,t0)
                      + 0.1 P(f0,o3,t0) + 0.1 P(f1,o0,t0) + 0.1 P(f1,o1,t0)
                      + 0.1 P(f1,o2,t0) + 0.1 P(f1,o3,t0) - PEN(t0)  = 0
 constraint_11:       0.1 P(f0,o0,t1) + 0.1 P(f0,o1,t1) + 0.1 P(f0,o2,t1)
                      + 0.1 P(f0,o3,t1) + 0.1 P(f1,o0,t1) + 0.1 P(f1,o1,t1)
                      + 0.1 P(f1,o2,t1) + 0.1 P(f1,o3,t1) - PEN(t1)  = 0
 constraint_11:       0.1 P(f0,o0,t2) + 0.1 P(f0,o1,t2) + 0.1 P(f0,o2,t2)
                      + 0.1 P(f0,o3,t2) + 0.1 P(f1,o0,t2) + 0.1 P(f1,o1,t2)
                      + 0.1 P(f1,o2,t2) + 0.1 P(f1,o3,t2) - PEN(t2)  = 0
 constraint_12:       - qENCT + qENC(t0) + 0.909090909090909 qENC(t1)
                      + 0.826446280991735 qENC(t2)  = 0
 constraint_13(t0):   - qENC(t0) + 10 At(e0,t0) + 20 At(e1,t0) + 30 At(e2,t0)
                      + 40 At(e3,t0)  = 0
 constraint_13(t1):   - qENC(t1) + 10 At(e0,t1) + 20 At(e1,t1) + 30 At(e2,t1)
                      + 40 At(e3,t1)  = 0
 constraint_13(t2):   - qENC(t2) + 10 At(e0,t2) + 20 At(e1,t2) + 30 At(e2,t2)
                      + 40 At(e3,t2)  = 0
 constraint_14(t0):   - qEN(t0) + At(e0,t0) + At(e1,t0) + At(e2,t0) + At(e3,t0)
                       = 0
 constraint_14(t1):   - qEN(t1) + At(e0,t1) + At(e1,t1) + At(e2,t1) + At(e3,t1)
                       = 0
 constraint_14(t2):   - qEN(t2) + At(e0,t2) + At(e1,t2) + At(e2,t2) + At(e3,t2)
                       = 0
 constraint_15_0(t0): At(e0,t0) <= 100
 constraint_15_1(t0): At(e0,t0) - 100 W(e0,t0) >= 0
 constraint_15_0(t1): At(e0,t1) <= 100
 constraint_15_1(t1): At(e0,t1) - 100 W(e0,t1) >= 0
 constraint_15_0(t2): At(e0,t2) <= 100
 constraint_15_1(t2): At(e0,t2) - 100 W(e0,t2) >= 0
 constraint_16_(q0):  - At(e1,t0) + 50 W(e0,t0) >= 0
 constraint_16_(q1):  - At(e1,t0) + 50 W(e1,t0) <= 0
 constraint_16_(q2):  - At(e1,t1) + 50 W(e0,t1) >= 0
 constraint_16_(q3):  - At(e1,t1) + 50 W(e1,t1) <= 0
 constraint_16_(q4):  - At(e1,t2) + 50 W(e0,t2) >= 0
 constraint_16_(q5):  - At(e1,t2) + 50 W(e1,t2) <= 0
 constraint_16_(q6):  - At(e2,t0) + 150 W(e1,t0) >= 0
 constraint_16_(q7):  - At(e2,t0) + 150 W(e2,t0) <= 0
 constraint_16_(q8):  - At(e2,t1) + 150 W(e1,t1) >= 0
 constraint_16_(q9):  - At(e2,t1) + 150 W(e2,t1) <= 0
 constraint_16_(q10): - At(e2,t2) + 150 W(e1,t2) >= 0
 constraint_16_(q11): - At(e2,t2) + 150 W(e2,t2) <= 0
 constraint_17(t0):   - At(e3,t0) + 1.79769313486232e+308 W(e2,t0) >= 0
 constraint_17(t1):   - At(e3,t1) + 1.79769313486232e+308 W(e2,t1) >= 0
 constraint_17(t2):   - At(e3,t2) + 1.79769313486232e+308 W(e2,t2) >= 0
 constraint_18_(q0):  XM(j0,s0,f0,t0,k0) - X(f0,i0,t0,k0) + XM(j0,d0,f0,t0,k0)
                      + XM(j0,s0,f0,t0,k1) - X(f0,i0,t0,k1)
                      + XM(j0,d0,f0,t0,k1) + XM(j0,s0,f0,t0,k2)
                      - X(f0,i0,t0,k2) + XM(j0,d0,f0,t0,k2)  = 0
 constraint_18_(q1):  XM(j0,s0,f0,t1,k0) - X(f0,i0,t1,k0) + XM(j0,d0,f0,t1,k0)
                      + XM(j0,s0,f0,t1,k1) - X(f0,i0,t1,k1)
                      + XM(j0,d0,f0,t1,k1) + XM(j0,s0,f0,t1,k2)
                      - X(f0,i0,t1,k2) + XM(j0,d0,f0,t1,k2)  = 0
 constraint_18_(q2):  XM(j0,s0,f0,t2,k0) - X(f0,i0,t2,k0) + XM(j0,d0,f0,t2,k0)
                      + XM(j0,s0,f0,t2,k1) - X(f0,i0,t2,k1)
                      + XM(j0,d0,f0,t2,k1) + XM(j0,s0,f0,t2,k2)
                      - X(f0,i0,t2,k2) + XM(j0,d0,f0,t2,k2)  = 0
 constraint_18_(q3):  XM(j0,s0,f1,t0,k0) - X(f1,i0,t0,k0) + XM(j0,d0,f1,t0,k0)
                      + XM(j0,s0,f1,t0,k1) - X(f1,i0,t0,k1)
                      + XM(j0,d0,f1,t0,k1) + XM(j0,s0,f1,t0,k2)
                      - X(f1,i0,t0,k2) + XM(j0,d0,f1,t0,k2)  = 0
 constraint_18_(q4):  XM(j0,s0,f1,t1,k0) - X(f1,i0,t1,k0) + XM(j0,d0,f1,t1,k0)
                      + XM(j0,s0,f1,t1,k1) - X(f1,i0,t1,k1)
                      + XM(j0,d0,f1,t1,k1) + XM(j0,s0,f1,t1,k2)
                      - X(f1,i0,t1,k2) + XM(j0,d0,f1,t1,k2)  = 0
 constraint_18_(q5):  XM(j0,s0,f1,t2,k0) - X(f1,i0,t2,k0) + XM(j0,d0,f1,t2,k0)
                      + XM(j0,s0,f1,t2,k1) - X(f1,i0,t2,k1)
                      + XM(j0,d0,f1,t2,k1) + XM(j0,s0,f1,t2,k2)
                      - X(f1,i0,t2,k2) + XM(j0,d0,f1,t2,k2)  = 0
 constraint_19_(q0):  - 0.01 X(i0,d0,t0,k0) - 0.01 X(i0,d0,t0,k1)
                      - 0.01 X(i0,d0,t0,k2) + XM(j0,d0,f0,t1,k0)
                      + XM(j0,d0,f1,t1,k0) + XM(j0,d0,f0,t1,k1)
                      + XM(j0,d0,f1,t1,k1) + XM(j0,d0,f0,t1,k2)
                      + XM(j0,d0,f1,t1,k2)  = 0
 constraint_19_(q1):  - 0.01 X(i0,d0,t1,k0) - 0.01 X(i0,d0,t1,k1)
                      - 0.01 X(i0,d0,t1,k2) + XM(j0,d0,f0,t2,k0)
                      + XM(j0,d0,f1,t2,k0) + XM(j0,d0,f0,t2,k1)
                      + XM(j0,d0,f1,t2,k1) + XM(j0,d0,f0,t2,k2)
                      + XM(j0,d0,f1,t2,k2)  = 0
 constraint_19_(q2):  - 0.01 X(i0,d0,t2,k0) - 0.01 X(i0,d0,t2,k1)
                      - 0.01 X(i0,d0,t2,k2)  = 0
 constraint_20_(q0):  XM(j0,d0,f0,t0,k0) + XM(j0,d0,f1,t0,k0)
                      + XM(j0,d0,f0,t0,k1) + XM(j0,d0,f1,t0,k1)
                      + XM(j0,d0,f0,t0,k2) + XM(j0,d0,f1,t0,k2)  = 0
 constraint_21_(q0):  - 0.99 X(i0,d0,t0,k0) - 0.99 X(i0,d0,t0,k1)
                      - 0.99 X(i0,d0,t0,k2) + XM(j0,d0,q0,t1,k0)
                      + XM(j0,d0,q0,t1,k1) + XM(j0,d0,q0,t1,k2)  = 0
 constraint_21_(q1):  - 0.99 X(i0,d0,t1,k0) - 0.99 X(i0,d0,t1,k1)
                      - 0.99 X(i0,d0,t1,k2) + XM(j0,d0,q0,t2,k0)
                      + XM(j0,d0,q0,t2,k1) + XM(j0,d0,q0,t2,k2)  = 0
 constraint_22_(q0):  X(i0,n0,t0,k0) + X(i0,n0,t0,k1) + X(i0,n0,t0,k2)
                      - X(n0,i0,t1,k0) - X(n0,i0,t1,k1) - X(n0,i0,t1,k2)  = 0
 constraint_22_(q1):  X(i0,n0,t1,k0) + X(i0,n0,t1,k1) + X(i0,n0,t1,k2)
                      - X(n0,i0,t2,k0) - X(n0,i0,t2,k1) - X(n0,i0,t2,k2)  = 0
 constraint_23_(q0):  X(n0,i0,t0,k0) + X(n0,i0,t0,k1) + X(n0,i0,t0,k2)  = 0
 constraint_24_(q0):  X(i0,m0,t0,k0) + X(i0,m0,t0,k1) + X(i0,m0,t0,k2)
                      - X(m0,i0,t1,k0) - X(m0,i0,t1,k1) - X(m0,i0,t1,k2)  = 0
 constraint_24_(q1):  X(i0,m0,t1,k0) + X(i0,m0,t1,k1) + X(i0,m0,t1,k2)
                      - X(m0,i0,t2,k0) - X(m0,i0,t2,k1) - X(m0,i0,t2,k2)  = 0
 constraint_25_(q0):  X(m0,i0,t0,k0) + X(m0,i0,t0,k1) + X(m0,i0,t0,k2)  = 0
 constraint_26_(q0):  - 0.76 X(m0,i0,t0,k0) + XM(j0,s0,m0,t0,k0)
                      - 0.76 X(m0,i0,t0,k1) + XM(j0,s0,m0,t0,k1)
                      - 0.76 X(m0,i0,t0,k2) + XM(j0,s0,m0,t0,k2)  = 0
 constraint_26_(q1):  - 0.76 X(m0,i0,t1,k0) + XM(j0,s0,m0,t1,k0)
                      - 0.76 X(m0,i0,t1,k1) + XM(j0,s0,m0,t1,k1)
                      - 0.76 X(m0,i0,t1,k2) + XM(j0,s0,m0,t1,k2)  = 0
 constraint_26_(q2):  - 0.76 X(m0,i0,t2,k0) + XM(j0,s0,m0,t2,k0)
                      - 0.76 X(m0,i0,t2,k1) + XM(j0,s0,m0,t2,k1)
                      - 0.76 X(m0,i0,t2,k2) + XM(j0,s0,m0,t2,k2)  = 0
 constraint_27_(q0):  X(f0,i0,t0,k0) + X(f1,i0,t0,k0) - X(i0,z0,t0,k0)
                      + X(n0,i0,t0,k0) + X(m0,i0,t0,k0) + X(f0,i0,t0,k1)
                      + X(f1,i0,t0,k1) - X(i0,z0,t0,k1) + X(n0,i0,t0,k1)
                      + X(m0,i0,t0,k1) + X(f0,i0,t0,k2) + X(f1,i0,t0,k2)
                      - X(i0,z0,t0,k2) + X(n0,i0,t0,k2) + X(m0,i0,t0,k2)
                      - Sn(i0,t0)  = 0
 constraint_27_(q1):  X(f0,i0,t1,k0) + X(f1,i0,t1,k0) - X(i0,z0,t1,k0)
                      + X(n0,i0,t1,k0) + X(m0,i0,t1,k0) + X(f0,i0,t1,k1)
                      + X(f1,i0,t1,k1) - X(i0,z0,t1,k1) + X(n0,i0,t1,k1)
                      + X(m0,i0,t1,k1) + X(f0,i0,t1,k2) + X(f1,i0,t1,k2)
                      - X(i0,z0,t1,k2) + X(n0,i0,t1,k2) + X(m0,i0,t1,k2)
                      + Sn(i0,t0) - Sn(i0,t1)  = 0
 constraint_27_(q2):  X(f0,i0,t2,k0) + X(f1,i0,t2,k0) - X(i0,z0,t2,k0)
                      + X(n0,i0,t2,k0) + X(m0,i0,t2,k0) + X(f0,i0,t2,k1)
                      + X(f1,i0,t2,k1) - X(i0,z0,t2,k1) + X(n0,i0,t2,k1)
                      + X(m0,i0,t2,k1) + X(f0,i0,t2,k2) + X(f1,i0,t2,k2)
                      - X(i0,z0,t2,k2) + X(n0,i0,t2,k2) + X(m0,i0,t2,k2)
                      + Sn(i0,t1) - Sn(i0,t2)  = 0
 constraint_28_(q0):  - 0.3 X(z0,i0,t0,k0) + X(i0,n0,t0,k0)
                      - 0.3 X(z0,i0,t0,k1) + X(i0,n0,t0,k1)
                      - 0.3 X(z0,i0,t0,k2) + X(i0,n0,t0,k2) + Sh(i0,t0)  = 0
 constraint_28_(q1):  - 0.3 X(z0,i0,t1,k0) + X(i0,n0,t1,k0)
                      - 0.3 X(z0,i0,t1,k1) + X(i0,n0,t1,k1)
                      - 0.3 X(z0,i0,t1,k2) + X(i0,n0,t1,k2) - Sh(i0,t0)
                      + Sh(i0,t1)  = 0
 constraint_28_(q2):  - 0.3 X(z0,i0,t2,k0) + X(i0,n0,t2,k0)
                      - 0.3 X(z0,i0,t2,k1) + X(i0,n0,t2,k1)
                      - 0.3 X(z0,i0,t2,k2) + X(i0,n0,t2,k2) - Sh(i0,t1)
                      + Sh(i0,t2)  = 0
 constraint_29_(q0):  - 0.3 X(z0,i0,t0,k0) + X(i0,m0,t0,k0)
                      - 0.3 X(z0,i0,t0,k1) + X(i0,m0,t0,k1)
                      - 0.3 X(z0,i0,t0,k2) + X(i0,m0,t0,k2) + Sm(i0,t0)  = 0
 constraint_29_(q1):  - 0.3 X(z0,i0,t1,k0) + X(i0,m0,t1,k0)
                      - 0.3 X(z0,i0,t1,k1) + X(i0,m0,t1,k1)
                      - 0.3 X(z0,i0,t1,k2) + X(i0,m0,t1,k2) - Sm(i0,t0)
                      + Sm(i0,t1)  = 0
 constraint_29_(q2):  - 0.3 X(z0,i0,t2,k0) + X(i0,m0,t2,k0)
                      - 0.3 X(z0,i0,t2,k1) + X(i0,m0,t2,k1)
                      - 0.3 X(z0,i0,t2,k2) + X(i0,m0,t2,k2) - Sm(i0,t1)
                      + Sm(i0,t2)  = 0
 constraint_30_(q0):  - 0.4 X(z0,i0,t0,k0) + X(i0,d0,t0,k0)
                      - 0.4 X(z0,i0,t0,k1) + X(i0,d0,t0,k1)
                      - 0.4 X(z0,i0,t0,k2) + X(i0,d0,t0,k2) + Sl(i0,t0)  = 0
 constraint_30_(q1):  - 0.4 X(z0,i0,t1,k0) + X(i0,d0,t1,k0)
                      - 0.4 X(z0,i0,t1,k1) + X(i0,d0,t1,k1)
                      - 0.4 X(z0,i0,t1,k2) + X(i0,d0,t1,k2) - Sl(i0,t0)
                      + Sl(i0,t1)  = 0
 constraint_30_(q2):  - 0.4 X(z0,i0,t2,k0) + X(i0,d0,t2,k0)
                      - 0.4 X(z0,i0,t2,k1) + X(i0,d0,t2,k1)
                      - 0.4 X(z0,i0,t2,k2) + X(i0,d0,t2,k2) - Sl(i0,t1)
                      + Sl(i0,t2)  = 0
 constraint_31_(q0):  - 45 FF(f0,h0,o0,t0) - 45 FF(f0,h0,o1,t0)
                      - 45 FF(f0,h0,o2,t0) - 45 FF(f0,h0,o3,t0)
                      + X(f0,i0,t0,k0) + X(f0,i0,t0,k1) + X(f0,i0,t0,k2) <= 0
 constraint_31_(q1):  - 45 FF(f0,h0,o0,t0) - 45 FF(f0,h0,o1,t0)
                      - 45 FF(f0,h0,o2,t0) - 45 FF(f0,h0,o3,t0)
                      - 45 FF(f0,h0,o0,t1) - 45 FF(f0,h0,o1,t1)
                      - 45 FF(f0,h0,o2,t1) - 45 FF(f0,h0,o3,t1)
                      + X(f0,i0,t1,k0) + X(f0,i0,t1,k1) + X(f0,i0,t1,k2) <= 0
 constraint_31_(q2):  - 45 FF(f0,h0,o0,t0) - 45 FF(f0,h0,o1,t0)
                      - 45 FF(f0,h0,o2,t0) - 45 FF(f0,h0,o3,t0)
                      - 45 FF(f0,h0,o0,t1) - 45 FF(f0,h0,o1,t1)
                      - 45 FF(f0,h0,o2,t1) - 45 FF(f0,h0,o3,t1)
                      - 45 FF(f0,h0,o0,t2) - 45 FF(f0,h0,o1,t2)
                      - 45 FF(f0,h0,o2,t2) - 45 FF(f0,h0,o3,t2)
                      + X(f0,i0,t2,k0) + X(f0,i0,t2,k1) + X(f0,i0,t2,k2) <= 0
 constraint_31_(q3):  - 45 FF(f1,h0,o0,t0) - 45 FF(f1,h0,o1,t0)
                      - 45 FF(f1,h0,o2,t0) - 45 FF(f1,h0,o3,t0)
                      + X(f1,i0,t0,k0) + X(f1,i0,t0,k1) + X(f1,i0,t0,k2) <= 0
 constraint_31_(q4):  - 45 FF(f1,h0,o0,t0) - 45 FF(f1,h0,o1,t0)
                      - 45 FF(f1,h0,o2,t0) - 45 FF(f1,h0,o3,t0)
                      - 45 FF(f1,h0,o0,t1) - 45 FF(f1,h0,o1,t1)
                      - 45 FF(f1,h0,o2,t1) - 45 FF(f1,h0,o3,t1)
                      + X(f1,i0,t1,k0) + X(f1,i0,t1,k1) + X(f1,i0,t1,k2) <= 0
 constraint_31_(q5):  - 45 FF(f1,h0,o0,t0) - 45 FF(f1,h0,o1,t0)
                      - 45 FF(f1,h0,o2,t0) - 45 FF(f1,h0,o3,t0)
                      - 45 FF(f1,h0,o0,t1) - 45 FF(f1,h0,o1,t1)
                      - 45 FF(f1,h0,o2,t1) - 45 FF(f1,h0,o3,t1)
                      - 45 FF(f1,h0,o0,t2) - 45 FF(f1,h0,o1,t2)
                      - 45 FF(f1,h0,o2,t2) - 45 FF(f1,h0,o3,t2)
                      + X(f1,i0,t2,k0) + X(f1,i0,t2,k1) + X(f1,i0,t2,k2) <= 0
 constraint_32_(q0):  FF(f0,h0,o0,t0) + FF(f0,h0,o1,t0) + FF(f0,h0,o2,t0)
                      + FF(f0,h0,o3,t0) + FF(f0,h0,o0,t1) + FF(f0,h0,o1,t1)
                      + FF(f0,h0,o2,t1) + FF(f0,h0,o3,t1) + FF(f0,h0,o0,t2)
                      + FF(f0,h0,o1,t2) + FF(f0,h0,o2,t2) + FF(f0,h0,o3,t2)
                      <= 1
 constraint_32_(q1):  FF(f1,h0,o0,t0) + FF(f1,h0,o1,t0) + FF(f1,h0,o2,t0)
                      + FF(f1,h0,o3,t0) + FF(f1,h0,o0,t1) + FF(f1,h0,o1,t1)
                      + FF(f1,h0,o2,t1) + FF(f1,h0,o3,t1) + FF(f1,h0,o0,t2)
                      + FF(f1,h0,o1,t2) + FF(f1,h0,o2,t2) + FF(f1,h0,o3,t2)
                      <= 1
 constraint_33_(q0):  - 8000 WH(i0,h0,t0) + X(i0,z0,t0,k0) + X(z0,i0,t0,k0)
                      + X(i0,z0,t0,k1) + X(z0,i0,t0,k1) + X(i0,z0,t0,k2)
                      + X(z0,i0,t0,k2) + Sh(i0,t0) + Sm(i0,t0) + Sl(i0,t0)
                      + Sn(i0,t0) <= 0
 constraint_33_(q1):  - 8000 WH(i0,h0,t0) - 8000 WH(i0,h0,t1) + X(i0,z0,t1,k0)
                      + X(z0,i0,t1,k0) + X(i0,z0,t1,k1) + X(z0,i0,t1,k1)
                      + X(i0,z0,t1,k2) + X(z0,i0,t1,k2) + Sh(i0,t1) + Sm(i0,t1)
                      + Sl(i0,t1) + Sn(i0,t1) <= 0
 constraint_33_(q2):  - 8000 WH(i0,h0,t0) - 8000 WH(i0,h0,t1)
                      - 8000 WH(i0,h0,t2) + X(i0,z0,t2,k0) + X(z0,i0,t2,k0)
                      + X(i0,z0,t2,k1) + X(z0,i0,t2,k1) + X(i0,z0,t2,k2)
                      + X(z0,i0,t2,k2) + Sh(i0,t2) + Sm(i0,t2) + Sl(i0,t2)
                      + Sn(i0,t2) <= 0
 constraint_32_(i0):  WH(i0,h0,t0) + WH(i0,h0,t1) + WH(i0,h0,t2) <= 1
 constraint_35_(q0):  - 200 DA(d0,j0,t0) + X(i0,d0,t0,k0) + X(i0,d0,t0,k1)
                      + X(i0,d0,t0,k2) <= 0
 constraint_35_(q1):  - 200 DA(d0,j0,t0) - 200 DA(d0,j0,t1) + X(i0,d0,t1,k0)
                      + X(i0,d0,t1,k1) + X(i0,d0,t1,k2) <= 0
 constraint_35_(q2):  - 200 DA(d0,j0,t0) - 200 DA(d0,j0,t1) - 200 DA(d0,j0,t2)
                      + X(i0,d0,t2,k0) + X(i0,d0,t2,k1) + X(i0,d0,t2,k2) <= 0
 constraint_36_(d0):  DA(d0,j0,t0) + DA(d0,j0,t1) + DA(d0,j0,t2) <= 1
 constraint_37_(q0):  - 400 RF(d0,h0,t0) + X(n0,i0,t0,k0) + X(n0,i0,t0,k1)
                      + X(n0,i0,t0,k2) <= 0
 constraint_37_(q1):  - 400 RF(d0,h0,t0) - 400 RF(d0,h0,t1) + X(n0,i0,t1,k0)
                      + X(n0,i0,t1,k1) + X(n0,i0,t1,k2) <= 0
 constraint_37_(q2):  - 400 RF(d0,h0,t0) - 400 RF(d0,h0,t1) - 400 RF(d0,h0,t2)
                      + X(n0,i0,t2,k0) + X(n0,i0,t2,k1) + X(n0,i0,t2,k2) <= 0
 constraint_38_(n0):  RF(d0,h0,t0) + RF(d0,h0,t1) + RF(d0,h0,t2) <= 1
 constraint_39_(q0):  - 200 RM(m0,h0,t0) + X(m0,i0,t0,k0) + X(m0,i0,t0,k1)
                      + X(m0,i0,t0,k2) <= 0
 constraint_39_(q1):  - 200 RM(m0,h0,t0) - 200 RM(m0,h0,t1) + X(m0,i0,t1,k0)
                      + X(m0,i0,t1,k1) + X(m0,i0,t1,k2) <= 0
 constraint_39_(q2):  - 200 RM(m0,h0,t0) - 200 RM(m0,h0,t1) - 200 RM(m0,h0,t2)
                      + X(m0,i0,t2,k0) + X(m0,i0,t2,k1) + X(m0,i0,t2,k2) <= 0
 constraint_40_(m0):  RM(m0,h0,t0) + RM(m0,h0,t1) + RM(m0,h0,t2) <= 1
 constraint_41_(q0):  - 200 Q(q0,h0,t0) + XM(j0,d0,q0,t0,k0)
                      + XM(j0,d0,q0,t0,k1) + XM(j0,d0,q0,t0,k2) <= 0
 constraint_41_(q1):  - 200 Q(q0,h0,t0) - 200 Q(q0,h0,t1) + XM(j0,d0,q0,t1,k0)
                      + XM(j0,d0,q0,t1,k1) + XM(j0,d0,q0,t1,k2) <= 0
 constraint_41_(q2):  - 200 Q(q0,h0,t0) - 200 Q(q0,h0,t1) - 200 Q(q0,h0,t2)
                      + XM(j0,d0,q0,t2,k0) + XM(j0,d0,q0,t2,k1)
                      + XM(j0,d0,q0,t2,k2) <= 0
 constraint_42_(q0):  Q(q0,h0,t0) + Q(q0,h0,t1) + Q(q0,h0,t2) <= 1
 constraint_43_(t0):  FC(t0) <= 400000
 constraint_43_(t1):  FC(t0) + FC(t1) <= 800000
 constraint_43_(t2):  FC(t0) + 1.1 FC(t1) + FC(t2) <= 840000
 constraint_44_(q0):  X(i0,z0,t0,k0) + X(i0,z0,t0,k1) + X(i0,z0,t0,k2) >= 50
 constraint_44_(q1):  X(i0,z0,t1,k0) + X(i0,z0,t1,k1) + X(i0,z0,t1,k2) >= 100
 constraint_44_(q2):  X(i0,z0,t2,k0) + X(i0,z0,t2,k1) + X(i0,z0,t2,k2) >= 190
 constraint_45_(q0):  X(z0,i0,t0,k0) + X(z0,i0,t0,k1) + X(z0,i0,t0,k2) >= 50
 constraint_45_(q1):  X(z0,i0,t1,k0) + X(z0,i0,t1,k1) + X(z0,i0,t1,k2) >= 100
 constraint_45_(q2):  X(z0,i0,t2,k0) + X(z0,i0,t2,k1) + X(z0,i0,t2,k2) >= 190
 constraints46:       TR1(k0) + TR1(k1) + TR1(k2)  = 1
 constraints47:       TR2(k0) + TR2(k1) + TR2(k2)  = 1
 constraints48:       TR3(k0) + TR3(k1) + TR3(k2)  = 1
 constraint_49_(k0):  XM(j0,s0,f0,t0,k0) + XM(j0,s0,f1,t0,k0)
                      + XM(j0,d0,f0,t0,k0) + XM(j0,d0,f1,t0,k0)
                      + XM(j0,s0,m0,t0,k0) + XM(j0,s0,f0,t1,k0)
                      + XM(j0,s0,f1,t1,k0) + XM(j0,d0,f0,t1,k0)
                      + XM(j0,d0,f1,t1,k0) + XM(j0,s0,m0,t1,k0)
                      + XM(j0,s0,f0,t2,k0) + XM(j0,s0,f1,t2,k0)
                      + XM(j0,d0,f0,t2,k0) + XM(j0,d0,f1,t2,k0)
                      + XM(j0,s0,m0,t2,k0) - 1.79769313486232e+308 TR1(k0) <= 0
 constraint_49_(k1):  XM(j0,s0,f0,t0,k1) + XM(j0,s0,f1,t0,k1)
                      + XM(j0,d0,f0,t0,k1) + XM(j0,d0,f1,t0,k1)
                      + XM(j0,s0,m0,t0,k1) + XM(j0,s0,f0,t1,k1)
                      + XM(j0,s0,f1,t1,k1) + XM(j0,d0,f0,t1,k1)
                      + XM(j0,d0,f1,t1,k1) + XM(j0,s0,m0,t1,k1)
                      + XM(j0,s0,f0,t2,k1) + XM(j0,s0,f1,t2,k1)
                      + XM(j0,d0,f0,t2,k1) + XM(j0,d0,f1,t2,k1)
                      + XM(j0,s0,m0,t2,k1) - 1.79769313486232e+308 TR1(k1) <= 0
 constraint_49_(k2):  XM(j0,s0,f0,t0,k2) + XM(j0,s0,f1,t0,k2)
                      + XM(j0,d0,f0,t0,k2) + XM(j0,d0,f1,t0,k2)
                      + XM(j0,s0,m0,t0,k2) + XM(j0,s0,f0,t1,k2)
                      + XM(j0,s0,f1,t1,k2) + XM(j0,d0,f0,t1,k2)
                      + XM(j0,d0,f1,t1,k2) + XM(j0,s0,m0,t1,k2)
                      + XM(j0,s0,f0,t2,k2) + XM(j0,s0,f1,t2,k2)
                      + XM(j0,d0,f0,t2,k2) + XM(j0,d0,f1,t2,k2)
                      + XM(j0,s0,m0,t2,k2) - 1.79769313486232e+308 TR1(k2) <= 0
 constraint_50_(k0):  X(f0,i0,t0,k0) + X(f1,i0,t0,k0) + X(i0,z0,t0,k0)
                      + X(z0,i0,t0,k0) + X(i0,n0,t0,k0) + X(n0,i0,t0,k0)
                      + X(i0,m0,t0,k0) + X(m0,i0,t0,k0) + X(f0,i0,t1,k0)
                      + X(f1,i0,t1,k0) + X(i0,z0,t1,k0) + X(z0,i0,t1,k0)
                      + X(i0,n0,t1,k0) + X(n0,i0,t1,k0) + X(i0,m0,t1,k0)
                      + X(m0,i0,t1,k0) + X(f0,i0,t2,k0) + X(f1,i0,t2,k0)
                      + X(i0,z0,t2,k0) + X(z0,i0,t2,k0) + X(i0,n0,t2,k0)
                      + X(n0,i0,t2,k0) + X(i0,m0,t2,k0) + X(m0,i0,t2,k0)
                      - 1.79769313486232e+308 TR2(k0) <= 0
 constraint_50_(k1):  X(f0,i0,t0,k1) + X(f1,i0,t0,k1) + X(i0,z0,t0,k1)
                      + X(z0,i0,t0,k1) + X(i0,n0,t0,k1) + X(n0,i0,t0,k1)
                      + X(i0,m0,t0,k1) + X(m0,i0,t0,k1) + X(f0,i0,t1,k1)
                      + X(f1,i0,t1,k1) + X(i0,z0,t1,k1) + X(z0,i0,t1,k1)
                      + X(i0,n0,t1,k1) + X(n0,i0,t1,k1) + X(i0,m0,t1,k1)
                      + X(m0,i0,t1,k1) + X(f0,i0,t2,k1) + X(f1,i0,t2,k1)
                      + X(i0,z0,t2,k1) + X(z0,i0,t2,k1) + X(i0,n0,t2,k1)
                      + X(n0,i0,t2,k1) + X(i0,m0,t2,k1) + X(m0,i0,t2,k1)
                      - 1.79769313486232e+308 TR2(k1) <= 0
 constraint_50_(k2):  X(f0,i0,t0,k2) + X(f1,i0,t0,k2) + X(i0,z0,t0,k2)
                      + X(z0,i0,t0,k2) + X(i0,n0,t0,k2) + X(n0,i0,t0,k2)
                      + X(i0,m0,t0,k2) + X(m0,i0,t0,k2) + X(f0,i0,t1,k2)
                      + X(f1,i0,t1,k2) + X(i0,z0,t1,k2) + X(z0,i0,t1,k2)
                      + X(i0,n0,t1,k2) + X(n0,i0,t1,k2) + X(i0,m0,t1,k2)
                      + X(m0,i0,t1,k2) + X(f0,i0,t2,k2) + X(f1,i0,t2,k2)
                      + X(i0,z0,t2,k2) + X(z0,i0,t2,k2) + X(i0,n0,t2,k2)
                      + X(n0,i0,t2,k2) + X(i0,m0,t2,k2) + X(m0,i0,t2,k2)
                      - 1.79769313486232e+308 TR2(k2) <= 0
 constraint_51_(k0):  XM(j0,d0,q0,t0,k0) + XM(j0,d0,q0,t1,k0)
                      + XM(j0,d0,q0,t2,k0) - 1.79769313486232e+308 TR3(k0) <= 0
 constraint_51_(k1):  XM(j0,d0,q0,t0,k1) + XM(j0,d0,q0,t1,k1)
                      + XM(j0,d0,q0,t2,k1) - 1.79769313486232e+308 TR3(k1) <= 0
 constraint_51_(k2):  XM(j0,d0,q0,t0,k2) + XM(j0,d0,q0,t1,k2)
                      + XM(j0,d0,q0,t2,k2) - 1.79769313486232e+308 TR3(k2) <= 0
 constraint_52_(q0):  - X(f0,i0,t0,k0) - X(f0,i0,t0,k1) - X(f0,i0,t0,k2)
                      + P(f0,o0,t0) + P(f0,o1,t0) + P(f0,o2,t0) + P(f0,o3,t0)
                       = 0
 constraint_52_(q1):  - X(f0,i0,t1,k0) - X(f0,i0,t1,k1) - X(f0,i0,t1,k2)
                      + P(f0,o0,t1) + P(f0,o1,t1) + P(f0,o2,t1) + P(f0,o3,t1)
                       = 0
 constraint_52_(q2):  - X(f0,i0,t2,k0) - X(f0,i0,t2,k1) - X(f0,i0,t2,k2)
                      + P(f0,o0,t2) + P(f0,o1,t2) + P(f0,o2,t2) + P(f0,o3,t2)
                       = 0
 constraint_52_(q3):  - X(f1,i0,t0,k0) - X(f1,i0,t0,k1) - X(f1,i0,t0,k2)
                      + P(f1,o0,t0) + P(f1,o1,t0) + P(f1,o2,t0) + P(f1,o3,t0)
                       = 0
 constraint_52_(q4):  - X(f1,i0,t1,k0) - X(f1,i0,t1,k1) - X(f1,i0,t1,k2)
                      + P(f1,o0,t1) + P(f1,o1,t1) + P(f1,o2,t1) + P(f1,o3,t1)
                       = 0
 constraint_52_(q5):  - X(f1,i0,t2,k0) - X(f1,i0,t2,k1) - X(f1,i0,t2,k2)
                      + P(f1,o0,t2) + P(f1,o1,t2) + P(f1,o2,t2) + P(f1,o3,t2)
                       = 0
 constraint_53_(q0):  - 1.79769313486232e+308 FF(f0,h0,o0,t0) + P(f0,o0,t0)
                      <= 0
 constraint_53_(q1):  - 1.79769313486232e+308 FF(f1,h0,o0,t0) + P(f1,o0,t0)
                      <= 0
 constraint_53_(q2):  - 1.79769313486232e+308 FF(f0,h0,o0,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o0,t1) + P(f0,o0,t1)
                      <= 0
 constraint_53_(q3):  - 1.79769313486232e+308 FF(f1,h0,o0,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o0,t1) + P(f1,o0,t1)
                      <= 0
 constraint_53_(q4):  - 1.79769313486232e+308 FF(f0,h0,o0,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o0,t1)
                      - 1.79769313486232e+308 FF(f0,h0,o0,t2) + P(f0,o0,t2)
                      <= 0
 constraint_53_(q5):  - 1.79769313486232e+308 FF(f1,h0,o0,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o0,t1)
                      - 1.79769313486232e+308 FF(f1,h0,o0,t2) + P(f1,o0,t2)
                      <= 0
 constraint_53_(q6):  - 1.79769313486232e+308 FF(f0,h0,o1,t0) + P(f0,o1,t0)
                      <= 0
 constraint_53_(q7):  - 1.79769313486232e+308 FF(f1,h0,o1,t0) + P(f1,o1,t0)
                      <= 0
 constraint_53_(q8):  - 1.79769313486232e+308 FF(f0,h0,o1,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o1,t1) + P(f0,o1,t1)
                      <= 0
 constraint_53_(q9):  - 1.79769313486232e+308 FF(f1,h0,o1,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o1,t1) + P(f1,o1,t1)
                      <= 0
 constraint_53_(q10): - 1.79769313486232e+308 FF(f0,h0,o1,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o1,t1)
                      - 1.79769313486232e+308 FF(f0,h0,o1,t2) + P(f0,o1,t2)
                      <= 0
 constraint_53_(q11): - 1.79769313486232e+308 FF(f1,h0,o1,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o1,t1)
                      - 1.79769313486232e+308 FF(f1,h0,o1,t2) + P(f1,o1,t2)
                      <= 0
 constraint_53_(q12): - 1.79769313486232e+308 FF(f0,h0,o2,t0) + P(f0,o2,t0)
                      <= 0
 constraint_53_(q13): - 1.79769313486232e+308 FF(f1,h0,o2,t0) + P(f1,o2,t0)
                      <= 0
 constraint_53_(q14): - 1.79769313486232e+308 FF(f0,h0,o2,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o2,t1) + P(f0,o2,t1)
                      <= 0
 constraint_53_(q15): - 1.79769313486232e+308 FF(f1,h0,o2,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o2,t1) + P(f1,o2,t1)
                      <= 0
 constraint_53_(q16): - 1.79769313486232e+308 FF(f0,h0,o2,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o2,t1)
                      - 1.79769313486232e+308 FF(f0,h0,o2,t2) + P(f0,o2,t2)
                      <= 0
 constraint_53_(q17): - 1.79769313486232e+308 FF(f1,h0,o2,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o2,t1)
                      - 1.79769313486232e+308 FF(f1,h0,o2,t2) + P(f1,o2,t2)
                      <= 0
 constraint_53_(q18): - 1.79769313486232e+308 FF(f0,h0,o3,t0) + P(f0,o3,t0)
                      <= 0
 constraint_53_(q19): - 1.79769313486232e+308 FF(f1,h0,o3,t0) + P(f1,o3,t0)
                      <= 0
 constraint_53_(q20): - 1.79769313486232e+308 FF(f0,h0,o3,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o3,t1) + P(f0,o3,t1)
                      <= 0
 constraint_53_(q21): - 1.79769313486232e+308 FF(f1,h0,o3,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o3,t1) + P(f1,o3,t1)
                      <= 0
 constraint_53_(q22): - 1.79769313486232e+308 FF(f0,h0,o3,t0)
                      - 1.79769313486232e+308 FF(f0,h0,o3,t1)
                      - 1.79769313486232e+308 FF(f0,h0,o3,t2) + P(f0,o3,t2)
                      <= 0
 constraint_53_(q23): - 1.79769313486232e+308 FF(f1,h0,o3,t0)
                      - 1.79769313486232e+308 FF(f1,h0,o3,t1)
                      - 1.79769313486232e+308 FF(f1,h0,o3,t2) + P(f1,o3,t2)
                      <= 0
Bounds
      NTC Free
 0 <= FF(f0,h0,o0,t0) <= 1
 0 <= FF(f0,h0,o1,t0) <= 1
 0 <= FF(f0,h0,o2,t0) <= 1
 0 <= FF(f0,h0,o3,t0) <= 1
 0 <= FF(f1,h0,o0,t0) <= 1
 0 <= FF(f1,h0,o1,t0) <= 1
 0 <= FF(f1,h0,o2,t0) <= 1
 0 <= FF(f1,h0,o3,t0) <= 1
 0 <= WH(i0,h0,t0) <= 1
 0 <= DA(d0,j0,t0) <= 1
 0 <= RF(d0,h0,t0) <= 1
 0 <= RM(m0,h0,t0) <= 1
 0 <= Q(q0,h0,t0) <= 1
 0 <= FF(f0,h0,o0,t1) <= 1
 0 <= FF(f0,h0,o1,t1) <= 1
 0 <= FF(f0,h0,o2,t1) <= 1
 0 <= FF(f0,h0,o3,t1) <= 1
 0 <= FF(f1,h0,o0,t1) <= 1
 0 <= FF(f1,h0,o1,t1) <= 1
 0 <= FF(f1,h0,o2,t1) <= 1
 0 <= FF(f1,h0,o3,t1) <= 1
 0 <= WH(i0,h0,t1) <= 1
 0 <= DA(d0,j0,t1) <= 1
 0 <= RF(d0,h0,t1) <= 1
 0 <= RM(m0,h0,t1) <= 1
 0 <= Q(q0,h0,t1) <= 1
 0 <= FF(f0,h0,o0,t2) <= 1
 0 <= FF(f0,h0,o1,t2) <= 1
 0 <= FF(f0,h0,o2,t2) <= 1
 0 <= FF(f0,h0,o3,t2) <= 1
 0 <= FF(f1,h0,o0,t2) <= 1
 0 <= FF(f1,h0,o1,t2) <= 1
 0 <= FF(f1,h0,o2,t2) <= 1
 0 <= FF(f1,h0,o3,t2) <= 1
 0 <= WH(i0,h0,t2) <= 1
 0 <= DA(d0,j0,t2) <= 1
 0 <= RF(d0,h0,t2) <= 1
 0 <= RM(m0,h0,t2) <= 1
 0 <= Q(q0,h0,t2) <= 1
 0 <= W(e0,t0) <= 1
 0 <= W(e0,t1) <= 1
 0 <= W(e0,t2) <= 1
 0 <= W(e1,t0) <= 1
 0 <= W(e1,t1) <= 1
 0 <= W(e1,t2) <= 1
 0 <= W(e2,t0) <= 1
 0 <= W(e2,t1) <= 1
 0 <= W(e2,t2) <= 1
 0 <= TR1(k0) <= 1
 0 <= TR1(k1) <= 1
 0 <= TR1(k2) <= 1
 0 <= TR2(k0) <= 1
 0 <= TR2(k1) <= 1
 0 <= TR2(k2) <= 1
 0 <= TR3(k0) <= 1
 0 <= TR3(k1) <= 1
 0 <= TR3(k2) <= 1
Binaries
 FF(f0,h0,o0,t0)  FF(f0,h0,o1,t0)  FF(f0,h0,o2,t0)  FF(f0,h0,o3,t0) 
 FF(f1,h0,o0,t0)  FF(f1,h0,o1,t0)  FF(f1,h0,o2,t0)  FF(f1,h0,o3,t0) 
 WH(i0,h0,t0)  DA(d0,j0,t0)  RF(d0,h0,t0)  RM(m0,h0,t0)  Q(q0,h0,t0) 
 FF(f0,h0,o0,t1)  FF(f0,h0,o1,t1)  FF(f0,h0,o2,t1)  FF(f0,h0,o3,t1) 
 FF(f1,h0,o0,t1)  FF(f1,h0,o1,t1)  FF(f1,h0,o2,t1)  FF(f1,h0,o3,t1) 
 WH(i0,h0,t1)  DA(d0,j0,t1)  RF(d0,h0,t1)  RM(m0,h0,t1)  Q(q0,h0,t1) 
 FF(f0,h0,o0,t2)  FF(f0,h0,o1,t2)  FF(f0,h0,o2,t2)  FF(f0,h0,o3,t2) 
 FF(f1,h0,o0,t2)  FF(f1,h0,o1,t2)  FF(f1,h0,o2,t2)  FF(f1,h0,o3,t2) 
 WH(i0,h0,t2)  DA(d0,j0,t2)  RF(d0,h0,t2)  RM(m0,h0,t2)  Q(q0,h0,t2)  W(e0,t0) 
 W(e0,t1)  W(e0,t2)  W(e1,t0)  W(e1,t1)  W(e1,t2)  W(e2,t0)  W(e2,t1) 
 W(e2,t2)  TR1(k0)  TR1(k1)  TR1(k2)  TR2(k0)  TR2(k1)  TR2(k2)  TR3(k0) 
 TR3(k1)  TR3(k2) 
End
