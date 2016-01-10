/*
 * Used to compute parallel tasks quickly on a highly scalable system.
 */
package cloudcompute.datastructure;

/**
 *
 * @author director
 */
public class Matrix {
    
    public double[][] a;
    protected int d1;
    protected int d2;
    
    public Matrix(double[][] _a) {
        a = _a;
        d1 = a.length;
        d2 = a[0].length;
    }
    
    public double get(int i, int j) {
        try {
            return a[i][j];
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int d1() {
        return d1;
    }
    
    public int d2() {
        return d2;
    }
}
