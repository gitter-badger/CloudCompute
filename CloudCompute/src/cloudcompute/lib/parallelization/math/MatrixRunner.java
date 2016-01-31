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

import javafx.concurrent.Task;

/**
 *
 * @author brown
 */
public class MatrixRunner extends Thread {
    public double[][] A;
    public double[][] B;
    public int[] c;
    public int[] d;
    
    public double[][] r;
    public MatrixRunner(double[][] _A, double[][] _B, int[] _c, int[] _d) {
        A = _A;
        B = _B;
        c = _c;
        d = _d;
    }
    
    private double dot(double[] a, double[] b) {
        double total = 0;
        for (int i = 0; i < a.length; i++) {
            total += a[i] * b[i];
        }
        return total;
    }
    
    @Override
    public void run () {
        r = new double[A.length][B[0].length];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < d.length; j++) {
                double[] b_d = new double[B[j].length];
                for (int k = 0; k < b_d.length; k++) {
                    b_d[k] = B[k][j];
                }
                r[c[i]][d[j]] = dot(A[c[i]], b_d);
            }
        }
    }
}
