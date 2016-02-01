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
package core.lib.math.Sums;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author brown
 */
public class Pi {
    /*
    Calculates each individual arctan(not multithreaded) for use in
    */
    public static BigDecimal atan_inv(int inv, int digits) {
        BigDecimal result = BigDecimal.ZERO;
        MathContext m = new MathContext(digits - 2);
        
        BigDecimal a = BigDecimal.ONE;
        BigDecimal a_d = new BigDecimal(inv * inv);
        
        int d = 1;
        
        BigDecimal t = BigDecimal.ZERO;
        
        int sign = 1;
        
        BigDecimal u = BigDecimal.ONE;
        
        BigDecimal er = new BigDecimal(".1");
        er = er.pow(digits);
        
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            t = u.divide(a.multiply(BigDecimal.valueOf(d)), m);
            
            if (t.compareTo(er) <= 0) {
                break;
            }
            
            if (sign == 1) {
                result = result.add(t);
            } else {
                result = result.subtract(t);
            }
            
            a = a.multiply(a_d, m);
            d += 2;
            sign *= -1;
        }
        result = result.divide(BigDecimal.valueOf(inv), m);
        
        return result;
        
    }
    
}
