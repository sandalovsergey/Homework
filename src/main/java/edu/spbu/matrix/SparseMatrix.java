package main.java.edu.spbu.matrix;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

class VectorCord {
    int x;
    int y;


    public VectorCord(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VectorCord that = (VectorCord) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

class SparseP implements Runnable {
    SparseMatrix m, M, res;

    public SparseP(SparseMatrix m, SparseMatrix M, SparseMatrix res) {
        this.m = m;
        this.M = M;
        this.res = res;
    }

    @Override
    public void run() {
        VectorCord keyTM, keyOM;
        for (Map.Entry entryTM : m.MatrixS.entrySet()) {
            keyTM = (VectorCord) entryTM.getKey();
            for (Map.Entry entryOM : M.MatrixS.entrySet()) {

                keyOM = (VectorCord) entryOM.getKey();



                    if (keyTM.y == keyOM.x) {
                        VectorCord v = new VectorCord(keyTM.x, keyOM.y);

                        synchronized (res.MatrixS.entrySet()) {

                        if (res.MatrixS.get(v) == null)
                            res.MatrixS.put(v, (double) entryTM.getValue() * (double) entryOM.getValue());

                        else
                            res.MatrixS.put(v, res.MatrixS.get(v) + (double) entryTM.getValue() * (double) entryOM.getValue());
                    }

                }
            }
        }


    }
}


public class SparseMatrix implements Matrix {
    HashMap<VectorCord, Double> MatrixS;
    int SizeM, SizeN;

    public SparseMatrix(String adress) {
        MatrixS = new HashMap<VectorCord, Double>();

        Scanner in = null;
        try {
            in = new Scanner(new File(adress));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SizeN = in.nextLine().split(" ").length;
        in.close();


        Scanner in1 = null;
        try {
            in1 = new Scanner(new File(adress));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;

        while (in1.hasNextLine()) {

            String[] s = in1.nextLine().split(" ");
            // SizeN = s.length;
            for (int j = 0; j < s.length; j++) {
                if (parseDouble(s[j]) != 0.0) {
                    VectorCord v = new VectorCord(i, j);
                    this.MatrixS.put(v, parseDouble(s[j]));

                }
            }
            i++;
        }
        SizeM = i;

        in1.close();


    }

    public SparseMatrix(int x, int y) {
        MatrixS = new HashMap<VectorCord, Double>();

        SizeM = x;
        SizeN = y;
    }

    public SparseMatrix() {
        MatrixS = new HashMap<VectorCord, Double>();
    }

    public void SparseOut() {
        for (int i = 0; i < SizeM; i++) {
            for (int j = 0; j < SizeN; j++) {
                VectorCord v = new VectorCord(i, j);
                if (this.MatrixS.get(v) != null)
                    System.out.print(this.MatrixS.get(v) + " ");

                else System.out.print(0.0 + " ");

            }
            System.out.println();
        }

    }

    @Override
    public boolean equals(Object o) {
        //  if (!(o instanceof SparseMatrix)) {
        //      return false;
        //  }

        if (o instanceof SparseMatrix) {
            SparseMatrix other = (SparseMatrix) o;
            if (this.MatrixS.size() == other.MatrixS.size()) {
                for (VectorCord keyM : this.MatrixS.keySet()) {

                    if (other.MatrixS.get(keyM) == null) {
                        // System.out.println("NULL");
                        return false;
                    } else if (!(this.MatrixS.get(keyM).equals(other.MatrixS.get(keyM))))  // ==???
                    {
                        // System.out.println("Не совпали");
                        return false;
                    }

                }
            } else
                return false;


            return true;
        } else {
            if (o instanceof DenseMatrix) {
                DenseMatrix other = (DenseMatrix) o;
                if (this.SizeN != other.SizeM)
                    return false;

                else {
                    VectorCord keyTM;
                    for (Map.Entry entryTM : this.MatrixS.entrySet()) {
                        keyTM = (VectorCord) entryTM.getKey();
                        if (!(this.MatrixS.get(keyTM).equals(other.MatrixD[keyTM.x][keyTM.y])))
                            return false;
                    }
                    return true;
                }
            } else
                return false;

        }
    }

    @Override
    public String toString() {
        return "Sparse Matrix";
    }

    public SparseMatrix mulSS(SparseMatrix other) {
        if (SizeN == other.SizeM) {
            SparseMatrix res = new SparseMatrix(SizeM, other.SizeN);
            VectorCord keyTM, keyOM;
            // double sum;
            for (Map.Entry entryTM : this.MatrixS.entrySet()) {
                keyTM = (VectorCord) entryTM.getKey();
                for (Map.Entry entryOM : other.MatrixS.entrySet()) {

                    keyOM = (VectorCord) entryOM.getKey();

                    if (keyTM.y == keyOM.x) {
                        VectorCord v = new VectorCord(keyTM.x, keyOM.y);

                        if (res.MatrixS.get(v) == null)
                            res.MatrixS.put(v, (double) entryTM.getValue() * (double) entryOM.getValue());

                        else
                            res.MatrixS.put(v, res.MatrixS.get(v) + (double) entryTM.getValue() * (double) entryOM.getValue());
                    }
                }
            }
            Iterator<Map.Entry<VectorCord, Double>> entries = res.MatrixS.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<VectorCord, Double> entry = entries.next();
                if (entry.getValue().equals(0.0))
                    entries.remove();
            }
            return res;
        } else {
            System.out.println("This matrix cant be multiplied");
            return null;
        }
    }

    public SparseMatrix pmulSS(SparseMatrix other) throws InterruptedException {
        if (SizeM == SizeN && other.SizeM == other.SizeN && SizeN == other.SizeM) {

            int N = SizeN;
            SparseMatrix res = new SparseMatrix(N, N);
            int k = MatrixS.size() / 11, z = 0;
            HashMap<VectorCord, Double> clone = (HashMap<VectorCord, Double>) MatrixS.clone();
            SparseMatrix m1 = new SparseMatrix();
            SparseMatrix m2 = new SparseMatrix();
            SparseMatrix m3 = new SparseMatrix();
            SparseMatrix m4 = new SparseMatrix();
            SparseMatrix m5 = new SparseMatrix();
            SparseMatrix m6 = new SparseMatrix();
            SparseMatrix m7 = new SparseMatrix();
            SparseMatrix m8 = new SparseMatrix();
            SparseMatrix m9 = new SparseMatrix();
            SparseMatrix m10 = new SparseMatrix();
            SparseMatrix m11 = new SparseMatrix();


            m1.input(k, clone);
            m2.input(k, clone);
            m3.input(k, clone);
            m4.input(k, clone);
            m5.input(k, clone);
            m6.input(k, clone);
            m7.input(k, clone);
            m8.input(k, clone);
            m9.input(k, clone);
            m10.input(k, clone);
            m11.input(k,clone);

            SparseP t1 = new SparseP(m1, other, res);
            SparseP t2 = new SparseP(m2, other, res);
            SparseP t3 = new SparseP(m3, other, res);
            SparseP t4 = new SparseP(m4, other, res);
            SparseP t5 = new SparseP(m5, other, res);
            SparseP t6 = new SparseP(m6, other, res);
            SparseP t7 = new SparseP(m7, other, res);
            SparseP t8 = new SparseP(m8, other, res);
            SparseP t9 = new SparseP(m9, other, res);
            SparseP t10 = new SparseP(m10, other, res);
            SparseP t11 = new SparseP(m11, other, res);


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



            if(MatrixS.size() % 11 != 0) {
                SparseMatrix mtmp = new SparseMatrix();
                mtmp.input(MatrixS.size() % 11, clone);
                SparseP tmp = new SparseP(mtmp, other, res);

                Thread threadtmp = new Thread(tmp);

                threadtmp.start();
                threadtmp.join();

            }


            Iterator<Map.Entry<VectorCord, Double>> entries = res.MatrixS.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<VectorCord, Double> entry = entries.next();
                if (entry.getValue().equals(0.0))
                    entries.remove();
            }

            return res;

        } else {
            System.out.println("Wrong parameters");
            return null;
        }
    }

    public void input(int a, HashMap<VectorCord, Double> clone) {
        int i = 0;
        VectorCord w;
        for (Map.Entry entry : clone.entrySet()) {
            if (i < a) {
                w = (VectorCord) entry.getKey();
                MatrixS.put(w, (double) entry.getValue());
                i++;


            }
        }

        i=0;
        Iterator<Map.Entry<VectorCord, Double>> entries = clone.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<VectorCord, Double> entry1 = entries.next();
            if (i<a) {
                entries.remove();
                i++;
            }
        }


    }

    public SparseMatrix mulSD(DenseMatrix other) {
        if (this.SizeN == other.SizeM) {
            SparseMatrix res = new SparseMatrix(this.SizeM, other.SizeN);
            //  double mult;
            for (VectorCord keyTM : this.MatrixS.keySet()) {
                for (int j = 0; j < other.SizeN; j++) {
                    VectorCord v = new VectorCord(keyTM.x, j);
                    if (res.MatrixS.get(v) == null) {
                        //    mult=(double)this.MatrixS.get(keyTM)*other.MatrixD[keyTM.y][j];

                        // if(mult != 0.0) {
                        res.MatrixS.put(v, this.MatrixS.get(keyTM) * other.MatrixD[keyTM.y][j]);
                        //  }
                    } else {
                        res.MatrixS.put(v, res.MatrixS.get(v) + this.MatrixS.get(keyTM) * other.MatrixD[keyTM.y][j]);
                    }
                }
            }
            Iterator<VectorCord> it = res.MatrixS.keySet().iterator();
            while (it.hasNext()) {
                if (res.MatrixS.get(it.next()).equals(0.0))
                    it.remove();
            }
         //   res.SparseOut();
            return res;
        } else {
            System.out.println("This matrix cant be multiplied");
            return null;
        }

    }

    public Matrix mul(Matrix other) {
        if (other instanceof SparseMatrix)
            return this.mulSS((SparseMatrix) other);

        else {
            if (other instanceof DenseMatrix)
                return this.mulSD((DenseMatrix) other);

            else return null;
        }
    }

    public Matrix pmul(Matrix other) throws InterruptedException {
        if (other instanceof SparseMatrix)
            return this.pmulSS((SparseMatrix) other);

            //   else {
            //      if (other instanceof DenseMatrix)
            //          return this.mulSD((DenseMatrix) other);

        else return null;
    }
}


//  public void transpS() {}













 /* {
        if (!(o instanceof SparseMatrix)) {
            return false;
        }
        SparseMatrix other = (SparseMatrix) o;
        if (this.MatrixS.size() == other.MatrixS.size()) {
            for (VectorCord keyM : this.MatrixS.keySet()) {

                if (other.MatrixS.get(keyM) == null) {
                   // System.out.println("NULL");
                    return false;
                }

                else if(!(this.MatrixS.get(keyM).equals(other.MatrixS.get(keyM))))  // ==???
                {
                   // System.out.println("Не совпали");
                    return false;
                }

            }
        }
        else
            return false;


        return true;
    }



   public SparseMatrix mulSS(SparseMatrix other) {
        if (SizeN == other.SizeM) {
            SparseMatrix res = new SparseMatrix(SizeM, other.SizeN);

            // double sum;
            for(VectorCord keyTM: this.MatrixS.keySet()) {
                for (VectorCord keyOM : other.MatrixS.keySet()) {
                    if(keyTM.y==keyOM.x){
                        VectorCord v = new VectorCord(keyTM.x,keyOM.y);

                        if(res.MatrixS.get(v)==null)
                            res.MatrixS.put(v,this.MatrixS.get(keyTM)*other.MatrixS.get(keyOM));

                        else
                            res.MatrixS.put(v,res.MatrixS.get(v) + this.MatrixS.get(keyTM)*other.MatrixS.get(keyOM));
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

        else {
            System.out.println("This matrix cant be multiplied");
            return null;
        }
    }











    public SparseMatrix mulSS(SparseMatrix other) {
        if (SizeN == other.SizeM) {
            SparseMatrix res = new SparseMatrix(SizeM, other.SizeN);
            VectorCord keyTM,keyOM;
            // double sum;
            for(Map.Entry entryTM : this.MatrixS.entrySet()) {
                keyTM = (VectorCord) entryTM.getKey();
                for (Map.Entry entryOM : other.MatrixS.entrySet()) {

                    keyOM = (VectorCord) entryOM.getKey();

                    if(keyTM.y==keyOM.x){
                        VectorCord v = new VectorCord(keyTM.x,keyOM.y);

                        if(res.MatrixS.get(v)==null)
                            res.MatrixS.put(v,(double)entryTM.getValue()*(double)entryOM.getValue());

                        else
                            res.MatrixS.put(v,res.MatrixS.get(v) + (double)entryTM.getValue()*(double)entryOM.getValue());
                    }
                }
            }
           Iterator<Map.Entry<VectorCord,Double>> entries = res.MatrixS.entrySet().iterator();
            while (entries.hasNext()){
                Map.Entry<VectorCord,Double> entry = entries.next();
                if(entry.getValue().equals(0.0))
                    entries.remove();
            }
            return res;
        }

        else {
            System.out.println("This matrix cant be multiplied");
            return null;
        }
    }

    */







