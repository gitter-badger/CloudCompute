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

/**
 * Quaternion! 4 dimensional imaginary numbers!
 *
 * @author director
 */
public class Quaternion {

    public double a, b, c, d; //a + bi + cj + dk

    //Above construction
    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    //returns this + h
    public Quaternion add(Quaternion h) {
        return new Quaternion(a + h.a, b + h.b, c + h.c, d + h.d);
    }

    //returns this - h
    public Quaternion subtract(Quaternion h) {
        return new Quaternion(a - h.a, b - h.b, c - h.c, d - h.d);

    }

    //returns this * h
    public Quaternion multiply(Quaternion h) {
        return new Quaternion(a * h.a - b * h.b - c * h.c - d * h.c, a * h.b + b * h.a + c * h.d - d * h.c, a * h.c - b * h.d + c * h.a + d * h.b, a * h.d + b * h.c - c * h.b + d * h.a);
    }

    //returns this * h^-1, which represents this / h
    public Quaternion divide(Quaternion h) {
        return this.multiply(h.inv());
    }

    //returns the inverse, or this * inverse = 1 + 0i + 0j + 0k
    public Quaternion inv() {
        return this.conj().scale(1 / normSquared());
    }

    //scales it by a constant
    public Quaternion scale(double factor) {
        return new Quaternion(a * factor, b * factor, c * factor, d * factor);
    }

    //returns conjugation  of this
    public Quaternion conj() {
        return new Quaternion(a, -b, -c, -d);
    }

    //returns unit vector
    public Quaternion unit() {
        return this.scale(1 / norm());
    }

    public double norm() {
        return Math.sqrt(normSquared());
    }

    public double normSquared() {
        return a * a + b * b + c * c + d * d;
    }
}
