package cloudcompute.JavaCLImplementation;

import java.io.IOException;

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
/**
 *
 * @author brown
 */
public class JavaCLProgram {

    public static void main(String[] args) throws IOException {
        Lib.init();
        //Calculates e^n
        float n = 1.03234f;
        float n_p = 1;
        int s = 1000;
        float[][] A = new float[s][1];
        float[][] B = new float[1][s];
        for (int i = 0; i < s; i++) {
            A[i][0] = n_p;
            n_p *= n;
        }
        float k_s = 1;
        for (int i = 0; i < s; i++) {
            B[0][i] = k_s;
            k_s /= (i + 1);
        }

        long start = System.nanoTime();
        float[][] AB = Matrix.mul(B, A);
        long end = System.nanoTime();
        System.out.println((end - start) / (Math.pow(10, 9)) + "\n");

        System.out.print("\n");

        for (int i = 0; i < AB.length; i++) {
            for (int j = 0; j < AB[i].length; j++) {
                System.out.print(AB[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }
}
