/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.math.sequences;

import cloudcompute.lib.parallelization.math.Sequences.PS_Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This is used for calculating the number of non-zero digits in the primorial
 * base expansion of an Integer n. (sequence A267263 in OEIS)
 *
 * @author director
 */
public class PrimorialResidue {

    /*
    Used to calculate list of the sequence. Good for roughly 2 billion values
     */
    public static int[] find(int max, int threads) throws InterruptedException, ExecutionException, Exception {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<int[]>> list = new ArrayList<Future<int[]>>();
        
        for (int i = 0; i < max; i++) {
            Callable<int[]> callable = new PS_Task(i * max / threads, (i+1) * max / threads);
            Future<int[]> future = executor.submit(callable);
            list.add(future);
            
        }
        
        int[] res = new int[max + 1];
        int c = 0;
        for (Future<int[]> f : list) {
            for (int v : f.get()) {
                if (!(c > max)) {
                    res[c] = v;
                    c++;
                } else {
                    return res;
                }
            }
        }
        
        return res;
    }
}
