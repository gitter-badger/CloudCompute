/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
