/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo;

import static tpo.tpo_todas.comprobar;

/**
 *
 * @author Juan Braceras
 */
public class Tpo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] reinas;  // Vector con las posiciones de las reinas de cada fila
        int nreinas;  // Número de reinas
        int i;        // Contador

        // Leer número de reinas 
        // (parámetro del programa)
        nreinas =40;

        // Colocar las reinas en el tablero
        if (nreinas > 0) {
            // Crear vector dinámicamente
            reinas = new int[nreinas];

            // Inicializar vector:
            // (inicialmente, ninguna reina está colocada)
            for (i = 0; i < nreinas; i++) {
                reinas[i] = -1;
//                System.out.println(reinas[i] + ", ");
            }
            // Tomamos la hora del sistema en nanosegundos
            long inicio = System.nanoTime(); 
            // Colocar reinas (algoritmo recursivo)
            colocarReina(0, reinas, nreinas);
            long fin = System.nanoTime();
            long tTotal = fin - inicio;
            System.out.println("");
            System.out.println("El tiempo total para encontrar una solucion sera: " + tTotal);

        } else {

            System.out.println("Debe ingresar un numero de reinas mayor a 0");

        }
    }

    // Problema de las N reinas
// ------------------------
// Algoritmo recursivo
//
// 0. Colocaremos una reina en cada fila
//
// 1. Se coloca una reina en una casilla de su fila y,
//    a continuación, se intentan colocar las reinas restantes.
//
// 2. Si las demás reinas no se pueden colocar con éxito,
//    probamos a colocar la reina actual en otra columna.
//
// Caso base: Cuando no quedan reinas por colocar.
// Comprobar si una reina está bien colocada
// -----------------------------------------
// La reina de la fila i está bien colocada si no está
// en la columna ni en la misma diagonal que cualquiera
// de las reinas de las filas anteriores
//
// Parámetros
//   fila   - Fila de la reina cuya posición queremos validar
//   reinas - Vector con las posiciones de las reinas
//   n      - Número de reinas
    public static boolean comprobar(int fila, int reinas[]) {
        int i;
        boolean resultado;

        for (i = 0; i < fila; i++) {
            if ((reinas[i] == reinas[fila]) // Misma columna
                    || (Math.abs(fila - i) == Math.abs(reinas[fila] - reinas[i]))) {  // Misma diagonal
                return false;
            }
        }
        return true;
    }

// Colocación de una reina
// -----------------------
// Parámetros
//   fila   - Fila de la reina que queremos colocar
//   reinas - Vector con las posiciones de las reinas
//   n      - Número de reinas
// Resultado
//   TRUE si se coloca correctamente la reina
//   FALSE si no se ha podido colocar la reina
    public static boolean colocarReina(int fila, int reinas[], int n) {
        boolean ok = false;

        if (fila < n) {
            //System.out.println("entro en if");

            // Quedan reinas por colocar
            while ((reinas[fila] < n - 1) && !ok) {
                // System.out.println("entro en while con columna " + reinas[fila] + "fila:" + fila);
                // Colocamos la reina en la siguiente columna

                reinas[fila]++;

                // Comprobamos si la posición 
                // de la reina actual es válida
                //System.out.println("Fila:" + fila + " / Columna:" + reinas[fila] + ": " + comprobar(fila, reinas));
                if (comprobar(fila, reinas)) {

                    // Si es así, intentamos colocar
                    // las reinas restantes
                    ok = colocarReina(fila + 1, reinas, n);

                }
            }

            if (!ok) {
                //System.out.println("backtracking----------------");
                // Si no se han podido colocar las demás reinas
                // con la reina actual donde está, la quitamos
                reinas[fila] = -1;
            }

        } else {

            // No quedan reinas por colocar (caso base)
            ok = true;
//            int i;
//            for (i = 0; i < n - 1; i++) {
//                System.out.print(reinas[i] + ", ");
//            }
//            System.out.println(reinas[i++]);

        }

        return ok;
    }
}
