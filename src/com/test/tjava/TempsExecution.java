package com.test.tjava;
import java.util.Arrays;
import java.util.Scanner;

public class TempsExecution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez entrer les dimensions de la première matrice (m x p):");
        int m1 = scanner.nextInt();
        int p1 = scanner.nextInt();

        System.out.println("Veuillez entrer les dimensions de la deuxième matrice (p x n):");
        int p2 = scanner.nextInt();
        int n2 = scanner.nextInt();

        double[][] matrice1 = new double[m1][p1];
        double[][] matrice2 = new double[p2][n2];

        try {
            System.out.println("Veuillez entrer les valeurs de la première matrice :");
            entrerValeurMatrice(scanner, matrice1);

            System.out.println("Veuillez entrer les valeurs de la deuxième matrice :");
            entrerValeurMatrice(scanner, matrice2);

            long debutExecution = System.currentTimeMillis();
            double[][] resultatThread = MatriceAvecThread.multiplierMatrice(matrice1, matrice2);
            long finExecution = System.currentTimeMillis();
            long tempsExecutionThread = finExecution - debutExecution;

            debutExecution = System.currentTimeMillis();
            double[][] resultatSansThread = MatriceSansThread.multiplierMatrice(matrice1, matrice2);
            finExecution = System.currentTimeMillis();
            long tempsExecutionSansThread = finExecution - debutExecution;

            System.out.println("Résultat avec Thread:");
            afficherMatrice(resultatThread);

            System.out.println("\nRésultat sans Thread:");
            afficherMatrice(resultatSansThread);

            System.out.println("\nTemps d'exécution avec Thread: " + tempsExecutionThread + " ms");
            System.out.println("Temps d'exécution sans Thread: " + tempsExecutionSansThread + " ms");

            if (tempsExecutionThread < tempsExecutionSansThread) {
                System.out.println("\nLe code avec Thread est plus rapide.");
            } else if (tempsExecutionThread > tempsExecutionSansThread) {
                System.out.println("\nLe code sans Thread est plus rapide.");
            } else {
                System.out.println("\nLes deux codes s'exécutent en même temps.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Erreur : Les valeurs de la matrice doivent être de type double.");
        }
    }

    private static void entrerValeurMatrice(Scanner scanner, double[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print("Valeur à la position [" + i + "][" + j + "]: ");
                matrice[i][j] = scanner.nextDouble();
            }
        }
    }

    private static void afficherMatrice(double[][] matrice) {
        for (double[] ligne : matrice) {
            System.out.println(Arrays.toString(ligne));
        }
    }
}
