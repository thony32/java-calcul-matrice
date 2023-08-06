// Code source de la classe MatriceSansThread

package com.test.tjava;
public class MatriceSansThread {
    public static double[][] multiplierMatrice(double[][] matrice1, double[][] matrice2) {
        int m = matrice1.length;
        int n = matrice2[0].length;
        int p = matrice2.length;

        if (matrice1[0].length != p) {
            throw new IllegalArgumentException("Dimensions de matrice non valides pour la multiplication.");
        }

        double[][] resultat = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0;

                for (int k = 0; k < p; k++) {
                    sum += matrice1[i][k] * matrice2[k][j];
                }

                resultat[i][j] = sum;
            }
        }

        return resultat;
    }
}
