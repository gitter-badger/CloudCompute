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

import cloudcompute.lib.IO;
import cloudcompute.lib.Twitter.TwitterLib;
<<<<<<< HEAD
import cloudcompute.lib.examples.Fibonacci;
=======
import cloudcompute.lib.math.Sieves.Eratosthenes;
>>>>>>> origin/master
import cloudcompute.lib.math.sequences.Format;
import cloudcompute.lib.math.sequences.PrimorialResidue;
import twitter4j.TwitterException;

/**
 *
 * @author director
 */
public class CloudCompute {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, TwitterException {
        TwitterLib.init();
        
        int i = Integer.parseInt(args[0]);
        String path = "C:/Temp/CC/Fibonacci/";
        long start = System.nanoTime();
        IO.write(path + i + ".txt", "" + Fibonacci.fibonacci_bi_optimized(i));
        long end = System.nanoTime();
        System.out.println("Done! (" + (end - start) / 1000000000 +  ")");

        /*int i = Integer.parseInt("10000000");
        String path = "C:/Temp/CC/PrimorialResidue/";
        long start = System.nanoTime();
        IO.write(path + i + ".txt", "" + Format.pair(Eratosthenes.primes(i)));
        long end = System.nanoTime();
        System.out.println("Done! (" + (end - start) / 1000000000 +  ")");*/

        /*int digits = 40000;
        String s = CalcPi.pi_arctan(digits + 25).toString();
        int digits = 40000;
        String s = CalcPi.pi_arctan(digits + 250).toString();
        System.out.println("Calculated " + digits + "of pi");
        Thread.sleep(1000 * 60 * 60 * 3);
        int tweetsperhour = 6; //LESS THAN 100
        System.out.println("Beginning to tweet");

        for (int i = 0; i < digits; i += 140) {
            TwitterLib.tweet(s.substring(i, i + 139));
            Thread.sleep(3600000 / tweetsperhour);
        }*/
    }
}
