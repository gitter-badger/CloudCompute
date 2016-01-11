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
import cloudcompute.lib.parallelization.MultiThreading;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author brown
 */
public class Fibonacci_par {

    public static List<Integer> fibonacci_list(int n) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        for (int i = 0; i < MultiThreading.getcores(); i++) {
            l.addAll(Fibonacci.fibonacci_list(3 + n * i, n * (i + 1)));
        }
        return l;

    }

    public static List<BigInteger> fibonacci_list_bi(int n) throws InterruptedException {
        Set<BigInteger> hs = new HashSet<>();
        List<BigInteger> l = new ArrayList<>();

        int b = n / MultiThreading.getcores();
        ListGen[] ls = new ListGen[MultiThreading.getcores()];
        for (int i = 0; i < MultiThreading.getcores(); i++) {
            ls[i] = new ListGen(b * i, b * (i + 1));
        }
        
        for (int i = 0; i < ls.length; i++) {
            ls[i].start();
        }
        for (int i = 0; i < ls.length; i++) {
            ls[i].join();
        }
        for (int i = 0; i < ls.length; i++) {
            System.out.println("" + ls[i].r);
            l.addAll(ls[i].r);
            
        }
        
        hs.addAll(l);
        l.clear();
        l.addAll(hs);
        Collections.sort(l);
        return l;

    }
}
