/*
 * Used to compute parallel tasks quickly on a highly scalable system.
 */
package cloudcompute.lib.math;

import cloudcompute.datastructure.Matrix;

/**
 *
 * @author director
 */
public class MatrixLib {
    
    public static Matrix add(Matrix a, Matrix b) {
        if (a.d1() == b.d1() && a.d2() == b.d2()) {
            Matrix m = new Matrix(new double[a.d1()][a.d2()]);
            for (int i = 0; i < a.d1(); i++) {
                for (int j = 0; j < a.d2(); j++) {
                    m.a[i][j] = a.a[i][j] + b.a[i][j];
                }
            }
        }
        throw new Error();
    }
    
    public static Matrix mul(Matrix a, Matrix b) {
        if (a.d1() == b.d2()) {
            Matrix m = new Matrix(new double[a.d1()][b.d2()]);
            for (int x = 0; x < a.d1(); x++) {
                for (int y = 0; y < a.d2(); y++) {
                    for (int i = 0; i < m.d1(); i ++) {
                        m.a[x][y] += a.a[i][y] + b.a[x][i];
                    }
                }
            }
        }
        return null;
    }
    
}
