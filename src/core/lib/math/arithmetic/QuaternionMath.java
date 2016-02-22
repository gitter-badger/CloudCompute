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

import core.lib.DataTypes.Quaternion;

/**
 * Tons of math with Quaternion class
 *
 * @author director
 */
public class QuaternionMath {

    //returns e^h
    public static Quaternion exp(Quaternion h) {
        double realScale = Math.exp(h.a);
        Quaternion imagVector = new Quaternion(0, h.b, h.c, h.d);
        Quaternion imagScale = new Quaternion(Math.cos(h.norm()), 0, 0, 0);
        double imagNorm = imagVector.norm();
        imagScale = imagScale.add(imagVector.scale(Math.sin(imagNorm) / imagNorm));
        return imagScale.scale(realScale);
    }

    //returns ln(h)
    public static Quaternion ln(Quaternion h) {
        double norm = h.norm();
        double ln_r = Math.log(norm);
        Quaternion imagVector = new Quaternion(0, h.b, h.c, h.d);
        double imagScale = Math.acos(h.a / norm) / imagVector.norm();
        return imagVector.scale(imagScale);
    }

    //returns log_base(h) base logarithm of a quaternion
    public static Quaternion log(Quaternion base, Quaternion h) {
        return ln(h).divide(ln(base));
    }

    //returns base^power , only for integers
    public static Quaternion pow(Quaternion base, int power) {
        if (power == 0) {
            return new Quaternion(1, 0, 0, 0);
        }
        if (power == 1) {
            return base;
        }
        if (power == 2) {
            return base.multiply(base);
        }
        if (power % 2 == 0) {
            return pow(pow(base, 2), power / 2);
        } else {
            return base.multiply(pow(pow(base, 2), (power - 1) / 2));
        }
    }
    
    //returns base^power , but for quaternion powers
    public static Quaternion pow(Quaternion base, Quaternion power) {
        return exp(power.multiply(ln(base)));
    }
}
