package edu.spbu;
import com.sun.org.apache.xpath.internal.operations.Equals;
import edu.spbu.DMat;
import edu.spbu.SMat;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMat {
    @Test
    public void equalsDMat1(){
        DMat  a = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test1\\in1.txt");
        assertEquals(a,a);
    }

    @Test
    public void equalsDMat2(){
        DMat a = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test2\\d1.txt");
        DMat b = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test2\\d2.txt");
        assertEquals(a,b);
    }

    @Test
    public void equalsDMat3(){
        DMat a = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test3\\d1.txt");
        DMat b = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test3\\d2.txt");
        assertFalse(a.equals(b));
    }

    @Test
    public void mulDDTest1(){
        DMat a = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test4\\d1.txt");
        DMat b =  new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test4\\d2.txt");
        DMat res =  new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDD\\test4\\d3.txt");
        assertEquals(res, a.mul(b));
    }

    @Test
    public void equalsSMat1(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test1\\s1.txt");
        assertEquals(a,a);

    }

    @Test
    public void equalsSMat2(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test2\\s1.txt");
        SMat b = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test2\\s2.txt");
        assertEquals(a,b);
    }

    @Test
    public void equalsSMat3(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test3\\s1.txt");
        SMat b = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test3\\s2.txt");
        assertFalse(a.equals(b));
    }

    @Test
    public void mulSSTest1(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test4\\s1.txt");
        SMat b = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test4\\s2.txt");
        SMat res = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test4\\s3.txt");
        assertEquals(res,a.mul(b));
    }

    @Test
    public void mulSSTest2(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test5\\s1.txt");
        SMat b = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test5\\s2.txt");
        SMat res = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSS\\test5\\s3.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void mulSDTest3(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\s1.txt");
        SMat b = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\d1.txt");
        SMat res = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\res.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void test(){
        SMat a = new SMat("1.txt");
        SMat b = new SMat("2.txt");
        SMat res= new SMat("3.txt");
        assertTrue(res.equals(a.mul(b)));
    } //???

    @Test
    public void mulSDTest1(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\s1.txt");
        DMat b = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\d1.txt");
        SMat res = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test1\\res.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void mulSDTest2(){
        SMat a = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test2\\s1.txt");
        DMat b = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test2\\d1.txt");
        SMat res = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testSD\\test2\\res.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void mulSDtest0(){
        SMat a = new SMat("1.txt");
        DMat b = new DMat("2.txt");
        SMat res = new SMat("3.txt");
        assertEquals(res,a.mul(b));
    }

    @Test
    public void mulDSTest1(){
        DMat a = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test1\\d1.txt");
        SMat b = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test1\\s1.txt");
        SMat res = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test1\\res.txt");
        assertEquals(res,a.mul(b));


    }

    @Test
    public void mulDSTest2(){
        DMat a = new DMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test2\\d1.txt");
        SMat b = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test2\\s1.txt");
        SMat res = new SMat("C:\\Users\\Sergey Sandalov\\IdeaProjects\\Homework2\\testDS\\test2\\res.txt");
        assertEquals(res,a.mul(b));
    }

    @Test
    public void mulDSTest3(){
        DMat a = new DMat("1.txt");
        SMat b = new SMat("2.txt");
        SMat res = new SMat("3.txt");
        assertEquals(res,a.mul(b));
    }

    @Test
    public void GlobalMul(){
        SMat a = new SMat("m11.txt");
        SMat b = new SMat("m22.txt");

        DMat c = new DMat("m11.txt");
        DMat d = new DMat("m22.txt");

        SMat res1 = new SMat("r33.txt");
        DMat res2 = new DMat("r33.txt");

        assertEquals(res1,a.mul(b)); //S*S
        assertEquals(res1,a.mul(d)); //S*D
        assertEquals(res1,c.mul(b)); //D*S
        assertEquals(res2,c.mul(d)); //D*D
    }




}
