/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.lib.DataTypes;

/**
 * Complex vector, as x + iy where x and y are real, and i * i = -1
 * @author director
 */
public class Complex { //in x + iy
    public double x;
    public double y;
    
    public Complex(double x, double y) { //from x + iy
        this.x = x;
        this.y = y;
    }
    
    public Complex(double rad) { //from e^(i*rad)
        this.x = Math.cos(rad); 
        this.y = Math.sin(rad);
    }
    
    public Complex conj() { //returns conjugate
        return new Complex(x, -y);
    }
    
    public Complex add(Complex op) { //adds two Complex
        return new Complex(x + op.x, y + op.y);
    }
    
    public Complex sub(Complex op) { //subtracts two complex
        return new Complex(x - op.x, y - op.y);
    }
    
    public Complex dot(Complex op) { //dot product of vectors
        return new Complex(x * op.x, y * op.y);
    }
    
    public Complex mul(Complex op) { //multiplication of two complex
        return new Complex(x * op.x - y * op.y, x * op.y + y * op.x);
    }
    
    public Complex scale(double factor) { //scales the Complex by a factor
        return new Complex(factor * x, factor * y);
    }
    
    public Complex div(Complex op) { //returns (this / op)
        return mul(op.inv());
    }
    
    public Complex inv() { //returns this^-1
        return new Complex(x, -y).scale(1 / (x * x + y * y));
    }
    
    public double norm() { //returns the modulus, absolute value, etc of this
        return Math.sqrt(x * x + y * y);
    }
    
    public Complex unit() { //returns unit vector
        return scale(1 / norm());
    }
}
