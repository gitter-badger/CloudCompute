/*
 * Copyright (C) ChemicalDevelopment 2016
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cloudcompute.lib.math.sequences;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author brown
 */
public class Format {

    public static String pair(List<?> l, String path) throws FileNotFoundException, IOException {
        System.out.println("Making string");
        String res = "";
        String c = System.getProperty("line.separator");
        Object[] t = l.toArray();

        FileOutputStream out = new FileOutputStream(path);
        for (int i = 0; i < t.length; i++) {
            String s = (i + ", " + t[i] + c);
            for (char v : s.toCharArray()) {
                out.write(v);
            }
        }
        return null;
    }
}