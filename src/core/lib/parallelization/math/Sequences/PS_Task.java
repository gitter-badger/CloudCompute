/* 
 * Copyright (C) 2016 ChemicalDevelopment
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package core.lib.parallelization.math.Sequences;

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
public class PS_Task extends Thread { //A multi-threaded approach (non-sorted, of course) of a thread that writes out primorial residue sequence

    private long min;
    private long max;
    private long[] primorials;
    private BufferedWriter bw;
    private boolean b_type;

    public PS_Task(long _min, long _max, FileOutputStream _f, boolean _b_type) throws UnsupportedEncodingException {
        min = _min;
        max = _max;
        primorials = new long[]{1, 2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870, Long.parseLong("6469693230"), Long.parseLong("200560490130")};
        bw = new BufferedWriter(new OutputStreamWriter(_f), 1250000000);
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
        bw.append(0 + " " + 0 + c);
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
                bw.append(t + " " + count + c);
            }
        }
    }

    //has a comma inbetween values
    public void storage_optimized() throws InterruptedException, IOException {
        String c = ",";
        bw.append(0 + c);
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
                    bw.append(count + c);
                } else {
                    bw.append("" + count);
                }
            }
        }

    }
}
