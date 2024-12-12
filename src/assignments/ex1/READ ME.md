# Ex1 - Number Formatting Converter

## Author
- **ID**: 325310142

## Overview
This project implements a number formatting converter and calculator for bases 2 to 16, using the `<number>b<base>` format. It supports base conversions, numerical operations, and validation for formatted numbers.

## Functions
- **`number2Int`**: Converts formatted strings to decimal. Returns `-1` for invalid inputs.
- **`isNumber`**: Validates the `<number>b<base>` format for bases 2 to 16.
- **`int2Number`**: Converts decimal numbers to a specified base. Returns an empty string if the input is invalid.
- **`equals`**: Placeholder function (raises an exception as per assignment instructions).
- **`maxIndex`**: Finds the index of the largest number in an array, ignoring invalid numbers.

## Testing
Comprehensive unit tests are provided in `Ex1Test.java`, covering valid inputs, invalid inputs, and edge cases for all functions.

Valid examples:
1011b2 → Binary.
A23bA → Decimal.
FFbG → Hexadecimal.
Invalid examples:
1bb2 → Multiple bs.
1234b18 → Base outside the range 2–16.
-1011b2 → Negative numbers.

---
