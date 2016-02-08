/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.lib.math.arithmetic;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author director
 */
public class Root {

    public static BigDecimal sqrt(BigDecimal x, MathContext mc) {
        BigDecimal TWO = new BigDecimal(2);
        BigDecimal g = x.divide(TWO, mc);
        boolean done = false;
        final int maxIterations = mc.getPrecision() + 5;
        for (int i = 0; !done && i < maxIterations; i++) {
            BigDecimal r = x.divide(g, mc);
            r = r.add(g);
            r = r.divide(TWO, mc);
            done = r.equals(g);
            g = r;
        }
        return g;
    }
}
