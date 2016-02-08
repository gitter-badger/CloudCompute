/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.lib;

import core.lib.math.Sieves.Eratosthenes;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;

/**
 *
 * @author director
 */
public class BigRational {

    public BigInteger numerator;
    public BigInteger denominator;
    public MathContext m;

    public BigRational(BigInteger n, BigInteger d, int dig) {
        numerator = n;
        denominator = d;
        m = new MathContext(dig);
    }

    public void negate() {
        numerator.negate();
    }

    public void add(BigInteger a) {
        numerator = numerator.add(denominator.multiply(a));
    }

    public void add(BigRational q) {
        numerator = numerator.multiply(q.denominator);
        numerator = numerator.add(denominator.multiply(q.numerator));
        denominator = q.denominator.multiply(denominator);

    }

    public BigRational add_r(BigInteger a) {
        BigRational result = this;
        result.add(a);
        return result;
    }

    public BigRational add_r(BigRational a) {
        BigRational result = this;
        result.add(a);
        return result;
    }

    public void mul(BigInteger m) {
        numerator = numerator.multiply(m);
    }

    public void mul(BigRational m) {
        numerator = numerator.multiply(m.numerator);
        denominator = denominator.multiply(m.denominator);
    }

    public BigRational mul_r(BigInteger m) {
        BigRational r = this;
        r.mul(m);
        return r;
    }

    public BigRational mul_r(BigRational m) {
        BigRational r = this;
        r.mul(m);
        return r;
    }

    public void div(BigInteger d) {
        denominator = denominator.multiply(d);
    }

    public void div(BigRational d) {
        numerator = numerator.multiply(d.denominator);
        denominator = denominator.multiply(d.numerator);
    }

    public BigRational div_r(BigInteger d) {
        BigRational result = this;
        result.div(d);
        return result;
    }

    public BigRational div_r(BigRational d) {
        BigRational result = this;
        result.div(d);
        return result;
    }

    public BigDecimal getDecimal() {
        System.out.println("Numerator: " + numerator + " Denominator: " + denominator);
        return (new BigDecimal(numerator)).divide(new BigDecimal(denominator), m);
    }

    public void factor() {
        List<Integer> primes = Eratosthenes.primes(1000);
        for (int i : primes) {
            while (numerator.mod(BigInteger.valueOf(i)).signum() == 0 && denominator.mod(BigInteger.valueOf(i)).signum() == 0) {
                System.out.println("Divisible by " + i);
                numerator = numerator.divide(BigInteger.valueOf(i));
                denominator = denominator.divide(BigInteger.valueOf(i));
            }
        }
    }
}
