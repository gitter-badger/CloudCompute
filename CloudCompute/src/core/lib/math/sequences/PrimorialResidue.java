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
package core.lib.math.sequences;

import core.lib.parallelization.math.Sequences.PS_Task;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This is used for calculating the number of non-zero digits in the primorial
 * base expansion of an Integer n. (sequence A267263 in OEIS)
 *
 * @author director
 */
public class PrimorialResidue {

    //finds first priorial residue sequence, writes it out to a path, with either csv or btype
    public static void find(long max, String path, boolean btype) throws IOException, InterruptedException {
        File g = new File(path);
        g.delete();
        FileOutputStream s = new FileOutputStream(path);
        PS_Task p = new PS_Task(0, max, s, btype);
        p.start();
        p.join();
    }
}
