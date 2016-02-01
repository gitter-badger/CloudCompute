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
package OpenCLCore.Math;

import com.nativelibs4java.opencl.CLBuffer;
import com.nativelibs4java.opencl.CLEvent;
import com.nativelibs4java.opencl.CLKernel;
import com.nativelibs4java.opencl.CLMem;
import OpenCLCore.Lib;
import core.lib.IO;
import java.nio.ByteOrder;
import org.bridj.Pointer;
import static org.bridj.Pointer.allocateFloats;
import static org.bridj.Pointer.allocateInts;

/**
 *
 * @author brown
 */
public class WritePrimorialResidue {
    
    public static void write(int max, String path) {
        IO.write(path, find(max));
    }
    
    public static String find(int max) {
        //System.out.println("Starting");

        ByteOrder byteOrder = Lib.context.getByteOrder();

        Pointer<Integer> ip = allocateInts(max).order(byteOrder);
        Pointer<Integer> op = allocateInts(max).order(byteOrder);

        for (int i = 0; i < max; i++) {
            ip.set(i, i);
        }

        // Create OpenCL input buffers (using the native memory pointers aPtr and bPtr) :
        CLBuffer<Integer> i = Lib.context.createBuffer(CLMem.Usage.Input, ip);

        CLBuffer<Integer> out = Lib.context.createIntBuffer(CLMem.Usage.Output, op);

        //CLBuffer<Float> createBuffer = context.createBuffer(CLMem.Usage.Output, Float.class, 1);
        // Read the program sources and compile them :
        // Get and call the kernel :
        CLKernel addFloatsKernel = Lib.programs.get("pr").createKernel("primorialResidue");
        addFloatsKernel.setArgs(i, out, max);
        CLEvent addEvt = addFloatsKernel.enqueueNDRange(Lib.queue, new int[]{max});

       // System.out.println("Done");

        Pointer<Integer> outPtr = out.read(Lib.queue, addEvt); // blocks until add_floats finished

        String r = "" + outPtr.get(0) + ", ";
        for (int c = 1; c < outPtr.getInts().length; c++) {
            r += outPtr.getInts()[c] + ", ";
        }
        return r;
    }
    
}
