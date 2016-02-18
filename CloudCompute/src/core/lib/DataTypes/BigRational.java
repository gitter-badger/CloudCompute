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
package core.lib.DataTypes;

import core.lib.math.Sieves.Eratosthenes;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;

/**
 * A BigInteger / BigInteger, division at end. Terribly inefficient for sums, ex. a / b + c / d + f / g = (dga + bgc + bdf) / (bdg). Gets large quickly
 * @author director
 */
public class BigRational {

    public BigInteger numerator;
    public BigInteger denominator;

    public BigRational(BigInteger n, BigInteger d) { //creates n / d with digits of precision
        numerator = n;
        denominator = d;
    }

    public BigRational add(BigRational a) { //returns this + a
        return new BigRational(numerator.multiply(a.denominator).add((a.numerator.multiply(denominator))), denominator.multiply(a.denominator));
    }

    public BigRational mul(BigRational m) { //returns this * m
        return new BigRational(numerator.multiply(m.numerator), denominator.multiply(m.denominator));
    }
    
    public BigRational inv() { //returns 1 / this
        return new BigRational(denominator, numerator);
    }

    public BigRational div(BigRational d) { //returns this / d
        return this.mul(d.inv());
    }

    public BigDecimal getDecimal(int digits) { //returns BigDecimal with specified digits
        return (new BigDecimal(numerator)).divide(new BigDecimal(denominator), new MathContext(digits));
    }

    public void factor() { //factors the numerator and denominator
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
