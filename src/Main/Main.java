/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.util.Scanner;

/**
 *
 * @author Jimmy Silva Luna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingresa la dimension del tablero: ");
        int n = 8;
        int numeroDeSoluciones = 0;
        int[][] tablero = new int[n][n];
        int reg = 0;
        
        // Calcula el primer tablero
//        Algoritmo.resolver(tablero, reg);
//        
//        Algoritmo.imprimirTablero(tablero);
        
        // Calcula todas las posibles soluciones
        numeroDeSoluciones = Algoritmo.resultadosPosibles(tablero, reg, numeroDeSoluciones);
        
        System.out.println("Para n = " + n + "\nNumero de soluciones posibles -> " + numeroDeSoluciones);
        
    }
    
}
