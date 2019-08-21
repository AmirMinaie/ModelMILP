package com.company;

public class Main {

    public static void main(String[] args) {
//        Data data = new Data("./InputData.xlsx");
//        Module module = new Module(data);

        int c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 4; k++) {
                    c++;
                    System.out.print(c + "   " +
                            (i * 8 + j *4 + k) + "\n");
                }
            }
        }

    }
}

