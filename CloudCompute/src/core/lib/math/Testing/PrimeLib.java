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
package core.lib.math.Testing;

import java.util.List;

/**
 * Everything to do with primes!
 * @author brown
 */


public class PrimeLib {
    /*
    Return true if they are coprime
    */
    public static boolean arecoprime(int a, int b) {
        if (a != 1 && b != 1) {
            if (a == b + 1 || a == b - 1) {
                return true;
                }
                for (int i = 2; i <= Math.min(a, b); i++) {
                    if (a % i == 0 && b % i == 0) {
                        return false;
                    }
                }
            }
        return true;
    }
    /*
    Returns true if all in a are coprime to each other (set-wise coprime)
    */
    public static boolean arecoprime_s(List<Integer> a) {
        int prev = 0;
        for (int v : a) {
            if (!arecoprime(v, prev)) {
                return false;
            }
            prev = v;
        }
        return true;
    }
}
