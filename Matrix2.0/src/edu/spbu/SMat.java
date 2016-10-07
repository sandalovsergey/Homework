package edu.spbu;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Double.parseDouble;
public class SMat implements Matrix{

    HashMap<String,Double> MatrixS = new HashMap<String,Double>();
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
                    String str = Integer.toString(i) + "," + Integer.toString(j);
                    this.MatrixS.put(str, parseDouble(s[j]));

                }
            }
            i++;
        }


        SizeM=i;
       // SizeN=in1.nextLine().length();

    in1.close();


    }

    public void SparseOut() {
        for (int i = 0; i < SizeM; i++) {
            for (int j = 0; j < SizeN; j++) {
                String str = Integer.toString(i) + "," + Integer.toString(j);
                if (this.MatrixS.get(str) != null)
                    System.out.print(this.MatrixS.get(str) + " ");

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
           for(String keyM : this.MatrixS.keySet()) {
               for (String keyO : other.MatrixS.keySet()) {
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
        return null;
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
