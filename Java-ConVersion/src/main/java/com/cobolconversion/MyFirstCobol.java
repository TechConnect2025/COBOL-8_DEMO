package com.cobolconversion;

import java.util.Scanner;

/**
 * Converted from: myfirstcobol.cbl
 * Original Author: Michael Pirali
 * Date: 2019/10/13
 * Purpose: Interactive name greeting program
 */
public class MyFirstCobol {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Clear screen simulation (BLANK SCREEN in COBOL)
            System.out.println("\n\n\n\n\n");
            
            // Display title
            System.out.println("          Hey !");
            System.out.println();
            
            // Prompt for name
            System.out.print("          Quel est ton nom ? ");
            String nom = scanner.nextLine().trim();
            
            // Validate that name is not empty (REQUIRED in COBOL)
            while (nom.isEmpty()) {
                System.out.print("          Quel est ton nom ? ");
                nom = scanner.nextLine().trim();
            }
            
            // Display greeting
            System.out.println();
            System.out.println("          Salut " + nom);
            System.out.println(".");
        }
    }
}
