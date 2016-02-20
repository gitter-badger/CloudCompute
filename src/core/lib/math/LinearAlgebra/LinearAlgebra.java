/* 
 * Copyright (C) 2016 ChemicalDevelopment
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package core.lib.math.LinearAlgebra;

import java.math.BigInteger;

/**
 *
 * @author Cade Brown
 */
public class LinearAlgebra {

    //identity matrix
    public static double[][] identity(int n) {
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++)
            I[i][i] = 1;
        return I;
    }

    //dot product
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal dimensions");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    //transpose and return A
    public static double[][] transpose(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[j][i] = A[i][j];
        return C;
    }

    // return the addition of A and B
    public static double[][] add(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // return subtraction
    public static double[][] subtract(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    // the full matrix multiplication
    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal dimensions.");
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }
    
    //same as repeated multiplication
    public static double[][] pow(double[][] A, int p) {
        if (p == 1) {
            return A;
        }
        if (p == 2) {
            return multiply(A, A);
        }
        if (p % 2 == 0) {
            return pow((pow(A, 2)), p / 2);
        } else {
            return multiply(A, pow(pow(A, 2), (p - 1)/2));
        }
    }
    
        // the full matrix multiplication
    public static int[][] multiply_i(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal dimensions.");
        int[][] C = new int[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }
    
    //same as repeated multiplication
    public static int[][] pow_i(int[][] A, int p) {
        if (p == 1) {
            return A;
        }
        if (p == 2) {
            return multiply_i(A, A);
        }
        if (p % 2 == 0) {
            return pow_i((pow_i(A, 2)), p / 2);
        } else {
            return multiply_i(A, pow_i(pow_i(A, 2), (p - 1)/2));
        }
    }
    
        // the full matrix multiplication
    public static BigInteger[][] multiply_bi(BigInteger[][] A, BigInteger[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal dimensions.");
        BigInteger[][] C = new BigInteger[mA][nB];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                C[i][j] = BigInteger.ZERO;
            }
        }
        
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] = C[i][j].add(A[i][k].multiply(B[k][j]));
        return C;
    }
    
    //same as repeated multiplication
    public static BigInteger[][] pow_bi(BigInteger[][] A, int p) {
        if (p == 1) {
            return A;
        }
        if (p == 2) {
            return multiply_bi(A, A);
        }
        if (p % 2 == 0) {
            return pow_bi((pow_bi(A, 2)), p / 2);
        } else {
            return multiply_bi(A, pow_bi(pow_bi(A, 2), (p - 1)/2));
        }
    }
    
    /*
    
    
    ONLY USE THE FOLLOWING TWO METHODS FOR FIBONACCI MATRICIES
    asdflkajhsdf
    
    
    
    
    
    
    
    look at thisdfjaodsfhpasudhgip;as
    HOWENOEFWNOWFENONWEOFNOFEW
    
    
    
    
    
    */
    /*
    Used mainly for fibonacci matrix, but can be used for any 2x2 symetric matrices
    */
    public static BigInteger[][] multiply_bi_symetric(BigInteger[][] A, BigInteger[][] B) {
        int nA = A[0].length;
        int mB = B.length;
        if (nA != mB || nA != 2) throw new RuntimeException("Illegal dimensions.");
        BigInteger[][] C = new BigInteger[2][2];
        BigInteger a = A[0][0];
        BigInteger b = A[1][0];
        BigInteger e = B[0][0];
        BigInteger f = B[1][0];
        BigInteger h = B[1][1];
        C[0][0] = a.multiply(e).add(b.multiply(f));
        C[1][0] = a.multiply(f).add(b.multiply(h));
        C[0][1] = C[1][0];
        C[1][1] = C[0][0].add(C[0][1]);
        
        return C;
    }
    
    //same as repeated multiplication, but for symetric matricies
    public static BigInteger[][] pow_bi_symetric(BigInteger[][] A, int p) {
        if (p == 1) {
            return A;
        }
        if (p == 2) {
            return multiply_bi_symetric(A, A);
        }
        if (p % 2 == 0) {
            return pow_bi_symetric((pow_bi_symetric(A, 2)), p / 2);
        } else {
            return multiply_bi_symetric(A, pow_bi_symetric(pow_bi_symetric(A, 2), (p - 1)/2));
        }
    }
    
    //write out a string
    public static String write(double[][] A) {
        String r = "";
        for (int i = 0; i < A.length; i++) {
            r += "[";
            for (int j = 0; j < A[0].length; j++) {
                r += A[i][j] + "  ";
            }
            r += "]\n";
        }
        return r;
    }
    
     public static String write_i(int[][] A) {
        String r = "";
        for (int i = 0; i < A.length; i++) {
            r += "[";
            for (int j = 0; j < A[0].length; j++) {
                r += A[i][j] + "  ";
            }
            r += "]\n";
        }
        return r;
     }
}
