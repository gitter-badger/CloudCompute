/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.parallelization.math.Sequences;

import java.util.concurrent.Callable;

/**
 *
 * @author director
 */
public class PS_Task implements Callable<int[]> {

    private int min;
    private int max;
    private int[] primorials;
    
    public PS_Task(int _min, int _max) {
        min = _min;
        max = _max;
        primorials = new int[]{1, 2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870};
    }
    
        
    @Override
    public int[] call() throws Exception {
        int[] res = new int[max - min + 1];
        for (int t = min; t <= max; t++) {
            int count = 0;
            int r = t;
            while (r != 0) {
                for (int a = primorials.length - 1; a >= 0; a--) {
                    int p = primorials[a];
                    if (p <= r) {
                        r = r % p;
                        count++;
                    }
                }
            }
            res[t - min] = (count);
        }
        return res;
    }

}
