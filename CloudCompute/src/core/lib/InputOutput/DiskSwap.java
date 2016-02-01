/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.lib.InputOutput;

import core.lib.math.DataStructure.BigInt;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author director
 */
public class DiskSwap {

    private String path;
    private File f;
    private FileWriter fw;
    private FileReader fr;
    int lines = 0;
    private String c = System.getProperty("line.separator");

    public DiskSwap(String _path) throws IOException {
        path = _path;
        f = new File(path);
        fw = new FileWriter(f);
        fr = new FileReader(f);
    }

    public int store(String s) throws IOException {
        fw.write(s + c);
        fw.flush();
        lines++;
        return lines - 1;
    }

    public String get(int line) throws IOException {
        String line_n = "";
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            line_n = lines.skip(line - 1).findFirst().get();
        } catch (Exception e) {
            return "";
        }
        return line_n;
    }

    public int getint(int line) throws IOException {
        String l = get(line);
        if (l.equals("") || l == null) {
            return 0;
        }
        return Integer.parseInt(l);
    }

    public int getlines() {
        return lines;
    }
    
    public void delete() throws IOException {
        fw.close();
        fr.close();
        f.delete();
    }

    public static DiskSwap add(DiskSwap a, DiskSwap b, String p) throws IOException {
        DiskSwap r = new DiskSwap(p);
        r.delete();
        r = new DiskSwap(p);
        int overflow = 0;
        int base = BigInt.BASE;
        for (int i = 1; (i <= a.lines || i <= b.lines) || (overflow != 0 && i > 1); i++) {
            int a_i = a.getint(i);
            int b_i = b.getint(i);
            int w_m = (a_i % base + b_i % base + overflow % base);
            int w_d = a_i / base + b_i / base + overflow / base;
            r.store("" + w_m);
            overflow = w_d;
        }
        return r;
    }
}
