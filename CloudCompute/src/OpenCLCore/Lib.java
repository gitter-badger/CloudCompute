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

import com.nativelibs4java.opencl.CLContext;
import com.nativelibs4java.opencl.CLProgram;
import com.nativelibs4java.opencl.CLQueue;
import com.nativelibs4java.opencl.JavaCL;
import com.nativelibs4java.util.IOUtils;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author brown
 */
public class Lib {
    public static HashMap<String, CLProgram> programs = new HashMap<>();
    public static CLContext context = JavaCL.createBestContext();
    /*public static String src_vsp;
    public static CLProgram program_vsp;
    public static String src_pr;
    public static CLProgram program_pr;
    public static String src_mat;
    public static CLProgram program_mat;*/
    public static CLQueue queue = context.createDefaultQueue();

    public static void init() throws IOException {
        
        CLProgram program_vsp = context.createProgram(IOUtils.readText(Lib.class.getResource("kernels/vectorscalarproduct.cl")));
        CLProgram program_pr = context.createProgram(IOUtils.readText(Lib.class.getResource("kernels/primorialresidue.cl")));
        CLProgram program_mat = context.createProgram(IOUtils.readText(Lib.class.getResource("kernels/matrix.cl")));
        CLProgram program_mat_d = context.createProgram(IOUtils.readText(Lib.class.getResource("kernels/matrix_d.cl")));
        programs.put("vsp", program_vsp);
        programs.put("pr", program_pr);
        programs.put("mat", program_mat);
        programs.put("mat_d", program_mat_d);
    }

}
