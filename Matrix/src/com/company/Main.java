package com.company;

public class Main {

    public static void main(String[] args) {
        String adress1 = "in1.txt";
        String adress2 = "in2.txt";
        Mat m1 = new Mat(adress1);
        Mat m2 = new Mat(adress2);
        System.out.println("Matrix A is:");
        m1.Matout();
        System.out.println();
        System.out.println("Matrix B is:");
        m2.Matout();
        System.out.println();
        System.out.println("Result of multiplication A*B is:");
        m1.mulMatMat(m2);
        System.out.println();
        System.out.println("Result of add A+B is:");
        m1.addMat(m2);
        System.out.println();
        Mat testadd = new Mat("testadd.txt");
        Mat testmult = new Mat("testmult.txt");
        Mat outadd = new Mat("outadd.txt");
        Mat outmult = new Mat("outmult.txt");
        int k=testadd.isEqual(outadd);
        int m=testmult.isEqual(outmult);
        if(k==1) System.out.println("Mult is correct!");
          else System.out.println("Mult is not correct");

        if(m==1) System.out.println("Add is correct!");
          else System.out.println("Add is not correct");



}

}

