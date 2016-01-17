/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.parallelization.math.Sequences;

import cloudcompute.lib.parallelization.StringWriter;
import java.io.BufferedWriter;
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

    private long min;
    private long max;
    private long[] primorials;
    private StringWriter sw;
    private boolean b_type;

    public PS_Task(long _min, long _max, FileOutputStream _f, boolean _b_type) throws UnsupportedEncodingException {
        min = _min;
        max = _max;
        primorials = new long[]{1, 2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870, Long.parseLong("6469693230"), Long.parseLong("200560490130")};
        sw = new StringWriter(new BufferedWriter(new OutputStreamWriter(_f), 1250000000));
        b_type = _b_type;
    }

    @Override
    public void run() {
        try {
            if (b_type) {
                b_file();
            } else {
                storage_optimized();
            }
        } catch (Exception e) {
            
        }
    }

    //Like a b-file on OEIS
    public void b_file() throws InterruptedException, IOException {
        String c = System.getProperty("line.separator");
        sw.add(0 + " " + 0 + c);
        for (long t = min; t <= max; t++) {
            int count = 0;
            long r = t;
            while (r != 0) {
                for (int a = primorials.length - 1; a >= 0; a--) {
                    long p = primorials[a];
                    if (p <= r) {
                        r = r % p;
                        count++;
                    }
                }
                sw.add(t + " " + count + c);
            }
        }
        sw.start();
        sw.join();
    }

    //has a comma inbetween values
    public void storage_optimized() throws InterruptedException, IOException {
        String c = ",";
        sw.add(0 + c);
        for (long t = min; t <= max; t++) {
            int count = 0;
            long r = t;
            while (r != 0) {
                for (int a = primorials.length - 1; a >= 0; a--) {
                    long p = primorials[a];
                    if (p <= r) {
                        r = r % p;
                        count++;
                    }
                }
                if (t != max) {
                    sw.add(count + c);
                } else {
                    sw.add("" + count);
                }
            }
        }
        sw.start();
        sw.join();
    }

}
