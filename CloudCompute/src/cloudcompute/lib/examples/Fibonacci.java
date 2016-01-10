/* 
 * Copyright (C) ChemicalDevelopment 2016
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cloudcompute.lib.examples;

import cloudcompute.lib.math.LinearAlgebra.Matrix;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author brown
 */
public class Fibonacci {
    
    public static int fibonacci(int n) {
        return (int)Matrix.pow(new double[][]{{0, 1}, {1, 1}}, n - 1)[1][1];
    }
    
    public static BigInteger fibonacci_bi(int n) {
        return (Matrix.pow_bi(new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}}, n - 1))[1][1];
    }
    
    public static List<Integer> fibonacci_list(int start, int end) {
        List<Integer> f = new ArrayList<>();
        int[][] m = Matrix.pow_i(new int[][]{{0, 1}, {1, 1}}, 3);
        int[][] c = Matrix.pow_i(m, start / 3);
        for (int i = 0; i <= (end - start) / 3; i++) {
            f.add(c[0][0]);
            f.add(c[1][0]);
            f.add(c[1][1]);
            c = Matrix.multiply_i(c, m);
        }
        return f;
    }
    
    public static List<BigInteger> fibonacci_list_bi(int start, int end) {
        List<BigInteger> f = new ArrayList<>();
        BigInteger[][] m = Matrix.pow_bi(new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}}, 3);
        int h = start / 3;
        if (start == 1) {
            h++;
        }
        BigInteger[][] c = Matrix.pow_bi(m, h);
        for (int i = start / 3; i <= end / 3 + 1; i++) {
            f.add(c[0][0]);
            f.add(c[1][0]);
            f.add(c[1][1]);
            c = Matrix.multiply_bi(c, m);
        }
        Set<BigInteger> hs = new HashSet<>();
        hs.addAll(f);
        f.clear();
        f.addAll(hs);
        Collections.sort(f);

        return f;
    }
}