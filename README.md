# Assignment 1: Number Formatting Converter  
**Course**: Introduction to Computer Science, Ariel University  
**Author**: Avital Even Ari, ID: 325310142  

## Overview  
A converter and calculator for numbers in `<number>b<base>` format (bases 2–16). Supports base conversions, operations, and validation.

## Key Functions  
- **`number2Int`**: Converts formatted strings to decimal. Returns `-1` for invalid inputs.  
- **`isNumber`**: Validates numbers in `<number>b<base>` format.  
- **`int2Number`**: Converts decimal numbers to specified bases.  
- **`maxIndex`**: Finds the largest number index in an array, ignoring invalid inputs.

## Examples  
- Valid: `Ab2 → 1010`, `123b16 → 7B`.  
- Invalid: `1bb2 → Error`, `1234b18 → Error`.  

## Testing  
Comprehensive tests are provided in `Ex1Test.java` for valid, invalid, and edge cases.



