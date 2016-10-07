package edu.spbu;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Double.parseDouble;
public class Mat {
    double[][] Matrix111;



    public Mat(String adress) {
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


        Matrix111 = new double[M][N];
        for (int i = 0; i < M; i++) {
            String[] s = matrix.get(i).split(" ");
            for (int j = 0; j < N; j++) {
                this.Matrix111[i][j] = parseDouble(s[j]);
            }
            // s=null;
        }

    }
    @Override
    public String toString() {
        return "matrix";
    }

    public void Matout() {
        for (int i = 0; i < Matrix111.length; i++) {
            for (int j = 0; j < Matrix111[0].length; j++) {
                System.out.print(Matrix111[i][j] + " ");
            }
            System.out.println();
        }
    }


    public Mat(int x, int y) {
        Matrix111 = new double[x][y];
    }


    public Mat mulMatMat(Mat other) {
        if (Matrix111[0].length == other.Matrix111.length) {
            Mat res = new Mat(Matrix111.length, other.Matrix111[0].length);
                for (int i = 0; i < Matrix111.length; i++) {
                    for (int j = 0; j < other.Matrix111[0].length; j++) {
                        res.Matrix111[i][j] = 0.0;
                        for (int k = 0; k < Matrix111[0].length; k++) {
                            res.Matrix111[i][j] = res.Matrix111[i][j] + this.Matrix111[i][k] * other.Matrix111[k][j];

                        }
                    }

                }

            res.Matout();
            return res;
            }

        else{
            System.out.println("This matrix cant be multiplied");
            return null;
        }
    }

   public void addMat(Mat other){
        try {
            File f = new File("outadd.txt");
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);


            if (Matrix111.length == other.Matrix111.length && Matrix111[0].length== other.Matrix111[0].length) {
                Mat res = new Mat(Matrix111.length, Matrix111[0].length);
                for (int i = 0; i < Matrix111.length; i++) {
                    for (int j = 0; j < Matrix111[0].length; j++) {
                        res.Matrix111[i][j] = this.Matrix111[i][j] + other.Matrix111[i][j];
                        bw.write(res.Matrix111[i][j] + " ");
                    }
                    bw.append("\n");
                }



                //System.out.println("Result of multiplication");
                res.Matout();
            }
            else{
                bw.write("This matrix cant be added");
                System.out.println("This matrix cant be added");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean equals (Object o) {
        if (!(o instanceof Mat)) {
            return false;
        }
        Mat other = (Mat)o;
        boolean flag=true;
        if (Matrix111.length == other.Matrix111.length && Matrix111[0].length== other.Matrix111[0].length) {
            for(int i = 0; i< Matrix111.length; i++)
                for(int j = 0; j< Matrix111[0].length; j++) {
                    if (Matrix111[i][j] != other.Matrix111[i][j])
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

/*class VectorCord {
    int x;
    int y;

    public VectorCord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        if (!(o instanceof VectorCord)) {
            return false;
        }
        VectorCord other = (VectorCord) o;
        boolean flag = true;

        if (this.x == other.x && this.y == other.y) flag = true;
        else flag = false;

        return flag;

    }
}*/
