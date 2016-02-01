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
package core.lib.math.Sieves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculates list of primes
 * @author brown
 */


public class Eratosthenes {
    
    //basic method, calculates them quite fast.
    public static List<Integer> primes(int max) {
        List<Integer> p = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        int m = 1;
        p.add(2);
        p.add(3);
        p.add(5);
        p.add(7);
        p.add(11);
        p.add(13);

        for (int v : p) {
            m *= v;
        }
        for (int i = 1; i < m && i < max; i++) {
            if (p.contains(i)) {
                continue;
            }
            if (PrimeLib.arecoprime(i, m)) {
                a.add(i);
            }
            
        }
        
        List<Integer> toremove = new ArrayList<Integer>();
        
        for (int i = 0; i <= max / m; i++) {
            for  (int b : a) {
                p.add(m * i + b);
            }
        }
        
        for (int i : p) {
            if (i == 1) {
                continue;
            }
            for (int k = 2; k < max / i ; k++) {
                toremove.add(k * i);
            }
        }
        p.removeAll(toremove);
        Collections.sort(p);
        p.remove(0);
        
        return p;
        
    }
}
