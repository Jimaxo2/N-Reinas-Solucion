/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author User
 */
public class Algoritmo {
    
    public static int soluciones = 0;
    
    /**
     * Este metodo se encarga de imprimir el tablero sin importar su tamaño
     * @param tablero
     */
    public static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
    
    /**
     * Este metodo revisara si en alguna de las direcciones (diagonales, filas o columnas)
     * existe algun espacio ocupado
     */
    public static boolean revisarDirecciones(int[][] tablero, int reg, int col) {
        
        int n = tablero.length;

        // Revisar fila (horizontal)
        for (int i = 0; i < n; i++) {
            if (i != col && tablero[reg][i] == 1) {
                return true;
            }
        }

        // Revisar columna (vertical)
        for (int i = 0; i < n; i++) {
            if (i != reg && tablero[i][col] == 1) {
                return true;
            }
        }

        // Revisar diagonal negativa hacia enfrente
        for (int i = 1; reg+i < n && col+i < n; i++) {
            if (tablero[reg+i][col+i] == 1) {
                return true;
            }
        }

        // Revisar diagonal negativa hacia atras
        for (int i = 1; reg-i >= 0 && col-i >= 0; i++) {
            if (tablero[reg-i][col-i] == 1) {
                return true;
            }
        }

        // Revisar diagonal positiva hacia atras
        for (int i = 1; reg+i < n && col-i >= 0; i++) {
            if (tablero[reg+i][col-i] == 1) {
                return true;
            }
        }

        // Revisar diagonal positiva hacia enfrente
        for (int i = 1; reg-i >= 0 && col+i < n; i++) {
            if (tablero[reg-i][col+i] == 1) {
                return true;
            }
        }
        
        // Si no esta ocupado de ninguna forma regresa falso
        return false;
    }
    
    /**
     * Este metodo determina si el tablero ya esta lleno,
     * si lo esta devuelve true, si no lo esta devuelve
     * false
     */
    public static boolean tableroLleno(int tablero[][]) {
        int numUnos = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == 1) {
                    numUnos++;
                }
            }
        }
        if (numUnos == tablero.length) {
            return true;
        }
        return false;
    }
        
    /**
     * Este metodo busca si la fila seleccionada puede colocarse un 1
     */
    public static boolean filaDisponible(int tablero[][], int reg) {
        int vacio = 0;
        for (int i = 0; i < tablero.length; i++) {
            if (!revisarDirecciones(tablero, reg, i)) {
                vacio++;
            }
        }
        if (vacio > 0) {
            return true;
        }
        return false;
    }
    
            
    /**
     * Metodo que resuelve el problema y devuelve el primer tablero posible
     */
    public static int[][] resolver(int tablero[][], int reg) {
        
        int n = tablero.length;
        int[][] defaultTablero = tablero;
        
        if (tableroLleno(tablero)) {
            return tablero;
        }
        
        for (int i = 0; i <= n; i++) {
            
            if (tableroLleno(tablero)) {
                return tablero;
            }
            
            if (i == n && reg != 0 && !tableroLleno(tablero)) {
                tablero[reg-1] = new int[n];
                
                imprimirTablero(tablero);
                System.out.println();
                
                return tablero;
            }
            if (filaDisponible(tablero, reg) || reg == 0) {
                if (!revisarDirecciones(tablero, reg, i)) {
                    tablero[reg][i] = 1;
                    
                    imprimirTablero(tablero);
                    System.out.println();
                    
                    tablero = resolver(tablero, reg+1);
                }
            }
            else {
                tablero[reg-1] = new int[n];
                
                imprimirTablero(tablero);
                System.out.println();
                
                return tablero;
            }
            
        }
        
        if (tableroLleno(tablero)) {
            return tablero;
        }
        return defaultTablero;
    }
    
    /**
     * Metodo que calcula todas las posibles soluciones del problema de las N reinas
     */
    public static int[][] resolverTodo(int tablero[][], int reg) {
        
        int n = tablero.length;
        
        if (reg == n) {
            if (tableroLleno(tablero)) {
                Algoritmo.soluciones++;
                imprimirTablero(tablero);
            }
            tablero[reg-1] = new int[n];
            return tablero;
        }
        
        for (int i = 0; i <= n; i++) {
            if (i == n) {
                if (reg != 0) {
                    tablero[reg-1] = new int[n];
                }
                return tablero;
            }
            if (filaDisponible(tablero, reg)) {
                
                if (!revisarDirecciones(tablero, reg, i)) {
                    tablero[reg][i] = 1;
                    tablero = resolverTodo(tablero, reg+1);
                    tablero[reg] = new int[n];
                }
                
            }
            
        }
        
        return tablero;
    }
    
    /**
     * Metodo para determinar los resultados posibles
     */
    public static int resultadosPosibles(int tablero[][], int reg, int soluciones) {
        
        resolverTodo(tablero, reg);
        return Algoritmo.soluciones;
        
    }
    
}
