package assignments.ex1;

import java.util.Scanner;

public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = "", num2 = "", quit = "quit";

        while (!num1.equals(quit) && !num2.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");

            // Input number #1
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();
            if (num1.equals(quit)) break;

            // Input number #2
            System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
            num2 = sc.next();
            if (num2.equals(quit)) break;

            // Validate both numbers
            int n1 = Ex1.number2Int(num1);
            int n2 = Ex1.number2Int(num2);

            if (n1 == -1 || n2 == -1) {
                System.out.println("One or both of the numbers are invalid.");
                System.out.println("num1= " + num1 + " is number: " + (n1 != -1) + " , value: " + n1);
                System.out.println("num2= " + num2 + " is number: " + (n2 != -1) + " , value: " + n2);
                continue;
            }

            System.out.println("num1= " + num1 + " is number: true , value: " + n1);
            System.out.println("num2= " + num2 + " is number: true , value: " + n2);

            // Input base for output
            System.out.println("Enter a base for output: (a number [2,16])");
            int base = sc.nextInt();

            if (base < 2 || base > 16) {
                System.out.println("Invalid base. Please enter a base between 2 and 16.");
                continue;
            }

            // Perform calculations
            int sum = n1 + n2;
            int product = n1 * n2;

            // Convert results to the selected base
            String sumBase = Ex1.int2Number(sum, base);
            String productBase = Ex1.int2Number(product, base);

            // Display results
            System.out.println(num1 + " + " + num2 + " = " + sum);
            System.out.println(num1 + " * " + num2 + " = " + product);

            // Prepare array for maxIndex function
            String[] numbers = {num1, num2, sumBase, productBase};

            // Find and display the maximum number
            int maxIndex = Ex1.maxIndex(numbers);
            if (maxIndex == -1) {
                System.out.println("No valid numbers to determine the maximum.");
            } else {
                int maxValue = Ex1.number2Int(numbers[maxIndex]); // Convert to decimal
                System.out.println("Max number over " + String.join(",", numbers) + " is: " + maxValue);
            }
        }

        System.out.println("quiting now...");
        sc.close();
    }
}
