package assignments.ex1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very partial test class for Ex1.
 * Make sure you complete all the needed JUnits
 */
public class Ex1Test {

    // this tests are for external functions
    @Test
    void validateAndConvertBaseTest() {
        //check for good bases
        assertEquals(2, Ex1.validateAndConvertBase("2"));
        assertEquals(10, Ex1.validateAndConvertBase("A"));
        assertEquals(16, Ex1.validateAndConvertBase("G"));
        //check for invalid bases
        assertEquals(-1, Ex1.validateAndConvertBase("1"));
        assertEquals(-1, Ex1.validateAndConvertBase("H"));
        assertEquals(-1, Ex1.validateAndConvertBase(""));
        assertEquals(-1, Ex1.validateAndConvertBase(null));
        assertEquals(-1, Ex1.validateAndConvertBase("10")); // need to be A
        assertEquals(-1, Ex1.validateAndConvertBase("-1"));
        assertEquals(-1, Ex1.validateAndConvertBase("300"));
        assertEquals(-1, Ex1.validateAndConvertBase("a"));
    }

    @Test
    void isInvalidNumberForBaseTest() {
        //check if the number is valid for the base
        assertFalse(Ex1.isInvalidNumberForBase("1011", 2));
        assertFalse(Ex1.isInvalidNumberForBase("123", 10));
        assertFalse(Ex1.isInvalidNumberForBase("AB", 16));
        assertFalse(Ex1.isInvalidNumberForBase("0", 10));

        assertTrue(Ex1.isInvalidNumberForBase("102", 2));  // 2 is invalid for base 2
        assertTrue(Ex1.isInvalidNumberForBase("1G3", 16)); // G is invalid for base 16
        assertTrue(Ex1.isInvalidNumberForBase("", 10));
        assertTrue(Ex1.isInvalidNumberForBase(null, 10));
        assertTrue(Ex1.isInvalidNumberForBase("Z1", 16));
        assertTrue(Ex1.isInvalidNumberForBase("-123", 4));
        assertTrue(Ex1.isInvalidNumberForBase("1a23", 16));
    }


    @Test
    void computeNumberTest() {
        String s2 = "1011b2";
        int v = Ex1.number2Int(s2); //11
        assertEquals(v,11);
        String s10 = "1011bA"; //1011
        v = Ex1.number2Int(s10); //1011
        s2 = Ex1.int2Number(v,2); //1111110011 == 1011b2
        int v2 = Ex1.number2Int(s2); //1011
        assertEquals(v,v2);
        assertTrue(Ex1.equals(s10,s2));// equals is our function
    }

    @Test
    void computeNumberEdgeCasesTest() {
        //  we want to see that the number2Int and Int2Number return the number to be the source number
        //case 1:
        String s2 = "1b2"; // Binary "1"
        int v = Ex1.number2Int(s2); // 1
        assertEquals(v, 1);
        s2 = Ex1.int2Number(v, 2); // 1b2
        assertEquals(s2, "1b2");

        // Case 2:
        String s16 = "FFFFbG"; // Hexadecimal "FFFF"
        v = Ex1.number2Int(s16); // 65535
        assertEquals(v, 65535);
        String converted = Ex1.int2Number(v, 16); // FFFFbG
        assertEquals(converted, "FFFFbG");

        // Case 3:
        String s8 = "00007b8"; // Octal "7"
        v = Ex1.number2Int(s8); // 7
        assertEquals(v, 7);
        s8 = Ex1.int2Number(v, 8); // 7b8
        assertEquals(s8, "7b8");

        // Case 4:
        String s10 = "12345bA"; // Decimal "12345"
        v = Ex1.number2Int(s10); // 12345
        assertEquals(v, 12345);
        String binary = Ex1.int2Number(v, 2); // Binary form
        v = Ex1.number2Int(binary); // Back to decimal
        assertEquals(v, 12345);

        // Case 5:
        String sMaxBase = "1bG"; // Base 16 "1"
        v = Ex1.number2Int(sMaxBase); // 1
        assertEquals(v, 1);
        sMaxBase = Ex1.int2Number(v, 16); // 1bG
        assertEquals(sMaxBase, "1bG");

    }

    @Test
    void isBasisNumberTest() {
        String[] good = {"1", "1b2", "01b2", "123bA", "ABbG", "0bA"};
        for(int i=0;i<good.length;i=i+1) {
            boolean ok = Ex1.isNumber(good[i]);
            assertTrue(ok);
        }
        String[] not_good = {"b2", "2b2", "1G3bG", " BbG", "0bbA", "abB", "!@b2", "A", "1bb2"};
        for(int i=0;i<not_good.length;i=i+1) {
            boolean not_ok = Ex1.isNumber(not_good[i]);
            assertFalse(not_ok);
        }
    }

    @Test
    void int2NumberTest() {

        for (int base = 2; base <= 16; base++) {
            String baseStr = base < 10 ? String.valueOf(base) : String.valueOf((char) ('A' + base - 10));
            String result = Ex1.int2Number(0, base);
            assertEquals("0b" + baseStr, result, "Failed for base " + base);
            assertEquals(0, Ex1.number2Int(result), "number2Int failed for base " + base);
        }


        for (int base = 2; base <= 16; base++) {
            String baseStr = base < 10 ? String.valueOf(base) : String.valueOf((char) ('A' + base - 10));
            String result = Ex1.int2Number(1, base);
            assertEquals("1b" + baseStr, result, "Failed for base " + base);
            assertEquals(1, Ex1.number2Int(result), "number2Int failed for base " + base);
        }


        // Case 3:
        String hex = Ex1.int2Number(255, 16);
        assertEquals("FFbG", hex, "Failed for hexadecimal conversion of 255");
        assertEquals(255, Ex1.number2Int(hex), "number2Int failed for hexadecimal 255");

        // Case 4:
        String numInBase10 = Ex1.int2Number(123, 10); // "123bA"
        String numInBase2 = Ex1.int2Number(123, 2);  // "1111011b2"
        assertTrue(Ex1.equals(numInBase10, numInBase2), "Failed equality test for 123 in base 10 and base 2");// our equals

        //case 5
        assertEquals("", Ex1.int2Number(16, 17));
        assertEquals("", Ex1.int2Number(1, 1));
        assertEquals("", Ex1.int2Number(-1, 10));
    }

    @Test
    void isValidNumTest() {
        // Case 1: Valid numbers
        assertTrue(Ex1.isValidNum("123"), "Failed for valid number");
        assertTrue(Ex1.isValidNum("0"), "Failed for zero");
        assertTrue(Ex1.isValidNum("000123"), "Failed for number with leading zeros");

        // Case 2: Invalid numbers
        assertFalse(Ex1.isValidNum("123a"), "Failed for string with letters");
        assertFalse(Ex1.isValidNum("a123"), "Failed for string with letters at the start");
        assertFalse(Ex1.isValidNum("12.3"), "Failed for decimal number");
        assertFalse(Ex1.isValidNum(""), "Failed for empty string");
        assertFalse(Ex1.isValidNum(null), "Failed for null");

        // Case 3: Special characters
        assertFalse(Ex1.isValidNum("123!"), "Failed for number with special characters");
        assertFalse(Ex1.isValidNum("12@3"), "Failed for number with special characters in between");
    }

    @Test
    void maxIndexTest() {
        // Case 1: Valid numbers only
        String[] arr1 = {"1", "23", "45", "67", "89"};
        assertEquals(4, Ex1.maxIndex(arr1), "Failed for valid numbers");

        // Case 2: Array with invalid numbers
        String[] arr2 = {"abc", "23", "45a", "67", "89", "100"};
        assertEquals(5, Ex1.maxIndex(arr2), "Failed with invalid numbers in the array");

        // Case 3: Empty array
        String[] arr3 = {};
        assertEquals(-1, Ex1.maxIndex(arr3), "Failed for empty array");

        // Case 4: All invalid numbers
        String[] arr4 = {"abc", "def", "45a"};
        assertEquals(-1, Ex1.maxIndex(arr4), "Failed for array with only invalid numbers");

        // Case 5: Single valid number
        String[] arr5 = {"123"};
        assertEquals(0, Ex1.maxIndex(arr5), "Failed for single valid number");

        // Case 6: Null array
        String[] arr6 = null;
        assertThrows(NullPointerException.class, () -> Ex1.maxIndex(arr6), "Failed for null array");

        // Case 7: Numbers with leading zeros
        String[] arr7 = {"001", "23", "0045", "00067"};
        assertEquals(3, Ex1.maxIndex(arr7), "Failed for numbers with leading zeros");

        // Case 8: Array with negative numbers (treated as invalid)
        String[] arr8 = {"-1", "23", "-45", "67"};
        assertEquals(3, Ex1.maxIndex(arr8), "Failed for array with negative numbers treated as invalid");
    }

    @Test
    void equalsTest() {
        // Case 1: Equal positive numbers
        assertTrue(Ex1.equals("123", "123"), "Failed for equal positive numbers");

        // Case 2: Equal negative numbers
        assertTrue(Ex1.equals("-123", "-123"), "Failed for equal negative numbers");

        // Case 3: One positive and one negative (not equal)
        assertFalse(Ex1.equals("123", "-123"), "Failed for positive and negative numbers");

        // Case 4: Equal numbers with leading zeros
        assertTrue(Ex1.equals("00123", "123"), "Failed for numbers with leading zeros");

        // Case 5: Different numbers
        assertFalse(Ex1.equals("123", "456"), "Failed for different numbers");

        // Case 6: Invalid numbers
        assertFalse(Ex1.equals("123a", "123"), "Failed for invalid number in first parameter");
        assertFalse(Ex1.equals("123", "456bA"), "Failed for invalid number in second parameter");


        // Case 8: One null
        assertFalse(Ex1.equals(null, "123"), "Failed when first parameter is null");
        assertFalse(Ex1.equals("123", null), "Failed when second parameter is null");


        // Case 10: Equal formatted numbers
        assertTrue(Ex1.equals("123bA", "123"), "Failed for equal formatted numbers");
        assertTrue(Ex1.equals("1111011b2", "123"), "Failed for equal binary and decimal numbers");
    }

}