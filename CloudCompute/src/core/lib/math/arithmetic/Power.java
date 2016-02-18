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
package core.lib.math.arithmetic;

import core.lib.DataTypes.BigRational;
import java.math.BigDecimal;

/**
 * Powers of numbers!
 * @author brown
 */
public class Power {
    /*
    Used for practice, but not really practicality
    */
    public static BigDecimal pow_bd_i(BigDecimal b, int p) {
        if (b.compareTo(BigDecimal.ONE) == 0 || p == 0) {
            return BigDecimal.ONE;
        }
        if (p == 2) {
            return b.multiply(b);
        }
        if (p % 2 == 0) {
            return pow_bd_i(b.multiply(b), p / 2);
        } else {
            return b.multiply(pow_bd_i(b.multiply(b), (p - 1) / 2));
        }
    }
}
