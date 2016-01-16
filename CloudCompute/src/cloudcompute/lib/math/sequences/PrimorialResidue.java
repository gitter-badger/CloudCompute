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
