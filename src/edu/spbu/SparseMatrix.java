package edu.spbu;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

        SizeN=in.nextLine().split(" ").length;
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

        if(o instanceof SparseMatrix) {
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
        }

        else {
            if(o instanceof DenseMatrix)
            {
                DenseMatrix other = (DenseMatrix) o;
                if(this.SizeN!=other.SizeM)
                    return false;

                else{
                    VectorCord keyTM;
                    for (Map.Entry entryTM: this.MatrixS.entrySet()) {
                        keyTM = (VectorCord) entryTM.getKey();
                        if (!(this.MatrixS.get(keyTM).equals(other.MatrixD[keyTM.x][keyTM.y])))
                            return false;
                    }
                    return true;
                }
            }

            else
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
            VectorCord keyTM,keyOM;
            // double sum;
            for(Map.Entry entryTM : this.MatrixS.entrySet()) {
                for (Map.Entry entryOM : other.MatrixS.entrySet()) {
                    keyTM = (VectorCord) entryTM.getKey();
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

            //   res.SparseOut();
            return res;
        }

        else {
            System.out.println("This matrix cant be multiplied");
            return null;
        }
    }

    public SparseMatrix mulSD(DenseMatrix other) {
        if(this.SizeN==other.SizeM){
        SparseMatrix res = new SparseMatrix(this.SizeM,other.SizeN);
      //  double mult;
            for(VectorCord keyTM: this.MatrixS.keySet()){
                for(int j=0;j<other.SizeN;j++){
                    VectorCord v = new VectorCord(keyTM.x,j);
                    if(res.MatrixS.get(v)==null){
                    //    mult=(double)this.MatrixS.get(keyTM)*other.MatrixD[keyTM.y][j];

                       // if(mult != 0.0) {
                            res.MatrixS.put(v, this.MatrixS.get(keyTM) * other.MatrixD[keyTM.y][j]);
                      //  }
                    }

                    else{
                        res.MatrixS.put(v,res.MatrixS.get(v) + this.MatrixS.get(keyTM)*other.MatrixD[keyTM.y][j]);
                    }
                }
            }
            Iterator<VectorCord> it = res.MatrixS.keySet().iterator();
            while (it.hasNext()){
                if(res.MatrixS.get(it.next()).equals(0.0))
                    it.remove();
            }
            res.SparseOut();
            return res;
        }

        else{
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

  //  public void transpS() {}





    }







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

    */







