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
package core.lib.math.sequences;

import core.lib.math.LinearAlgebra.LinearAlgebra;
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
    Can be used for roughly the first 2.1 billion fibonacci numbers.
    Takes up a lot of ram at this point, however
     */
    public static BigInteger fibonacci_bi_optimized(int n) { //returns the nth fibonacci number. n = 5, f_n = 5
        BigInteger[][] m = LinearAlgebra.pow_bi(new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}}, 3);
        int mod = n % 3;
        int pow = 0;
        if (mod == 2) {
            pow = n / 3 + 1;
        } else {
            pow = n / 3;
        }
        BigInteger[][] d = LinearAlgebra.pow_bi_symetric(m, pow);
        switch (mod) {
            case 1:
                return d[1][1];
            case 2:
                return d[0][0];
            default:
                return d[1][0];
        }
    }

    public static void write(int n, String path) throws FileNotFoundException, IOException, InterruptedException { //writes first n fibonacci numbers
        File g = new File(path);
        g.delete();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
        BigInteger[][] w = new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        BigInteger[][] m = LinearAlgebra.pow_bi_symetric(w, 3);
        String c = ",";
        int p = 1;
        while (p <= n + 1) {
            bw.append(w[0][0] + c + w[1][0] + c + w[1][1] + c);
            p += 3;
            w = LinearAlgebra.multiply_bi_symetric(w, m);
        }
        bw.append(w[0][0] + c + w[1][0] + c + w[1][1]);

    }

    public static void write_s(int n, String path) throws FileNotFoundException, IOException, InterruptedException { //writes just the single nth fibonacci to path
        File g = new File(path);
        g.delete();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
        bw.append(fibonacci_bi_optimized(n).toString());

    }

    public static void write_bfile(int n, String path) throws IOException, InterruptedException { //writes all fibonacci numbers up to n to path, in b-file
        File g = new File(path);
        g.delete();
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
        BigInteger[][] w = new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        BigInteger[][] m = LinearAlgebra.pow_bi_symetric(w, 3);
        String c = System.getProperty("line.separator");
        int p = 0;
        while (p <= n + 1) {
            bw.append(p + " " + w[0][0] + c + (p + 1) + " " + w[1][0] + c + (p + 2) + " " + w[1][1] + c);
            p += 3;
            w = LinearAlgebra.multiply_bi_symetric(w, m);
        }
        bw.append(p + " " + w[0][0] + c + (p + 1) + " " + w[1][0] + c + (p + 2) + " " + w[1][1] + c);
    }
}
