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
package cloudcompute.lib.math.DataStructure;

import cloudcompute.lib.InputOutput.DiskSwap;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Formatter;

/**
 * This class is meant to be used a DataStructure for fast internal computing.
 *
 * @author brown
 */
public class BigInt {

    public final static int BASE_DECIMAL_DIGITS = 9;
    public final static int BASE = (int) Math.pow(10, BASE_DECIMAL_DIGITS);

    private int[] digits;

    /*
    Creates a BigInt by using any sort of group of integers (to represent 9 digits at a time)
     */
    public BigInt(int... d) {
        this.digits = d.clone();
    }

    /*
    Creates an equivelant BigInteger
     */
    public BigInteger toBigInteger() {
        return new BigInteger(this.toDecimalString());
    }

    /*
    Prints out a comma-separated string of digits
     */
    @Override
    public String toString() {
        return Arrays.toString(digits);
    }

    /*
    Just give it a string, and it converts to our format
     */
    public BigInt fromDecimal(String decimal) {
        int decLen = decimal.length();
        int bigLen = (decLen - 1) / BASE_DECIMAL_DIGITS + 1;

        int firstSome = decLen - (bigLen - 1) * BASE_DECIMAL_DIGITS;
        int[] digits = new int[bigLen];

        for (int i = 0; i < bigLen; i++) {

            String block = decimal.substring(Math.max(firstSome + (i - 1) * BASE_DECIMAL_DIGITS, 0), firstSome + i * BASE_DECIMAL_DIGITS);
            digits[i] = Integer.parseInt(block);
        }
        return new BigInt(digits);

    }

    /*
    Creates a string with no commas, just a plain numeric string
     */
    public String toDecimalString() {
        Formatter f = new Formatter();
        for (int digit : digits) {
            f.format("%09d", digit);
        }
        return f.toString();
    }

    /*
    Adds two BigInt s
     */
    public BigInt plus(BigInt operand) {
        int[] result = new int[Math.max(this.digits.length, operand.digits.length) + 1];

        addDigits(result, result.length - 1, this.digits);
        addDigits(result, result.length - 1, operand.digits);

        if (result[0] == 0) {
            result = Arrays.copyOfRange(result, 1, result.length);
        }
        return new BigInt(result);
    }

    /*
    Adds a specific digit to one index in the digits, with overflow protection
     */
    public void addDigit(int[] result, int resultIndex, int addendDigit) {
        int sum = result[resultIndex] + addendDigit;
        result[resultIndex] = sum % BASE;
        int carry = sum / BASE;
        if (carry > 0) {
            addDigit(result, resultIndex - 1, carry);
        }
    }

    /*
    Can add a group of integers to the BigInt
     */
    public void addDigits(int[] result, int resultIndex, int... addend) {
        int addendIndex = addend.length - 1;
        while (addendIndex >= 0) {
            addDigit(result, resultIndex,
                    addend[addendIndex]);
            addendIndex--;
            resultIndex--;
        }
    }

    /*
    Multiplies two BigInt s
     */
    public BigInt times(BigInt operand) {
        int[] result = new int[this.digits.length + operand.digits.length];
        multiplyDigits(result, result.length - 1, this.digits, operand.digits);

        if (result[0] == 0) {
            result = Arrays.copyOfRange(result, 1, result.length);
        }
        return new BigInt(result);
    }

    /*
    Multiplies one digit
     */
    public void multiplyDigit(int[] result, int resultIndex, int firstFactor, int secondFactor) {
        long prod = (long) firstFactor * (long) secondFactor;
        int prodDigit = (int) (prod % BASE);
        int carry = (int) (prod / BASE);
        addDigits(result, resultIndex, carry, prodDigit);
    }

    /*
    Multiplies a set of digits
     */
    private void multiplyDigits(int[] result, int resultIndex, int[] leftFactor, int[] rightFactor) {
        for (int i = 0; i < leftFactor.length; i++) {
            for (int j = 0; j < rightFactor.length; j++) {

                multiplyDigit(result, resultIndex - (i + j),
                        leftFactor[leftFactor.length - i - 1],
                        rightFactor[rightFactor.length - j - 1]);
            }
        }
    }

    public DiskSwap store(String path) throws IOException {
        DiskSwap p = new DiskSwap(path);
        for (int i : digits) {
            p.store("" + i);
        }
        return p;
    }
    

}