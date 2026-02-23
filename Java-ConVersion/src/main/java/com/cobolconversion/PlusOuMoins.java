package com.cobolconversion;

import java.util.Random;
import java.util.Scanner;

/**
 * Converted from: plusoumoins.cbl
 * Original Author: Michael Pirali
 * Date: 2019/10/13
 * Purpose: Number guessing game - guess a number between 1 and 100
 */
public class PlusOuMoins {
    
    // ANSI color codes for terminal output (simulating COBOL screen colors)
    private static final String RED_BG = "\033[41m\033[30m";
    private static final String WHITE_BG = "\033[47m\033[31m";
    private static final String RESET = "\033[0m";
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            
            // Generate random number between 1 and 100
            int randNumber = random.nextInt(100) + 1;
            int userNumber = 0;
            int attempts = 0;
            
            // Clear screen and display instructions
            clearScreen();
            System.out.println("          Veuillez entrer un nombre compris entre 1 et 100 :");
            System.out.println();
            
            // Game loop
            while (userNumber != randNumber) {
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
                        continue;
                    }
                    
                    // Check guess
                    if (userNumber < randNumber) {
                        System.out.println(WHITE_BG + "          Votre nombre est trop petit           " + RESET);
                    } else if (userNumber > randNumber) {
                        System.out.println(RED_BG + "          Votre nombre est trop grand           " + RESET);
                    } else {
                        // Winner!
                        System.out.println("          B I N G O             ");
                        System.out.println();
                        System.out.printf("          Vous avez trouvé en %2d essais !%n", attempts);
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("          Veuillez entrer un nombre valide");
                }
            }
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
