/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudcompute.lib.Analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author director
 */
public class Distribution {
    
    public static HashMap<Object, Integer> getDistrobution(List<? extends Object> l) {
        HashMap<Object, Integer> b = new HashMap<>();
        for (Object o : l) {
            b.put(o, b.get(o) + 1);
        }
        return b;
    }
    
}
