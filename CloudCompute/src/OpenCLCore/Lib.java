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
package OpenCLCore;

import com.nativelibs4java.opencl.CLContext;
import com.nativelibs4java.opencl.CLProgram;
import com.nativelibs4java.opencl.CLQueue;
import com.nativelibs4java.opencl.JavaCL;
import com.nativelibs4java.util.IOUtils;
import java.io.IOException;
import java.util.HashMap;

/**
 * Library for OpenCL code. OpenCL is very volatile, but can speed up calculations. You need to make sure your GPU and CPU can handle 64 bit operations before using mat_d, or any double precision
 * @author brown
 */
public class Lib {
    //programs with easy string indexes
    public static HashMap<String, CLProgram> programs = new HashMap<>();
    
    //context for using OpenCl, normally a GPU
    public static CLContext context = JavaCL.createBestContext();

    //queues operations with a device
    public static CLQueue queue = context.createDefaultQueue();

    //Creates kernel refernces and compiles, saves time later
    public static void init() throws IOException {
        
        CLProgram program_vsp = context.createProgram(IOUtils.readText(Lib.class.getResource("kernels/vectorscalarproduct.cl")));
        CLProgram program_mat = context.createProgram(IOUtils.readText(Lib.class.getResource("kernels/matrix.cl")));
        CLProgram program_mat_d = context.createProgram(IOUtils.readText(Lib.class.getResource("kernels/matrix_d.cl")));
        programs.put("vsp", program_vsp);
        programs.put("mat", program_mat);
        programs.put("mat_d", program_mat_d);
    }

}
