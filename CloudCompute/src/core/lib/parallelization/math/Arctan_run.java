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
package core.lib.parallelization.math;

import core.lib.examples.Fibonacci;
import core.lib.math.Sums.Pi;
import java.math.BigDecimal;

/**
 *
 * @author brown
 */
public class Arctan_run extends Thread {
    int inv;
    int digits;
    
    public BigDecimal r = BigDecimal.ZERO;
    
    public Arctan_run(int _inv, int _digits) {
        inv = _inv;
        digits = _digits;
    }
    
     @Override
    public void run() {
        r = Pi.atan_inv(inv, digits);
    }
}
