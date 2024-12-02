
    package assignments.ex1;
    /**
     * This class represents a simple solution for Ex1.
     * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
     * In this assignment, we will design a number formatting converter and calculator.
     * In general, we will use Strings as numbers over basis of binary till Hexa.
     * [2-16], 10-16 are represented by A,B,..G.
     * The general representation of the numbers is as a String with the following format:
     * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
     * The following are NOT in the format (not a valid number):
     * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
     * You should implement the following static functions:
     */
    public class Ex1 {
        /**
         * Convert the given number (num) to a decimal representation (as int).
         * It the given number is not in a valid format returns -1.
         *
         * @param num a String representing a number in basis [2,16]
         * @return
         */
        public static int number2Int(String num) {
            if (num == null || num.isEmpty()) {
                return -1; // Invalid input
            }

            // Case: Plain decimal number (base 10)
            if (num.matches("-?\\d+")) {
                return Integer.parseInt(num); // Valid decimal number
            }

            String[] parts = num.split("b");
            if (parts.length == 1) { // Base 10 (default)
                if (isInvalidNumberForBase(parts[0], 10)) {
                    return -1;
                }
                return Integer.parseInt(parts[0]);
            } else if (parts.length == 2) { // Base specified
                String numberPart = parts[0];
                String basePart = parts[1];

                int base = validateAndConvertBase(basePart);
                if (base == -1) {
                    return -1; // Invalid base
                }

                if (isInvalidNumberForBase(numberPart, base)) {
                    return -1; // Invalid number for the base
                }

                int result = 0;
                for (char c : numberPart.toCharArray()) {
                    int digitValue = Character.isDigit(c) ? c - '0' : c - 'A' + 10;
                    result = result * base + digitValue;
                }

                return result;
            }

            return -1; // Invalid format
        }


        /**
         * Validates and converts a given string representation of a base into an integer.
         * A valid base is either:
         * - A digit between '2' and '9'.
         * - An uppercase letter between 'A' and 'G', representing bases 10 to 16.
         *
         * @param baseStr The string representation of the base to validate.
         * @return The integer value of the base if valid, or -1 if the base is invalid.
         */
        public static int validateAndConvertBase(String baseStr) {
            // Check for null or invalid length
            if (baseStr == null || baseStr.length() != 1) {
                return -1; // Invalid base
            }

            char baseChar = baseStr.charAt(0);

            // If the base is a digit (2–9)
            if (Character.isDigit(baseChar)) {
                int base = baseChar - '0';
                return (base >= 2 && base <= 9) ? base : -1; // Return only if within range 2–9
            }

            // If the base is an uppercase letter (A–G)
            if (baseChar >= 'A' && baseChar <= 'G') {
                int base = baseChar - 'A' + 10; // Convert 'A' to 10, 'B' to 11, ..., 'G' to 16
                return base; // Valid base between 10–16
            }

            return -1; // Invalid base
        }


        public static boolean isInvalidNumberForBase(String numberPart, int base) {
            if (numberPart == null || numberPart.isEmpty()) {
                return true; // Null or empty string is invalid
            }

            // Special case: If base is 10 and the number is a plain decimal
            if (base == 10 && numberPart.matches("-?\\d+")) {
                return false; // Valid decimal number
            }

            // Negative numbers are invalid for other bases
            if (numberPart.contains("-")) {
                return true;
            }

            // Check for invalid characters for the given base
            for (char c : numberPart.toCharArray()) {
                int digitValue;

                if (Character.isDigit(c)) {
                    digitValue = c - '0';
                } else if (Character.isLetter(c)) {
                    if (c < 'A' || c > 'G') {
                        return true; // Invalid character
                    }
                    digitValue = c - 'A' + 10;
                } else {
                    return true; // Invalid character
                }

                if (digitValue >= base) {
                    return true; // Digit exceeds the base
                }
            }

            return false; // Number is valid for the given base
        }




        /**
         * This static function checks if the given String (g) is in a valid "number" format.
         *
         * @param a a String representing a number
         * @return true iff the given String is in a number format
         */
        public static boolean isNumber(String a) {
            boolean ans = true;

            // Check if the string is null or empty
            if (a == null || a.isEmpty()) {
                ans = false;
            } else if (a.matches("\\d+")) { // Check if the string contains only digits (e.g., "1")
                ans = true; // It's a valid decimal number
            } else {
                // Split the string into the number part and the base part
                String[] parts = a.split("b");
                if (parts.length != 2) { // Must contain exactly one 'b'
                    ans = false;
                } else {
                    String numberPart = parts[0];
                    String basePart = parts[1];

                    // Validate and convert the base
                    int base = validateAndConvertBase(basePart);
                    if (base == -1) {
                        ans = false; // Invalid base
                    } else if (isInvalidNumberForBase(numberPart, base)) {
                        ans = false; // Invalid number for the base
                    }
                }
            }

            return ans;
        }


        /**
         * Calculate the number representation (in basis base)
         * of the given natural number (represented as an integer).
         * If num<0 or base is not in [2,16] the function should return "" (the empty String).
         *
         * @param num  the natural number (include 0).
         * @param base the basis [2,16]
         * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
         */
        public static String int2Number(int num, int base) {
            if (num < 0 || base < 2 || base > 16) {
                return ""; // Return an empty string for invalid input
            }

            StringBuilder numberPart = new StringBuilder();
            int temp = num;
            while (temp > 0) {
                int digit = temp % base;
                char digitChar = (digit < 10) ? (char) ('0' + digit) : (char) ('A' + digit - 10);
                numberPart.insert(0, digitChar);
                temp /= base;
            }

            if (numberPart.isEmpty()) {
                numberPart.append("0");
            }

            return numberPart + "b" + (base < 10 ? String.valueOf(base) : String.valueOf((char) ('A' + base - 10)));
        }




        /**
         * Checks if the two numbers have the same value.
         *
         * @param n1 first number
         * @param n2 second number
         * @return true iff the two numbers have the same values.
         */
        public static boolean equals(String n1, String n2) {
            boolean ans = true;
            int n1ToInt = number2Int(n1);// use the function above
            int n2ToInt = number2Int(n2);
            ans = (n1ToInt == n2ToInt);
            return ans;
        }

        /**
         * This static function search for the array index with the largest number (in value).
         * In case there are more than one maximum - returns the first index.
         * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
         *
         * @param arr an array of numbers
         * @return the index in the array in with the largest number (in value).
         */

        public static int maxIndex(String[] arr) {
            int index = -1; // Default value if no valid number is found
            int maxNum = Integer.MIN_VALUE; // Initialize with the smallest possible integer value
            for (int i = 0; i < arr.length; i++) {
                if (isValidNum(arr[i])) { // Check if the number is valid
                    int currentNum = Ex1.number2Int(arr[i]); // Use number2Int to parse the number
                    if (currentNum > maxNum) { // Update max number and its index
                        maxNum = currentNum;
                        index = i;
                    }
                }
            }
            return index;
        }


        public static boolean isValidNum(String str) {
            if (str == null || str.isEmpty()) {
                return false; // Null or empty strings are invalid
            }

            if (str.contains("b")) {
                String[] parts = str.split("b");
                if (parts.length != 2) return false; // Must have exactly one 'b'
                String numberPart = parts[0];
                String basePart = parts[1];

                // Validate base
                int base = Ex1.validateAndConvertBase(basePart);
                if (base == -1) return false;

                // Validate number for the base
                return !Ex1.isInvalidNumberForBase(numberPart, base);
            }

            // Default case: Decimal number
            if (str.charAt(0) == '-') { // Check for a negative sign at the beginning
                str = str.substring(1); // Remove the negative sign for further validation
            }
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) { // Non-digit characters make the string invalid
                    return false;
                }
            }
            return true; // Valid decimal number
        }


    }
