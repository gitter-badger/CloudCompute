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
package OpenCLCore;

import OpenCLCore.Math.Arithmetic;
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
import static org.bridj.Pointer.allocateDoubles;
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
        ByteOrder byteOrder = Lib.context.getByteOrder();

        Pointer<Float> apo = allocateFloats(A.length * A[0].length).order(byteOrder);
        Pointer<Float> bpo = allocateFloats(B.length * B[0].length).order(byteOrder);
        Pointer<Float> op = allocateFloats(rows * columns).order(byteOrder);

        int counter = 0;
        for (int l1 = 0; l1 < A.length; l1++) {
            for (int l2 = 0; l2 < A[0].length; l2++) {
                apo.set(counter, A[l1][l2]);
                counter++;
            }
        }

        counter = 0;
        for (int l1 = 0; l1 < B.length; l1++) {
            for (int l2 = 0; l2 < B[0].length; l2++) {
                bpo.set(counter, B[l1][l2]);
                counter++;
            }
        }

        // Create OpenCL input buffers (using the native memory pointers aPtr and bPtr) :
        CLBuffer<Float> a = Lib.context.createBuffer(CLMem.Usage.Input, apo);
        CLBuffer<Float> b = Lib.context.createBuffer(CLMem.Usage.Input, bpo);

        CLBuffer<Float> out = Lib.context.createFloatBuffer(CLMem.Usage.Output, op);

        //CLBuffer<Float> createBuffer = context.createBuffer(CLMem.Usage.Output, Float.class, 1);
        // Read the program sources and compile them :
        // Get and call the kernel :
        CLKernel addFloatsKernel = Lib.programs.get("mat").createKernel("mul");
        addFloatsKernel.setArgs(a, b, out, rows, columns, A.length, B[0].length);
        CLEvent addEvt = addFloatsKernel.enqueueNDRange(Lib.queue, new int[]{rows * columns});

        float[][] C = new float[rows][columns];
        // System.out.println("Done");
        Pointer<Float> outPtr = out.read(Lib.queue, addEvt); // blocks until add_floats finished
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < columns; n++) {
                C[m][n] = outPtr.get(m * rows + n);
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
        CLKernel addFloatsKernel = Lib.programs.get("vsp").createKernel("vectorScalarProduct");
        addFloatsKernel.setArgs(i, j, out, length);
        CLEvent addEvt = addFloatsKernel.enqueueNDRange(Lib.queue, new int[]{length});

        // System.out.println("Done");
        Pointer<Float> outPtr = out.read(Lib.queue, addEvt); // blocks until add_floats finished

        return Arithmetic.addFloats(outPtr.getFloats());
    }
    
    public static double[][] mul(double[][] A, double[][] B) throws IOException {
        int rows = A.length;
        int columns = B[0].length;
        if (A[0].length != B.length) {
            throw new RuntimeException();
        }
        ByteOrder byteOrder = Lib.context.getByteOrder();

        Pointer<Double> apo = allocateDoubles(A.length * A[0].length).order(byteOrder);
        Pointer<Double> bpo = allocateDoubles(B.length * B[0].length).order(byteOrder);
        Pointer<Double> op = allocateDoubles(rows * columns).order(byteOrder);

        int counter = 0;
        for (int l1 = 0; l1 < A.length; l1++) {
            for (int l2 = 0; l2 < A[0].length; l2++) {
                apo.set(counter, A[l1][l2]);
                counter++;
            }
        }

        counter = 0;
        for (int l1 = 0; l1 < B.length; l1++) {
            for (int l2 = 0; l2 < B[0].length; l2++) {
                bpo.set(counter, B[l1][l2]);
                counter++;
            }
        }

        // Create OpenCL input buffers (using the native memory pointers aPtr and bPtr) :
        CLBuffer<Double> a = Lib.context.createBuffer(CLMem.Usage.Input, apo);
        CLBuffer<Double> b = Lib.context.createBuffer(CLMem.Usage.Input, bpo);

        CLBuffer<Double> out = Lib.context.createDoubleBuffer(CLMem.Usage.Output, op);

        //CLBuffer<Float> createBuffer = context.createBuffer(CLMem.Usage.Output, Float.class, 1);
        // Read the program sources and compile them :
        // Get and call the kernel :
        CLKernel addFloatsKernel = Lib.programs.get("mat_d").createKernel("mul");
        addFloatsKernel.setArgs(a, b, out, rows, columns, A.length, B[0].length);
        CLEvent addEvt = addFloatsKernel.enqueueNDRange(Lib.queue, new int[]{rows * columns});

        double[][] C = new double[rows][columns];
        // System.out.println("Done");
        Pointer<Double> outPtr = out.read(Lib.queue, addEvt); // blocks until add_floats finished
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < columns; n++) {
                C[m][n] = outPtr.get(m * rows + n);
            }
        }
        return C;
    }
}
