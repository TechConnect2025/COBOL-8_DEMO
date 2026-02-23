package com.cobolconversion;

import java.util.Random;
import java.util.Scanner;

/**
 * Converted from: plusoumoins2.cbl
 * Original Author: Michael Pirali
 * Date: 2019/10/13
 * Purpose: Enhanced number guessing game with replay functionality
 */
public class PlusOuMoins2 {
    
    // ANSI color codes for terminal output (simulating COBOL screen colors)
    private static final String RED_BG = "\033[41m\033[30m";
    private static final String WHITE_BG = "\033[47m\033[31m";
    private static final String RESET = "\033[0m";
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            String playAgain = "";
            
            // Main game loop - continue until player chooses not to play
            do {
                playGame(scanner, random);
                
                // Ask if player wants to play again
                System.out.println();
                playAgain = "";
                
                while (!playAgain.equalsIgnoreCase("o") && 
                       !playAgain.equalsIgnoreCase("n")) {
                    System.out.print("          Voulez-vous rejouer ? O/N ");
                    playAgain = scanner.nextLine().trim();
                    
                    if (playAgain.isEmpty()) {
                        continue; // REQUIRED field in COBOL
                    }
                }
                
            } while (playAgain.equalsIgnoreCase("o"));
            
            System.out.println("\n          Merci d'avoir joué !");
        }
    }
    
    private static void playGame(Scanner scanner, Random random) {
        // Generate random number between 1 and 100
        int randNumber = random.nextInt(100) + 1;
        int userNumber = 0;
        int attempts = 0;
        
        // Clear screen and display instructions
        clearScreen();
        System.out.println("          Veuillez entrer un nombre compris entre 1 et 100 :");
        System.out.println();
        
        // Game loop for this round
        while (userNumber != randNumber) {
            userNumber = 0; // Reset for each attempt
            attempts++;
            
            // Prompt for input
            System.out.print("          Nombre : ");
            
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue; // REQUIRED field in COBOL
                }
                userNumber = Integer.parseInt(input);
                
                // Validate range
                if (userNumber < 1 || userNumber > 100) {
                    System.out.println("          Veuillez entrer un nombre entre 1 et 100");
                    userNumber = 0;
                    continue;
                }
                
                // Check guess
                if (userNumber < randNumber) {
                    System.out.println(WHITE_BG + "          Votre nombre est trop petit           " + RESET);
                } else if (userNumber > randNumber) {
                    System.out.println(RED_BG + "          Votre nombre est trop grand           " + RESET);
                } else {
                    // Winner!
                    displayWinMessage(attempts);
                }
                
            } catch (NumberFormatException e) {
                System.out.println("          Veuillez entrer un nombre valide");
                userNumber = 0;
            }
        }
    }
    
    private static void displayWinMessage(int attempts) {
        System.out.println("          B I N G O             ");
        System.out.println();
        
        if (attempts == 1) {
            // Special message for first try (lucky!)
            System.out.println("          Vous avez trouvé du premier coup !!");
        } else {
            // Normal message with attempt count
            // Adjust column position based on number of digits (like COBOL iCola, iColb)
            String attemptsStr = String.format("%2d", attempts);
            System.out.println("          Vous avez trouvé en " + attemptsStr + " essais !");
        }
    }
    
    private static void clearScreen() {
        // BLANK SCREEN in COBOL
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // Alternative for systems that don't support ANSI codes:
        System.out.println("\n\n\n");
    }
}
