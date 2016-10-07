package edu.spbu.test;
import com.sun.org.apache.xpath.internal.operations.Equals;
import edu.spbu.DMat;
import edu.spbu.SMat;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMat {
    @Test
    public void equalsDMatTest(){
        DMat  a = new DMat("in1.txt");
        assertEquals(a,a);
    }

    @Test
    public void equalsSMat(){
        SMat a = new SMat("Sparse1.txt");
        assertEquals(a,a);

    }

    @Test
    public void equalsSmat2(){
        SMat a = new SMat("Sparse1.txt");
        SMat b = new SMat("Sparse2.txt");
        assertTrue(!(a.equals(b)));
    }

    @Test
    public void mulDDTest(){
        DMat a = new DMat("in1.txt");
        DMat b =  new DMat("in2.txt");
        DMat res =  new DMat("testmul.txt");
        assertEquals(res, a.mul(b));
    }
}
