package main.java.edu.spbu;

import main.java.edu.spbu.matrix.*;

public class MatrixPerfTest
{
    public static final String MATRIX1_NAME = "m1.txt";
    public static final String MATRIX2_NAME = "m2.txt";

    public static void main(String s[]) throws InterruptedException {

        System.out.println("Starting loading dense matrices");
        Matrix m1 = new DenseMatrix(MATRIX1_NAME);
        System.out.println("1 loaded");
        Matrix m2 = new DenseMatrix(MATRIX2_NAME);
        System.out.println("2 loaded");
        long start = System.currentTimeMillis();
        Matrix r1 = m1.mul(m2);
        System.out.println("Dense Matrix time: " + (System.currentTimeMillis() - start));
        System.out.println("Starting loading sparse matrices");
        m1 = new SparseMatrix(MATRIX1_NAME);
        System.out.println("1 loaded");
        m2 = new SparseMatrix(MATRIX2_NAME);
        System.out.println("2 loaded");
        start = System.currentTimeMillis();
        Matrix r2 = m1.mul(m2);
        System.out.println("Sparse Matrix time: " + (System.currentTimeMillis() - start));
        System.out.println("equals Dense*Dense and Sparse*Sparse: " + r1.equals(r2));

        System.out.println("Starting loading denseP matrices");
        m1 = new DenseMatrix("m1.txt");
        System.out.println("1 loaded");
        m2 = new DenseMatrix("m2.txt");
        System.out.println("2 loaded");
        long start2 = System.currentTimeMillis();
        Matrix r3 = m1.pmul(m2);
        System.out.println("DenseP Matrix time: " + (System.currentTimeMillis() - start2));
        System.out.println("equals Sparse*Sparse and DenseP*DenseP: " + r2.equals(r3));

        System.out.println("Starting loading SparseP matrices");
        m1 = new SparseMatrix("m1.txt");
        System.out.println("1 loaded");
        m2 = new SparseMatrix("m2.txt");
        System.out.println("2 loaded");
        start2 = System.currentTimeMillis();
        Matrix r4 = m1.pmul(m2);
        System.out.println("SparseP Matrix time: " + (System.currentTimeMillis() - start2));
        System.out.println("equals DenseP*DenseP and SparseP*SparseP: " + r3.equals(r4));


    }
}