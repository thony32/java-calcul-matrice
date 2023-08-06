// Code source de la classe MatriceAvecThread

package com.test.tjava;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatriceAvecThread {
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    public static double[][] multiplierMatrice(double[][] matrice1, double[][] matrice2) throws InterruptedException {
        int m = matrice1.length;
        int n = matrice2[0].length;
        int p = matrice2.length;

        if (matrice1[0].length != p) {
            throw new IllegalArgumentException("Dimensions de matrice non valides pour la multiplication.");
        }

        double[][] resultat = new double[m][n];

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Runnable task = new processusMultiplication(matrice1, matrice2, resultat, i, j);
                executor.execute(task);
            }
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Attente de la fin de l'exécution de tous les threads
        }

        return resultat;
    }

    private static class processusMultiplication implements Runnable {
        private final double[][] matrice1;
        private final double[][] matrice2;
        private final double[][] resultat;
        private final int ligne;
        private final int colonne;

        public processusMultiplication(double[][] matrice1, double[][] matrice2, double[][] resultat, int ligne, int colonne) {
            this.matrice1 = matrice1;
            this.matrice2 = matrice2;
            this.resultat = resultat;
            this.ligne = ligne;
            this.colonne = colonne;
        }

        @Override
        public void run() {
            int p = matrice2.length;
            double sum = 0;

            for (int k = 0; k < p; k++) {
                sum += matrice1[ligne][k] * matrice2[k][colonne];
            }

            resultat[ligne][colonne] = sum;
        }
    }
}

