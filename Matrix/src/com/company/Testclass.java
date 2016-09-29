package com.company;
import java.util.ArrayList;
/**
 * Created by Sergey Sandalov on 26.09.2016.
 */
public class Testclass {
    public static void main(String[] args) {
        double c = 0.0;
        ArrayList<ArrayList<Double>> matrix1 = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < 5; i++) {
            ArrayList<Double> row = new ArrayList<Double>();
            for (int j = 0; j < 10; j++) {
                row.add(c);
                c = c + 1;
            }
            matrix1.add(row);
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; ++j) {
                System.out.print(matrix1.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}