package edu.spbu;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Double.parseDouble;

public class DMat implements Matrix{
    double[][] MatrixD;

    public DMat(int x, int y) {
        MatrixD = new double[x][y];
    }

    public DMat(String adress) {
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

    public void Matout() {
        for (int i = 0; i < MatrixD.length; i++) {
            for (int j = 0; j < MatrixD[0].length; j++) {
                System.out.print(MatrixD[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Matrix mul(Matrix other){
        if (other instanceof DMat)
            return this.mulDD( (DMat) other);

        else return null;
    }

    public DMat mulDD(DMat other){
        if (MatrixD[0].length == other.MatrixD.length) {
            DMat res = new DMat(MatrixD.length, other.MatrixD[0].length);
            for (int i = 0; i < MatrixD.length; i++) {
                for (int j = 0; j < other.MatrixD[0].length; j++) {
                    res.MatrixD[i][j] = 0.0;
                    for (int k = 0; k < MatrixD[0].length; k++) {
                        res.MatrixD[i][j] = res.MatrixD[i][j] + this.MatrixD[i][k] * other.MatrixD[k][j];

                    }
                }

            }

           // res.Matout();
            return res;
        }

        else{
            System.out.println("This matrix cant be multiplied");
            return null;
        }
    }

    public SMat mulDS(SMat other){
        return null;
    }

    public boolean equals (Object o) {
        if (!(o instanceof DMat)) {
            return false;
        }
        DMat other = (DMat)o;
        boolean flag=true;
        if (MatrixD.length == other.MatrixD.length && MatrixD[0].length== other.MatrixD[0].length) {
            for(int i = 0; i< MatrixD.length; i++)
                for(int j = 0; j< MatrixD[0].length; j++) {
                    if (MatrixD[i][j] != other.MatrixD[i][j])
                        flag = false;
                }
        }

        else
        {
            System.out.println("This matrix cant be equal");
            flag=false;
        }
        return  flag;
    }
}
