/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.math.sequences;

import cloudcompute.lib.math.Sieves.Eratosthenes;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author director
 */
public class PrimorialResidue {

    public static List<Integer> find(int max) {
        List<Integer> primes = Eratosthenes.primes(max / (2 * 3 * 5 * 7 * 11) + 100);
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            all.add(i);
        }
        List<Integer> primorials = new ArrayList<>();
        for (int v : primes) {
            primorials.add(mul(primes.subList(0, primes.indexOf(v))));
            if (primorials.get(primes.indexOf(v)) > max) {
                break;
            }
        }
        System.out.println(primorials);
        List<Integer> res = new ArrayList<>();
        for (int t : all) {
            int count = 0;
            int r = t;
            while (r != 0) {
                int curmod = 1;
                for (int a : primorials) {
                    if (a <= r && a > curmod) {
                        curmod = a;
                    }
                }
                r = r % curmod;

                count++;
            }
            res.add(count);
        }
        return res;

    }

    public static List<Integer> find_bi(int max) {
        List<Integer> primes = Eratosthenes.primes(max);
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            all.add(i);
        }
        List<BigInteger> primorials = new ArrayList<>();
        for (int v : primes) {
            primorials.add(mul_bi(primes.subList(0, primes.indexOf(v))));
        }
        List<Integer> res = new ArrayList<>();
        for (int t : all) {
            int count = 0;
            int r = t;
            while (r != 0) {
                BigInteger curmod = BigInteger.ONE;
                for (BigInteger a : primorials) {
                    if (a.compareTo(BigInteger.valueOf(r)) <= 0) {
                        curmod = a;
                    }
                }
                r = BigInteger.valueOf(r).mod(curmod).intValue();
                count++;
            }
            res.add(count);
        }
        return res;

    }

    public static int mul(List<Integer> d) {
        int a = 1;
        for (int v : d) {
            a *= v;
        }
        return a;
    }

    public static BigInteger mul_bi(List<Integer> d) {
        BigInteger a = BigInteger.ONE;
        for (int v : d) {
            a = a.multiply(BigInteger.valueOf(v));
        }
        return a;
    }

}
