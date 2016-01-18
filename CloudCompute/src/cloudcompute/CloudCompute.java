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
package cloudcompute;

import cloudcompute.lib.Twitter.TwitterLib;
import cloudcompute.lib.examples.Fibonacci;
import cloudcompute.lib.math.sequences.Format;
import cloudcompute.lib.math.sequences.PrimorialResidue;
import cloudcompute.lib.parallelization.MultiThreading;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import twitter4j.TwitterException;

/**
 *
 * @author director
 */
public class CloudCompute {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, TwitterException, IOException, ExecutionException, Exception {
        TwitterLib.init();

        long n = Long.parseLong(args[1]);
        String path = args[0];

        boolean btype = Boolean.parseBoolean(args[2]);
        long start = System.nanoTime();
        System.out.println("Beginning to calculate");
        PrimorialResidue.find(n, path, btype);
        long end = System.nanoTime();
        System.out.println("Done! (" + (end - start) / 1000000000 + ")");
        System.exit(0);

    }
}
