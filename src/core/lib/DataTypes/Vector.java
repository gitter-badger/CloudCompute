/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.lib.DataTypes;

/**
 *
 * @author director
 */
public class Vector {
    
    public double[] contents;
    
    public Vector(int degrees) {
        contents = new double[degrees];
    }
    
    public Vector(double[] contents) {
        this.contents = contents;
    }
    
}
