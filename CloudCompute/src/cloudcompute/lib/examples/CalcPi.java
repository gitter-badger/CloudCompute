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

import cloudcompute.lib.math.Sums.Pi;
import java.math.BigDecimal;

/**
 *
 * @author brown
 */
public class CalcPi {
    
    public static BigDecimal pi_arctan(int digits) {
        BigDecimal f = new BigDecimal(4);
        BigDecimal total = BigDecimal.ZERO;
        total = total.add(f.multiply(Pi.atan_inv(5, digits)));
        total = total.subtract(Pi.atan_inv(239, digits));
        total = f.multiply(total);
        return total;
    }
    
}
