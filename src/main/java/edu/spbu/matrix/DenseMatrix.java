package main.java.edu.spbu.matrix;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

class DenseP implements Runnable{
    DenseMatrix M,res;
    double [][]m;
    int a,b;

    public DenseP(int a, int b, double[][] m, DenseMatrix M, DenseMatrix res) {

        this.m = m;
        this.M = M;
        this.a = a;
        this.b = b;
        this.res = res;
    }

    public DenseMatrix transp(DenseMatrix other){
        if(other.SizeM>0 && other.SizeN>0){
            DenseMatrix res = new DenseMatrix(other.SizeN,other.SizeM);

            for(int i=0;i<other.SizeM;i++)
                for(int j=0;j<other.SizeN;j++)
                    res.MatrixD[j][i]=other.MatrixD[i][j];

            return res;
        }

        else {
            System.out.println("Wrong parameters");
            return null;
        }

    }

    @Override
    public void run() {

        DenseMatrix tr = transp(M);
        for (int i = a; i < b; i++) {
            for (int j = 0; j < M.MatrixD[0].length; j++) {
                res.MatrixD[i][j] = 0.0;

                for (int k = 0; k < m[0].length; k++)
                    res.MatrixD[i][j] = res.MatrixD[i][j] + m[i][k] * tr.MatrixD[j][k];//M.MatrixD[k][j];//tr.MatrixD[j][k];  //


            }
        }
    }

}


public class DenseMatrix implements Matrix{
    double[][] MatrixD;
    int SizeM,SizeN;

    public DenseMatrix(int x, int y) {
        MatrixD = new double[x][y];
        SizeM=x;
        SizeN=y;
    }

    public DenseMatrix(String adress) {
        ArrayList<String> matrix = new ArrayList<String>();


        Scanner in = null;
        try {
            in = new Scanner(new File(adress));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int M = 0, N = 0;

        while (in.hasNextLine()) {
            M++;
            matrix.add(in.nextLine());

        }
        in.close();

        N = matrix.get(0).split(" ").length;

        SizeM=M;
        SizeN=N;

        MatrixD = new double[M][N];
        for (int i = 0; i < M; i++) {
            String[] s = matrix.get(i).split(" ");
            for (int j = 0; j < N; j++) {
                this.MatrixD[i][j] = parseDouble(s[j]);
            }
            // s=null;
        }

    }

    @Override
    public String toString() {
        return "Dense Matrix";
    }

    public void Denseout() {
        for (int i = 0; i < MatrixD.length; i++) {
            for (int j = 0; j < MatrixD[0].length; j++) {
                System.out.print(MatrixD[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Matrix mul(Matrix other){
        if (other instanceof DenseMatrix)
            return this.mulDD( (DenseMatrix) other);

        else if(other instanceof SparseMatrix)
            return this.mulDS((SparseMatrix) other);

        else return null;
    }

    public Matrix pmul (Matrix other) throws InterruptedException {
        if (other instanceof DenseMatrix) {
                return this.pmulDD((DenseMatrix) other);


            // else if(other instanceof SparseMatrix)
            //    return this.pmulDS((SparseMatrix) other);

        }
        else
            return null;
    }



    public DenseMatrix mulDD(DenseMatrix other){
        if (MatrixD[0].length == other.MatrixD.length) {
            DenseMatrix res = new DenseMatrix(MatrixD.length, other.MatrixD[0].length);
            DenseMatrix tr = transpD(other);
            for (int i = 0; i < MatrixD.length; i++) {
                for (int j = 0; j < other.MatrixD[0].length; j++) {
                    res.MatrixD[i][j] = 0.0;
                    for (int k = 0; k < MatrixD[0].length; k++) {
                        res.MatrixD[i][j] = res.MatrixD[i][j] + this.MatrixD[i][k] * tr.MatrixD[j][k];//other.MatrixD[k][j];

                    }
                }

            }

         //   res.Denseout();
            return res;
        }

        else{
            System.out.println("This matrix cant be multiplied");
            return null;
        }
    }

    public DenseMatrix pmulDD(DenseMatrix other) throws InterruptedException {

        if (this.SizeM == this.SizeN && other.SizeM == other.SizeN && this.SizeN == other.SizeM) {

            int N = this.SizeN;
            DenseMatrix res = new DenseMatrix(N, N);
            //  Thread t1,t2,t3,t4;
            int k = N / 11, z = 0;

            DenseP t1 = new DenseP(0, k, MatrixD, other, res);
            DenseP t2 = new DenseP(k, 2 * k, MatrixD, other, res);
            DenseP t3 = new DenseP(2 * k, 3 * k, MatrixD, other, res);
            DenseP t4 = new DenseP(3 * k, 4 * k, MatrixD, other, res);
            DenseP t5 = new DenseP(4 * k, 5 * k, MatrixD, other, res);
            DenseP t6 = new DenseP(5 * k, 6 * k, MatrixD, other, res);
            DenseP t7 = new DenseP(6*k,7*k,MatrixD,other,res);
            DenseP t8 = new DenseP(7*k,8*k,MatrixD,other,res);
            DenseP t9 = new DenseP(8*k,9*k,MatrixD,other,res);
            DenseP t10 = new DenseP(9*k,10*k,MatrixD,other,res);
            DenseP t11 = new DenseP(10*k,11*k,MatrixD,other,res);

            Thread thr1 = new Thread(t1);
            thr1.start();

            Thread thr2 = new Thread(t2);
            thr2.start();

            Thread thr3 = new Thread(t3);
            thr3.start();

            Thread thr4 = new Thread(t4);
            thr4.start();

            Thread thr5 = new Thread(t5);
            thr5.start();

            Thread thr6 = new Thread(t6);
            thr6.start();

            Thread thr7 = new Thread(t7);
            thr7.start();

            Thread thr8 = new Thread(t8);
            thr8.start();

            Thread thr9 = new Thread(t9);
            thr9.start();

            Thread thr10 = new Thread(t10);
            thr10.start();

            Thread thr11 = new Thread(t11);
            thr11.start();

            //ThreadGroup
           /* while (thr1.isAlive() || thr2.isAlive() || thr3.isAlive() || thr4.isAlive())
              z++;*/

            thr1.join();
            thr2.join();
            thr3.join();
            thr4.join();
            thr5.join();
            thr6.join();
            thr7.join();
            thr8.join();
            thr9.join();
            thr10.join();
            thr11.join();


            if(N % 11 !=0) {
                DenseP tmp = new DenseP(11 * k, N, MatrixD, other, res);
                Thread threadtmp = new Thread(tmp);

                threadtmp.start();
                threadtmp.join();
            }

            return res;
        } else {
            System.out.println("Wrong parameters");
            return null;
        }

    }


    public SparseMatrix mulDS(SparseMatrix other)  {
        if(this.SizeN==other.SizeM){
            SparseMatrix res = new SparseMatrix(this.SizeM,other.SizeN);
            //  double mult;
            for(VectorCord keyOM: other.MatrixS.keySet()){
                for(int j=0;j<this.SizeM;j++){
                    VectorCord v = new VectorCord(j,keyOM.y);
                    if(res.MatrixS.get(v)==null){
                        //    mult=(double)this.MatrixS.get(keyTM)*other.MatrixD[keyTM.y][j];

                        // if(mult != 0.0) {
                        res.MatrixS.put(v, other.MatrixS.get(keyOM) * this.MatrixD[j][keyOM.x]);
                        //  }
                    }

                    else{
                        res.MatrixS.put(v,res.MatrixS.get(v) + other.MatrixS.get(keyOM)*this.MatrixD[j][keyOM.x]);
                    }
                }
            }
            Iterator<VectorCord> it = res.MatrixS.keySet().iterator();
            while (it.hasNext()){
                if(res.MatrixS.get(it.next()).equals(0.0))
                    it.remove();
            }
           //   res.SparseOut();
            return res;
        }

        else{
            System.out.println("This matrix cant be multiplied");
            return null;
        }

    }

    public boolean equals (Object o) {
        // if (!(o instanceof DenseMatrix)) {
        //      return false;
        //  }
        if (o instanceof DenseMatrix){
            DenseMatrix other = (DenseMatrix) o;
        boolean flag = true;
        if (MatrixD.length == other.MatrixD.length && MatrixD[0].length == other.MatrixD[0].length) {
            for (int i = 0; i < MatrixD.length; i++)
                for (int j = 0; j < MatrixD[0].length; j++) {
                    if (MatrixD[i][j] != other.MatrixD[i][j])
                        flag = false;
                }
        } else {
            System.out.println("This matrix cant be equal");
            flag = false;
        }
        return flag;

        }

        else {
                if(o instanceof SparseMatrix)
                {
                    SparseMatrix other = (SparseMatrix) o;
                    if(this.SizeN!=other.SizeM)
                        return false;

                    else{
                        VectorCord keyOM;
                        for (Map.Entry entryOM: other.MatrixS.entrySet()) {
                            keyOM = (VectorCord) entryOM.getKey();
                            if (!(other.MatrixS.get(keyOM).equals(this.MatrixD[keyOM.x][keyOM.y])))
                                return false;
                        }
                        return true;
                    }
                }

                else
                    return false;

            }

    }

    public DenseMatrix transpD(DenseMatrix other){
        if(other.SizeM>0 && other.SizeN>0){
            DenseMatrix res = new DenseMatrix(other.SizeN,other.SizeM);

            for(int i=0;i<other.SizeM;i++)
                for(int j=0;j<other.SizeN;j++)
                    res.MatrixD[j][i]=other.MatrixD[i][j];

            return res;
        }

        else {
            System.out.println("Wrong parameters");
            return null;
        }

    }
}


/*
    public DenseMatrix pmulDD(DenseMatrix other) throws InterruptedException {

        if(this.SizeM==this.SizeN && other.SizeM==other.SizeN && this.SizeN==other.SizeM) {

          int N = this.SizeN;
          DenseMatrix res = new DenseMatrix(N,N);
        //  Thread t1,t2,t3,t4;
          int k = N/6,z=0;

            DenseP t1 = new DenseP(0,k,MatrixD,other,res);
            DenseP t2 = new DenseP(k,2*k,MatrixD,other,res);
            DenseP t3 = new DenseP(2*k,3*k,MatrixD,other,res);
            DenseP t4 = new DenseP(3*k,4*k,MatrixD,other,res);
            DenseP t5 = new DenseP(4*k,5*k,MatrixD,other,res);
            DenseP t6 = new DenseP(5*k,6*k,MatrixD,other,res);

            Thread thr1 = new Thread(t1);
            thr1.start();

            Thread thr2 = new Thread(t2);
            thr2.start();

            Thread thr3 = new Thread(t3);
            thr3.start();

            Thread thr4 = new Thread(t4);
            thr4.start();

            Thread thr5 = new Thread(t5);
            thr5.start();

            Thread thr6 = new Thread(t6);
            thr6.start();
            //ThreadGroup
           /* while (thr1.isAlive() || thr2.isAlive() || thr3.isAlive() || thr4.isAlive())
              z++;

            thr1.join();
                    thr2.join();
                    thr3.join();
                    thr4.join();
                    thr5.join();
                    thr6.join();

                    DenseP tmp = new DenseP(6*k,N,MatrixD,other,res);
                    Thread threadtmp = new Thread(tmp);

                    threadtmp.start();
                    threadtmp.join();

                    return res;
                    }

                    else{
                    System.out.println("Wrong parameters");
                    return null;
                    }

                    }
 */