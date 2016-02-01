/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.lib.math.DataStructure;

import core.lib.InputOutput.DiskSwap;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DiskSwap for integers. Stores in binary, lowest place value is on the far
 * left. So 1000 in binary is 1 in base 10, not 8
 *
 * @author director
 */
public class DiskSwapBigInt {

    private String path;
    private File f;
    private FileWriter fw;
    private FileReader fr;
    private char tr = '1';
    private char fa = '0';
    private int max;

    public DiskSwapBigInt(String _path, int _max) throws IOException, InterruptedException {
        path = _path;
        max = _max;
        init();
    }

    public void write() throws IOException, InterruptedException {
        Thread t = new Thread() {
            public void run() {
                try {
                    fw.flush();
                } catch (IOException ex) {
                }
            }
        };
        t.start();
        t.join();
    }

    private void init() throws IOException, InterruptedException {
        f = new File(path);
        fw = new FileWriter(f);
        fr = new FileReader(f);
        write();

    }

    private void refresh() throws IOException, InterruptedException {
        write();
        f = new File(path);
        fw = new FileWriter(f);
        fr = new FileReader(f);
    }

    public void set(int n) throws IOException, InterruptedException {
        boolean[] val = new boolean[31];
        int p2 = Integer.MAX_VALUE / 2 + 1;
        int count = 1;
        while (count < 32) {
            if (n >= p2) {
                val[31 - count] = true;
                n -= p2;
            } else {
                val[31 - count] = false;
            }
            count++;
            p2 /= 2;
        }
        writeBits(val);
        while (count < max) {
            writeBit(false);
            count++;
        }
        //refresh();
    }

    public void destroyExcessZeroes() {

    }

    public static DiskSwapBigInt add(DiskSwapBigInt a, DiskSwapBigInt b, String path) throws IOException, InterruptedException {
        DiskSwapBigInt res = new DiskSwapBigInt(path, Math.max(a.max, b.max) + 1);
        boolean overflow = false;
        int c = 0;
        boolean t;
        boolean add;
        boolean and;
        boolean xor;
        boolean any2;
        while (c < 100) {
            t = parse((char) a.fr.read());
            add = parse((char) b.fr.read());
            and = t && add;
            xor = overflow ^ t ^ add;
            any2 = and || t && overflow || add && overflow;
            res.writeBit(xor);
            if (any2) {
                overflow = true;
            } else {
                overflow = false;
            }
            c++;
        }
        //res.refresh();
        res.write();
        a.write();
        b.write();
        return res;
    }

    private static boolean parse(char c) {
        if (c == '1') {
            return true;
        }
        return false;
    }

    private void writeBits(boolean[] v) throws IOException, InterruptedException {
        for (boolean b : v) {
            writeBit(b);
        }
    }

    private void writeBit(boolean v) throws IOException, InterruptedException {
        if (v) {
            fw.write(tr);
        } else {
            fw.write(fa);
        }
        write();
    }

    private void replaceBit(boolean v, int i) throws IOException, InterruptedException {
        if (v) {
            fw.write("" + fa, 0, 0);
        } else {
            fw.write("" + tr, 0, 0);
        }
        write();
    }

}
