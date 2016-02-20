/* This is not supported on most integrated graphics devices */
__kernel void mul(__global double* a, __global double* b, __global double* out, int row, int col, int aw, int bw) { 
#pragma OPENCL EXTENSION cl_khr_fp64 : enable
int i = get_global_id(0); 
double r = 0;
int currow = i / aw;
int curcol = i % aw;
for (int k = 0; k < aw; k++) {
r += a[aw * currow + k] * b[bw * k + curcol];
}
out[i] = r;
}
