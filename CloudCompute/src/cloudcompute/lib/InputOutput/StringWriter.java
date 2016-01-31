package cloudcompute.lib.InputOutput;

/*
 * Copyright (C) ChemicalDevelopment 2016
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brown
 */
public class StringWriter extends Thread {

    private BufferedWriter bw;

    public StringWriter(BufferedWriter _bw) {
        bw = _bw;
    }

    public void add(String _s) throws IOException {
        bw.append(_s);
    }

    @Override
    public void run() {
        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(StringWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
