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

import core.lib.DataTypes.Complex;

/**
 *
 * @author director
 */
public class ComplexMath {
    
    public static Complex exp(Complex op) { //returns e^op
        return new Complex(op.y).scale(Math.exp(op.x));
    }
    
    public static Complex log(Complex op) { //returns ln(op)
        return new Complex(Math.log(op.norm()), Math.atan(op.y / op.x));
    }
    
    public static Complex pow(Complex op, int pow) { //returns op^pow, for an integer power
       if (pow == 0) {
           return new Complex(1, 0);
       }
       if (pow == 1) {
           return op;
       }
       if (pow == 2) {
           return op.mul(op);
       }
       if (pow % 2 == 0) {
           return pow(pow(op, 2), pow / 2);
       } else {
           return op.mul(pow(pow(op, 2), (pow - 1) / 2));
       }
    }
    
    public static Complex pow(Complex op, Complex pow) { //extends pow to Complex
        return exp(pow.mul(log(pow)));
    }
    
}
