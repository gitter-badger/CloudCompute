__kernel void vectorScalarProduct(__global float* v, __global float* s, __global float* op, int n) { 
int i = get_global_id(0); 
if (i >= n) return;
op[i] += v[i] * s[i];
}

