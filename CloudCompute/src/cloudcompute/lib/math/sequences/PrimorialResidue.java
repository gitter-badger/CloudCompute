/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.math.sequences;

import cloudcompute.lib.parallelization.math.Sequences.PS_Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    public static void find_mt(int max, int threads, String path) throws InterruptedException, ExecutionException, Exception {
        File g = new File(path);
        g.delete();
        FileWriter f = new FileWriter(path, true);
        List<Thread> coll = new ArrayList<>();
        int __min = 0;
        int __max = max / threads;
        int ch = max / threads;
        for (int i = 0; i < threads; i++) {
            PS_Task t = new PS_Task(__min, __max, f);
            coll.add(t);
            __min += ch;
            __max += ch;
        }
        System.out.println("Starting to join tasks");
        for (Thread k : coll) {
            k.start();
        }
        for (Thread k : coll) {
            k.join();
        }

        System.out.println("All are done");

        f.close();
    }
    
    public static void find(int max, String path) throws IOException, InterruptedException {
        File g = new File(path);
        g.delete();
        FileWriter f = new FileWriter(path, true);
        PS_Task p = new PS_Task(0, max, f);
        p.start();
        p.join();
        f.close();
    }
}
