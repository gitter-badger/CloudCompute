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
package cloudcompute.lib.parallelization.math;

import cloudcompute.lib.examples.Fibonacci;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brown
 */
public class ListGen extends Thread {
    int s;
    int e;
    
    List<BigInteger> r = new ArrayList<>();
    
    public ListGen(int _s, int _e) {
        s = _s;
        e = _e;
    }
    
     @Override
    public void run() {
        r.addAll(Fibonacci.fibonacci_list_bi(s, e));
    }
}
