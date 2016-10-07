package edu.spbu;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Double.parseDouble;
class VectorCord{
    int x;
    int y;


    public VectorCord(int x,int y){
       this.x = x;
        this.y = y;
    }


    public String toString(){
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


public class SMat implements Matrix{

    HashMap<VectorCord,Double> MatrixS = new HashMap<VectorCord,Double>();
    int SizeM,SizeN;

    public SMat(String adress) {
        Scanner in1 = null;
        try {
            in1 = new Scanner(new File(adress));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;

        while (in1.hasNextLine()) {

            String[] s = in1.nextLine().split(" ");
            SizeN=s.length;
            for (int j = 0; j < s.length; j++) {
                if (parseDouble(s[j]) != 0.0) {
                    VectorCord v = new VectorCord(i,j);
                    this.MatrixS.put(v, parseDouble(s[j]));

                }
            }
            i++;
        }


        SizeM=i;
       // SizeN=in1.nextLine().length();

    in1.close();


    }

    public SMat(int x,int y){
        SizeM = x;
        SizeN = y;
    }

    public void SparseOut() {
        for (int i = 0; i < SizeM; i++) {
            for (int j = 0; j < SizeN; j++) {
                VectorCord v = new VectorCord(i,j);
                if (this.MatrixS.get(v) != null)
                    System.out.print(this.MatrixS.get(v) + " ");

                else System.out.print(0.0 + " ");

            }
            System.out.println();
        }

    }

    public boolean equals (Object o) {
        if (!(o instanceof SMat)) {
            return false;
        }
        SMat other = (SMat)o;
        boolean flag=true;
        int count=0;
        if (this.MatrixS.size()==other.MatrixS.size()) {
           for(VectorCord keyM : this.MatrixS.keySet()) {
               for (VectorCord keyO : other.MatrixS.keySet()) {
                   if(keyM==keyO && this.MatrixS.get(keyM)==other.MatrixS.get(keyO))
                       count++;
               }
           }

           if(count==this.MatrixS.size())
               flag=true;

           else flag=false;

        }


        else
        {
            System.out.println("This matrix cant be equal");
            flag=false;
        }
        return  flag;
    }

    @Override
    public String toString() {
        return "Sparse Matrix";
    }

    public SMat mulSS(SMat other){
        if(SizeN==other.SizeM){
            SMat res = new SMat(SizeM,other.SizeN);

            return null;

        }

        else {
            System.out.println("This matrix cant be multipled");
            return null;
        }
    }

    public SMat mulSD(DMat other){
        return null;
    }

    public Matrix mul(Matrix other){
        if (other instanceof SMat)
            return this.mulSS( (SMat) other);

        else {
            if (other instanceof DMat)
                return this.mulSD((DMat) other);

            else return null;
        }
    }







}
