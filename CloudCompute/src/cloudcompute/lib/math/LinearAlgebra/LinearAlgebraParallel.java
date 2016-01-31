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
package cloudcompute.lib.math.LinearAlgebra;

import cloudcompute.lib.parallelization.MultiThreading;
import cloudcompute.lib.parallelization.math.MatrixRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static javafx.scene.input.KeyCode.C;

/**
 *
 * @author brown
 */
public class LinearAlgebraParallel {
    
    // the full matrix multiplication
    public static double[][] multiply(double[][] A, double[][] B) throws InterruptedException, ExecutionException {
        int threads = MultiThreading.getcores();
        List<MatrixRunner> lmr = new ArrayList<>();
        int pt = (int) (A.length / threads);
        for (int i = 0; i <= threads + 1; i++) {
            int[] c = new int[pt];
            int[] d = new int[B.length];
            int ci = 0;
            for (int a = i * pt; a < (i + 1) * pt; a++) {
                if (a >= A.length) {
                    break;
                }
                c[ci] = a;
                ci++;
            }
            for (int dk = 0; dk < d.length; dk++) {
                d[dk] = dk;
            }
            lmr.add(new MatrixRunner(A, B, c, d));
        }
        lmr.stream().forEach((m) -> {
            m.start();
        });
        for (MatrixRunner m : lmr) {
            m.join();
        }
        double[][] res = new double[A.length][B[0].length];
        for (MatrixRunner m : lmr) {
            res = Matrix.add(res, m.r);
        }
        return res;
    }
    
}
