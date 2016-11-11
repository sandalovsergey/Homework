package test.java.edu.spbu.matrix;

import main.java.edu.spbu.matrix.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void mulDD() {
        Matrix m1 = new DenseMatrix("m11.txt");
        Matrix m2 = new DenseMatrix("m22.txt");
        Matrix expected = new DenseMatrix("r33.txt");
        assertEquals(expected, m1.mul(m2));
    }

    @Test
    public void mulSS() {
        Matrix m1 = new SparseMatrix("m11.txt");
        Matrix m2 = new SparseMatrix("m22.txt");
        Matrix expected = new SparseMatrix("r33.txt");
        assertEquals(expected, m1.mul(m2));
    }

    @Test
    public void mulSD() {
        Matrix m1 = new SparseMatrix("m11.txt");
        Matrix m2 = new DenseMatrix("m22.txt");
        Matrix expected = new SparseMatrix("r33.txt");
        assertEquals(expected, m1.mul(m2));
    }

    @Test
    public void mulDS() {
        Matrix m1 = new DenseMatrix("m11.txt");
        Matrix m2 = new SparseMatrix("m22.txt");
        Matrix expected = new SparseMatrix("r33.txt");
        assertEquals(expected, m1.mul(m2));
    }

    @Test
    public void pmulDD() throws InterruptedException {
        Matrix m1 = new DenseMatrix("m11.txt");
        Matrix m2 = new DenseMatrix("m22.txt");
        Matrix expected = new DenseMatrix("r33.txt");
        assertEquals(expected, m1.pmul(m2));
    }

    @Test
    public void pmulSS() throws InterruptedException {
        Matrix m1 = new SparseMatrix("m11.txt");
        Matrix m2 = new SparseMatrix("m22.txt");
        Matrix expected = new SparseMatrix("r33.txt");
        assertEquals(expected, m1.pmul(m2));
    }











/*    @Test
    public void equalsDMat1(){
        DenseMatrix a = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test1\\in1.txt");
        assertEquals(a,a);
    }

    @Test
    public void equalsDMat2(){
        DenseMatrix a = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test2\\d1.txt");
        DenseMatrix b = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test2\\d2.txt");
        assertEquals(a,b);
    }

    @Test
    public void equalsDMat3(){
        DenseMatrix a = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test3\\d1.txt");
        DenseMatrix b = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test3\\d2.txt");
        assertFalse(a.equals(b));
    }

    @Test
    public void mulDDTest1(){
        Matrix a = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test4\\d1.txt");
        Matrix b =  new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test4\\d2.txt");
        Matrix res =  new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test4\\d3.txt");
        assertEquals(res, a.mul(b));
    }

    @Test
    public void equalsSMat1(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test1\\s1.txt");
        assertEquals(a,a);

    }

    @Test
    public void equalsSMat2(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test2\\s1.txt");
        SparseMatrix b = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test2\\s2.txt");
        assertEquals(a,b);
    }

    @Test
    public void equalsSMat3(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test3\\s1.txt");
        SparseMatrix b = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test3\\s2.txt");
        assertFalse(a.equals(b));
    }

    @Test
    public void mulSSTest1(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test4\\s1.txt");
        SparseMatrix b = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test4\\s2.txt");
        SparseMatrix res = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test4\\s3.txt");
        assertEquals(res,a.mul(b));
    }

    @Test
    public void mulSSTest2(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test5\\s1.txt");
        SparseMatrix b = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test5\\s2.txt");
        SparseMatrix res = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test5\\s3.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void mulSDTest3(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\s1.txt");
        SparseMatrix b = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\d1.txt");
        SparseMatrix res = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\res.txt");
        assertEquals(res,a.mul(b));


    }

/*    @Test
    public void test(){
        SparseMatrix a = new SparseMatrix("1.txt");
        SparseMatrix b = new SparseMatrix("2.txt");
        SparseMatrix res= new SparseMatrix("3.txt");
        assertTrue(res.equals(a.mul(b)));
    } //??? */

/*    @Test
    public void mulSDTest1(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\s1.txt");
        DenseMatrix b = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\d1.txt");
        SparseMatrix res = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\res.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void mulSDTest2(){
        SparseMatrix a = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test2\\s1.txt");
        DenseMatrix b = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test2\\d1.txt");
        SparseMatrix res = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test2\\res.txt");
        assertEquals(res,a.mul(b));


    }

 /*   @Test
    public void mulSDtest0(){
        SparseMatrix a = new SparseMatrix("1.txt");
        DenseMatrix b = new DenseMatrix("2.txt");
        SparseMatrix res = new SparseMatrix("3.txt");
        assertEquals(res,a.mul(b));
    }*/

/*    @Test
    public void mulDSTest1(){
        DenseMatrix a = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test1\\d1.txt");
        SparseMatrix b = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test1\\s1.txt");
        SparseMatrix res = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test1\\res.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void mulDSTest2(){
        DenseMatrix a = new DenseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test2\\d1.txt");
        SparseMatrix b = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test2\\s1.txt");
        SparseMatrix res = new SparseMatrix("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test2\\res.txt");
        assertEquals(res,a.mul(b));
    }

 /*   @Test
    public void mulDSTest3(){
        DenseMatrix a = new DenseMatrix("1.txt");
        SparseMatrix b = new SparseMatrix("2.txt");
        SparseMatrix res = new SparseMatrix("3.txt");
        assertEquals(res,a.mul(b));
    } */

 /*   @Test
    public void GlobalMul(){
        SparseMatrix a = new SparseMatrix("m11.txt");
        SparseMatrix b = new SparseMatrix("m22.txt");

        DenseMatrix c = new DenseMatrix("m11.txt");
        DenseMatrix d = new DenseMatrix("m22.txt");

        SparseMatrix res1 = new SparseMatrix("r33.txt");
        DenseMatrix res2 = new DenseMatrix("r33.txt");

        assertEquals(res1,a.mul(b)); //S*S
        assertEquals(res1,a.mul(d)); //S*D
        assertEquals(res1,c.mul(b)); //D*S
        assertEquals(res2,c.mul(d)); //D*D
    } */



}
