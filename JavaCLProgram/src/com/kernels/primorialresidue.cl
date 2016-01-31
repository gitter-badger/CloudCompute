__kernel void primorialResidue(__global int* input, __global int* output, int n) { 
int i = get_global_id(0); 
if (i >= n) return;
int primorials[10] = {1, 2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870};
int r = input[i];
int count = 0;
while (r != 0) {
    for (int a = sizeof(primorials) / sizeof(primorials[0]) - 1; a >= 0; a = a - 1) {
        long p = primorials[a];
            if (p <= r) {
                r = r % p;
                count++;
            }
        }
        
    }
output[i] = count;
}


