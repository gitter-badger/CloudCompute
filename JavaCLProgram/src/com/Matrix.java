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
package com;

import com.Math.Arithmetic;
import com.nativelibs4java.opencl.CLBuffer;
import com.nativelibs4java.opencl.CLContext;
import com.nativelibs4java.opencl.CLEvent;
import com.nativelibs4java.opencl.CLKernel;
import com.nativelibs4java.opencl.CLMem;
import com.nativelibs4java.opencl.CLProgram;
import com.nativelibs4java.opencl.CLQueue;
import com.nativelibs4java.opencl.JavaCL;
import com.nativelibs4java.util.IOUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.bridj.Pointer;
import static org.bridj.Pointer.allocateFloats;
import static org.bridj.Pointer.allocateInts;

/**
 *
 * @author brown
 */
public class Matrix {

    public static float[][] pow(float[][] A, int p) throws IOException {
        if (p == 1) {
            return A;
        }
        if (p == 2) {
            return mul(A, A);
        }
        if (p % 2 == 0) {
            return pow(pow(A, 2), p / 2);
        } else {
            return mul(A, pow(pow(A, 2), (p - 1) / 2));
        }
    }

    public static float[][] mul(float[][] A, float[][] B) throws IOException {
        int rows = A.length;
        int columns = B[0].length;
        if (A[0].length != B.length) {
            throw new RuntimeException();
        }
        float[][] C = new float[rows][columns];
        for (int j = 0; j < columns; j++) {
            float[] curCol = new float[B.length];
            for (int k = 0; k < curCol.length; k++) {
                curCol[k] = B[k][j];
            }
            for (int i = 0; i < rows; i++) {
                long start = System.nanoTime();
                C[i][j] = vectorScalar(A[i], curCol);
                long end = System.nanoTime();
                //System.out.println((end - start)/(Math.pow(10, 9)) + "\n");
            }
        }
        return C;
    }

    public static float vectorScalar(float[] v, float[] s) throws IOException {
        int length = v.length;
        //System.out.println("Starting");

        ByteOrder byteOrder = Lib.context.getByteOrder();

        Pointer<Float> vpo = allocateFloats(length).order(byteOrder);
        Pointer<Float> spo = allocateFloats(length).order(byteOrder);
        Pointer<Float> op = allocateFloats(length).order(byteOrder);

        for (int i = 0; i < length; i++) {
            vpo.set(i, v[i]);
            spo.set(i, s[i]);
        }

        // Create OpenCL input buffers (using the native memory pointers aPtr and bPtr) :
        CLBuffer<Float> i = Lib.context.createBuffer(CLMem.Usage.Input, vpo);
        CLBuffer<Float> j = Lib.context.createBuffer(CLMem.Usage.Input, spo);

        CLBuffer<Float> out = Lib.context.createFloatBuffer(CLMem.Usage.Output, op);

        //CLBuffer<Float> createBuffer = context.createBuffer(CLMem.Usage.Output, Float.class, 1);
        // Read the program sources and compile them :
        // Get and call the kernel :
        CLKernel addFloatsKernel = Lib.program_vsp.createKernel("vectorScalarProduct");
        addFloatsKernel.setArgs(i, j, out, length);
        CLEvent addEvt = addFloatsKernel.enqueueNDRange(Lib.queue, new int[]{length});

        // System.out.println("Done");
        Pointer<Float> outPtr = out.read(Lib.queue, addEvt); // blocks until add_floats finished

        return Arithmetic.addFloats(outPtr.getFloats());
    }
}
