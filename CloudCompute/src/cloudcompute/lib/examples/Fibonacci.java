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

import cloudcompute.CloudCompute;
import cloudcompute.lib.InputOutput.DiskSwap;
import cloudcompute.lib.math.LinearAlgebra.Matrix;
import cloudcompute.lib.InputOutput.StringWriter;
import cloudcompute.lib.math.DataStructure.DiskSwapBigInt;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

/**
 *
 * @author brown
 */
public class Fibonacci {

    /*
    Generating Fibonacci numbers that can fit into an int
     */
    public static int fibonacci(int n) {
        int[][] m = Matrix.pow_i(new int[][]{{0, 1}, {1, 1}}, 3);
        int mod = n % 3;
        int pow = 0;
        if (mod == 2) {
            pow = n / 3 - 1;
        } else {
            pow = n / 3 - 1;
        }
        int[][] d = Matrix.pow_i(m, pow);
        if (mod != 0) {
            return d[1][1];
        } else {
            return d[1][0];
        }
    }

    /*
    Can be used for roughly the first 2.1 billion fibonacci numbers.
     */
    public static BigInteger fibonacci_bi_optimized(int n) {
        BigInteger[][] m = Matrix.pow_bi(new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}}, 3);
        int mod = n % 3;
        int pow = 0;
        if (mod == 2) {
            pow = n / 3 + 1;
        } else {
            pow = n / 3;
        }
        BigInteger[][] d = Matrix.pow_bi_symetric(m, pow);
        switch (mod) {
            case 1:
                return d[1][1];
            case 2:
                return d[0][0];
            default:
                return d[1][0];
        }
    }

    public static void write(int n, String path) throws FileNotFoundException, IOException, InterruptedException {
        File g = new File(path);
        g.delete();
        StringWriter sw = new StringWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path))));
        BigInteger[][] w = new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        BigInteger[][] m = Matrix.pow_bi_symetric(w, 3);
        String c = ",";
        int p = 1;
        while (p <= n + 1) {
            sw.add(w[0][0] + c + w[1][0] + c + w[1][1] + c);
            p += 3;
            w = Matrix.multiply_bi_symetric(w, m);
        }
        sw.add(w[0][0] + c + w[1][0] + c + w[1][1]);
        sw.join();
        sw.start();

    }

    public static void write_s(int n, String path) throws FileNotFoundException, IOException, InterruptedException {
        File g = new File(path);
        g.delete();
        StringWriter sw = new StringWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path))));
        sw.add(fibonacci_bi_optimized(n).toString());
        sw.join();
        sw.start();

    }

    public static void write_bfile(int n, String path) throws IOException, InterruptedException {
        File g = new File(path);
        g.delete();
        StringWriter sw = new StringWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path))));
        BigInteger[][] w = new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        BigInteger[][] m = Matrix.pow_bi_symetric(w, 3);
        String c = System.getProperty("line.separator");
        int p = 0;
        while (p <= n + 1) {
            sw.add(p + " " + w[0][0] + c + (p + 1) + " " + w[1][0] + c + (p + 2) + " " + w[1][1] + c);
            p += 3;
            w = Matrix.multiply_bi_symetric(w, m);
        }
        sw.add(p + " " + w[0][0] + c + (p + 1) + " " + w[1][0] + c + (p + 2) + " " + w[1][1] + c);

        sw.join();
        sw.start();

    }

    public static void fib_diskswap(int n) throws IOException, InterruptedException {
        String a_path = CloudCompute.disk_swap + "a.txt";
        String b_path = CloudCompute.disk_swap + "b.txt";
        String c_path = CloudCompute.disk_swap + "c.txt";
        DiskSwapBigInt a = new DiskSwapBigInt(a_path, (int) (.7 * n));
        DiskSwapBigInt b = new DiskSwapBigInt(b_path, (int) (.7 * n));
        DiskSwapBigInt c = new DiskSwapBigInt(c_path, (int) (.7 * n));
        a.set(0);
        b.set(1);
        c.set(1);

        a = DiskSwapBigInt.add(b, c, a_path);
        //a = new DiskSwapBigInt(a_path, (int) (.7 * n));

        b = DiskSwapBigInt.add(a, c, b_path);

    }

    //retired .getList() because it runs out of memory, you need to write them out as you calculate, then forget them.
}
