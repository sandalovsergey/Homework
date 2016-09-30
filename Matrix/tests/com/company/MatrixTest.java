package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by artemaliev on 30/09/16.
 */
public class MatrixTest
{

  @Test
  public void mulSDTest(){
    Matrix a = new SMat("file1");
    Matrix b =  new DMat("file2");
    Matrix res =  new DMat("result2");
    assertEquals(res, a.mul(b));
  }

  @Test
  public void mulDDTest(){
    Matrix a = new DMat("file1");
    Matrix b =  new DMat("file2");
    Matrix res =  new DMat("result2");
    assertEquals(res, a.mul(b));
  }
  @Test
  public void mulDSTest(){
    Matrix a = new DMat("file1");
    Matrix b =  new SMat("file2");
    Matrix res =  new DMat("result2");
    assertEquals(res, a.mul(b));
  }
  @Test
  public void mulSSTest(){
    Matrix a = new SMat("file1");
    Matrix b =  new SMat("file2");
    Matrix res =  new DMat("result2");
    assertEquals(res, a.mul(b));
  }
}
