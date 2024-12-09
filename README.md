# Ex1 - Number Format Converter and Calculator

## Introduction
This project is a simple tool for converting and performing calculations on numbers in different bases, ranging from binary (base 2) to hexadecimal (base 16). The project includes functionality for validating numbers, performing arithmetic operations, and finding the largest number among a group of inputs. It also includes a command-line program for interactive use.

---

## Features
- **Base Conversion**:
  - Convert numbers between decimal and bases from 2 to 16.
- **Validation**:
  - Validate the format of numbers and their bases.
- **Arithmetic Operations**:
  - Perform addition and multiplication on numbers in various bases.
- **Maximum Finder**:
  - Identify the largest number among a set of inputs.
- **Interactive CLI**:
  - Use a command-line interface to input numbers, perform calculations, and view results.

---

## File Structure

## How to Use

### Core Functions (`Ex1.java`)
This file contains the core logic of the project:
- `number2Int(String num)`: Converts a number from any base (2-16) to its decimal value.
- `int2Number(int num, int base)`: Converts a decimal number to a specified base.
- `validateAndConvertBase(String baseStr)`: Validates a base and converts it to an integer.
- `isInvalidNumberForBase(String numberPart, int base)`: Checks if a number is invalid for a given base.
- `isNumber(String a)`: Validates if a string follows the `<number>b<base>` format.
- `maxIndex(String[] arr)`: Finds the largest number in an array.
- `equals(String n1, String n2)`: Checks if two numbers (in different bases) are equivalent.

### Test Functions (`Ex1Test.java`)
This file contains tests for all the implemented methods. Key tests include:
- Validating base conversion.
- Checking if numbers are correctly converted between bases.
- Verifying arithmetic operations (addition and multiplication).
- Handling invalid inputs like null values or unsupported formats.

Run the tests using JUnit in your Java IDE.

### Interactive CLI (`Ex1Main.java`)
The `Ex1Main` program allows you to input numbers and bases interactively and perform operations.

#### Steps to Run:
1. Run the `Ex1Main` class in your IDE.
2. Follow the prompts to input two numbers and specify a base.
3. The program will display:
   - Addition and multiplication results in both decimal and the selected base.
   - The largest number among the inputs and calculated results.


## Input Format
Numbers must follow the format `<number>b<base>`, where:
- `<number>` is the numeric value (e.g., `1011`, `FF`, `123`).
- `<base>` is the base of the number (e.g., `2`, `A` for base 10, `G` for base 16).
- Valid examples:
  - `1011b2` → Binary.
  - `A23bA` → Decimal.
  - `FFbG` → Hexadecimal.
- Invalid examples:
  - `1bb2` → Multiple `b`s.
  - `1234b18` → Base outside the range 2–16.
  - `-1011b2` → Negative numbers.


