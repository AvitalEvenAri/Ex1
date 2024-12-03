package assignments.ex1;

import java.util.Scanner;

public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a scanner object for user input
        String num1 = "", num2 = "", quit = "quit"; // Initialize variables, including a keyword for quitting

        // Main loop for user interaction
        while (!num1.equals(quit) && !num2.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");

            // Input the first number
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next(); // Read the first number from the user
            if (num1.equals(quit)) break; // Exit if the user types "quit"

            // Input the second number
            System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
            num2 = sc.next(); // Read the second number from the user
            if (num2.equals(quit)) break; // Exit if the user types "quit"

            // Convert both numbers to integers using Ex1.number2Int
            int n1 = Ex1.number2Int(num1); // Convert num1 to an integer
            int n2 = Ex1.number2Int(num2); // Convert num2 to an integer

            // Check if either number is invalid
            if (n1 == -1 || n2 == -1) {
                System.out.println("One or both of the numbers are invalid.");
                System.out.println("num1= " + num1 + " is number: " + (n1 != -1) + " , value: " + n1);
                System.out.println("num2= " + num2 + " is number: " + (n2 != -1) + " , value: " + n2);
                continue; // Skip to the next iteration
            }

            // Display the valid numbers
            System.out.println("num1= " + num1 + " is number: true , value: " + n1);
            System.out.println("num2= " + num2 + " is number: true , value: " + n2);

            // Ask the user for a base to display the output
            System.out.println("Enter a base for output: (a number [2,16])");
            int base = sc.nextInt(); // Read the base input

            // Validate the base
            if (base < 2 || base > 16) {
                System.out.println("Invalid base. Please enter a base between 2 and 16.");
                continue; // Skip to the next iteration
            }

            // Calculate the sum and product of the two numbers
            int sum = n1 + n2; // Calculate the sum
            int product = n1 * n2; // Calculate the product

            // Convert the sum and product to the selected base
            String sumBase = Ex1.int2Number(sum, base); // Convert the sum to the chosen base
            String productBase = Ex1.int2Number(product, base); // Convert the product to the chosen base

            // Display the sum and product in both decimal and the selected base
            System.out.println(num1 + " + " + num2 + " = " + sum);
            System.out.println(num1 + " * " + num2 + " = " + product);

            // Prepare an array containing the numbers for comparison
            String[] numbers = {num1, num2, sumBase, productBase};

            // Find the index of the maximum number in the array
            int maxIndex = Ex1.maxIndex(numbers);
            if (maxIndex == -1) {
                // If no valid numbers are found, display a message
                System.out.println("No valid numbers to determine the maximum.");
            } else {
                // Convert the maximum number to decimal and display it
                int maxValue = Ex1.number2Int(numbers[maxIndex]);
                System.out.println("Max number over " + String.join(",", numbers) + " is: " + maxValue);
            }
        }

        // Exit message
        System.out.println("quiting now...");
        sc.close(); // Close the scanner to release resources
    }
}
