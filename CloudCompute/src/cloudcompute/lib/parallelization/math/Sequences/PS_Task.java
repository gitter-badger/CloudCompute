/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.parallelization.math.Sequences;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author director
 */
public class PS_Task extends Thread {

    private int min;
    private int max;
    private int[] primorials;
    private FileWriter w;

    public PS_Task(int _min, int _max, FileWriter _w) {
        min = _min;
        max = _max;
        primorials = new int[]{1, 2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870};
        w = _w;
    }

    @Override
    public void run() {
        String c = System.getProperty("line.separator");
        try {
            w.append("0 0" + c);
        } catch (IOException ex) {
            System.out.println("error while writing" + ex.getMessage());

        }
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
                String s = t + " " + count + c;
                try {
                    w.append(s);
                } catch (IOException ex) {
                    System.out.println("error while writing" + ex.getMessage());
                }
            }
        }
    }

}
