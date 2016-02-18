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
package core.lib;

/**
 * Everything parallel
 * @author brown
 */
public class MultiThreading {
    
    public static int getCores() { //Returns processors in system
        return Runtime.getRuntime().availableProcessors();
    }
    
    public static long getAvailableMemory() { //non-allocated memory
        return Runtime.getRuntime().freeMemory();
    }
}
