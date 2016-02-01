/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OpenCLCore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author director
 */
public class UnitTest {

    public UnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of pow method, of class Matrix.
     */
    @Test
    public void testPow() throws Exception {
        System.out.println("pow");
        float[][] A = null;
        int p = 0;
        float[][] expResult = null;
        float[][] result = Matrix.pow(A, p);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mul method, of class Matrix.
     */
    @Test
    public void testMul() throws Exception {
        Lib.init();
        System.out.println("mul");
        float[][] A = new float[2000][2000];
        float[][] B = new float[2000][2000];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = 2 / (i + 1) + j * i / 3;
                B[i][j] = i - j + j * i / ((j + 1) * (i + 1));
            }
        }
        long start = System.nanoTime();
        float[][] result = Matrix.mul(A, B);
        long end = System.nanoTime();
        System.out.println((end - start) / (Math.pow(10, 9)));

        /*for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "   ");
            }
            System.out.print("\n");
        }*/
    }

    /**
     * Test of vectorScalar method, of class Matrix.
     */
    @Test
    public void testVectorScalar() throws Exception {
        System.out.println("vectorScalar");
        float[] v = null;
        float[] s = null;
        float expResult = 0.0F;
        float result = Matrix.vectorScalar(v, s);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
