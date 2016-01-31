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

import com.nativelibs4java.opencl.CLContext;
import com.nativelibs4java.opencl.CLProgram;
import com.nativelibs4java.opencl.CLQueue;
import com.nativelibs4java.opencl.JavaCL;
import com.nativelibs4java.util.IOUtils;
import java.io.IOException;

/**
 *
 * @author brown
 */
public class Lib {
    
    public static CLContext context = JavaCL.createBestContext();
    public static String src_vsp;
    public static CLProgram program_vsp;
    public static String src_pr;
    public static CLProgram program_pr;
    public static CLQueue queue = context.createDefaultQueue();

    public static void init() throws IOException {
         src_vsp = IOUtils.readText(JavaCLProgram.class.getResource("kernels/vectorscalarproduct.cl"));
         program_vsp = context.createProgram(src_vsp);
         src_pr = IOUtils.readText(JavaCLProgram.class.getResource("kernels/primorialresidue.cl"));
         program_pr = context.createProgram(src_pr);
    }
    
}
