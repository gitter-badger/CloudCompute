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
package cloudcompute;

import cloudcompute.lib.IO;
import cloudcompute.lib.examples.CalcPi;
import cloudcompute.lib.examples.Fibonacci;
import cloudcompute.lib.math.LinearAlgebra.Matrix;
import cloudcompute.lib.math.Sums.Pi;
import cloudcompute.lib.math.arithmetic.Power;
import cloudcompute.lib.parallelization.math.Fibonacci_par;
import java.math.BigDecimal;

/**
 *
 * @author director
 */
public class CloudCompute {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        /*int x = 1000000000;
        IO.write(args[0] + x + ".txt"  , Fibonacci.fibonacci_bi(x).toString());*/
        int n = 10000;
        //IO.write(args[0] + n + ".txt", "" + Fibonacci.fibonacci_bi(n));
        IO.write(args[0] + n + ".txt", "" + Fibonacci_par.fibonacci_list_bi(n));
        
    }
}
