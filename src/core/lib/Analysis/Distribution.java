/* 
 * Copyright (C) 2016 ChemicalDevelopment
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package core.lib.Analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Distribution stats on a dataset
 * @author director
 */
public class Distribution {
    
    public static HashMap<Object, Integer> getDistrobution(List<? extends Object> l) { //returns a hashmap with the counts of each object in l. For example, getD({1, 3, 5, 3, 5, 3}) returns <(1, 1), (3, 3), (5, 2)>
        HashMap<Object, Integer> b = new HashMap<>();
        for (Object o : l) {
            b.put(o, b.get(o) + 1);
        }
        return b;
    }
    
}
