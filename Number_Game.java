package personal;
import java.util.Random;
import java.util.Scanner;

public class Number_Game 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int score = 0;

        System.out.println("Welcome to the Guess the Number game!");

        while (playAgain) 
        {
            int lowerLimit = 1;
            int upperLimit = 100;
            int targetNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            int attempts = 0;
            int maxAttempts = 10; 
            System.out.println("I'm thinking of a number between " + lowerLimit + " and " + upperLimit + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) 
            {
                System.out.print("Enter your guess: ");
                int userGuess;
                try 
                {
                    userGuess = scanner.nextInt();
                } 
                catch (java.util.InputMismatchException e) 
                {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); 
                    continue;
                }

                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number " + targetNumber + " in " + attempts + " attempts.");
                    score++;
                    break;
                } else if (userGuess < targetNumber)
                {
                    System.out.println("Too low. Try again.");
                } 
                else 
                {
                    System.out.println("Too high. Try again.");
                }
            }

            if (attempts >= maxAttempts)
            {
                System.out.println("Out of attempts. The correct number was " + targetNumber + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            if (!playAgainInput.equals("yes")) 
            {
                System.out.println("Your final score is " + score + " round(s) won.");
                playAgain = false;
            }
        }
        
        //scanner.close();
    }
}
