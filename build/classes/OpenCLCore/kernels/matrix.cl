__kernel void mul(__global float* a, __global float* b, __global float* out, int row, int col, int aw, int bw) { 
int i = get_global_id(0); 
float r = 0;
int currow = i / aw;
int curcol = i % aw;
for (int k = 0; k < aw; k++) {
r += a[aw * currow + k] * b[bw * k + curcol];
}
out[i] = r;
}
