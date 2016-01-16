/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.parallelization.math.Sequences;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
    private BufferedWriter bw;
    private boolean b_type;

    public PS_Task(int _min, int _max, FileOutputStream _f, boolean _b_type) throws UnsupportedEncodingException {
        min = _min;
        max = _max;
        primorials = new int[]{1, 2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870};
        bw = new BufferedWriter(new OutputStreamWriter(_f), 10000);
        b_type = _b_type;
    }

    @Override
    public void run() {
        if (b_type) {
            b_file();
        } else {
            storage_optimized();
        }
    }

    public void b_file() {
        String c_s = System.getProperty("line.separator");

        try {
            bw.append(0 + " " + 0 + c_s);
        } catch (IOException ex) {
            Logger.getLogger(PS_Task.class.getName()).log(Level.SEVERE, null, ex);
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
                try {
                    bw.append(t + " " + count + c_s);
                } catch (IOException ex) {
                    System.out.println("error while writing" + ex.getMessage());
                }
            }
        }
        try {
            bw.close();
        } catch (IOException ex) {
            System.out.println("error while writing" + ex.getMessage());
        }
    }

    public void storage_optimized() {
        String c_s = System.getProperty("line.separator");

        try {
            bw.append(0 + c_s);
        } catch (IOException ex) {
            Logger.getLogger(PS_Task.class.getName()).log(Level.SEVERE, null, ex);
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
                try {
                    bw.append(count + c_s);
                } catch (IOException ex) {
                    System.out.println("error while writing" + ex.getMessage());
                }
            }
        }
        try {
            bw.close();
        } catch (IOException ex) {
            System.out.println("error while writing" + ex.getMessage());
        }
    }

}
