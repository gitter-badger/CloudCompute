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
import cloudcompute.lib.parallelization.math.Arctan_run;
import java.math.BigDecimal;

/**
 * Used to generate digits of pi. Currently using an inefficient arctan method that has O(b^digits) complexity
 * @author brown
 */
public class CalcPi {
    
    /*
    Calculates pi using multithreading and a derivation of machin's formula
    */
    public static BigDecimal pi_arctan(int digits) throws InterruptedException {
        BigDecimal a = new BigDecimal(176);
        BigDecimal b = new BigDecimal(28);
        BigDecimal c = new BigDecimal(-48);
        BigDecimal d = new BigDecimal(96);
        BigDecimal total = BigDecimal.ZERO;
        
        //Manually doing all this, need to change it.
        Arctan_run t1 = new Arctan_run(57, digits);
        Arctan_run t2 = new Arctan_run(239, digits);
        Arctan_run t3 = new Arctan_run(682, digits);
        Arctan_run t4 = new Arctan_run(12943, digits);
        t1.run();
        t2.run();
        t3.run();
        t4.run();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        
        total = total.add(a.multiply(t1.r));
        total = total.add(b.multiply(t2.r));
        total = total.add(c.multiply(t3.r));
        total = total.add(d.multiply(t4.r));
        
        return total;
    }
    
}
