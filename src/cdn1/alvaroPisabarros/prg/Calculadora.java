package cdn1.alvaroPisabarros.prg;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Calculadora diseñada para realizar operaciones con matrices
 *
 * @author Alvaro Pisabarros
 */
public class Calculadora {

    private int[][] singleMatrix, matrizA, matrizB;

    /**
     * Constructor de la clase Calculadora
     */
    public Calculadora() {

    }

    /**
     * Simula el menu de una calculadora
     */
    public void menu() {
        int op;

        this.singleMatrix = null;
        this.matrizA = null;
        this.matrizB = null;

        boolean salir = false, status;
        Scanner sc = new Scanner(System.in);

        while (!salir) {
            status = false;
            imprimirMenu();
            op = sc.nextInt();

            switch (op) {
                case 0: // Salir
                    salir = true;
                    break;

                case 1: // Suma
                    do {
                        if (crearDosMatrices()) {
                            if (mismoOrden(this.matrizA, this.matrizB)) {
                                status = true;
                                imprimirResultado(suma(this.matrizA, this.matrizB));
                            }
                        }
                    } while (!status);
                    break;

                case 2: // Escalar
                    if (crearMatriz()) {
                        System.out.println("Introduzca el numero por el cual dese realizar la multiplicacion:");
                        int num = sc.nextInt();
                        imprimirResultado(escalar(this.singleMatrix, num));
                    }
                    break;

                case 3: // Producto
                    do {
                        if (crearDosMatrices()) {
                            if (sePuedenMultiplicar(this.matrizA.length, this.matrizB[0].length)) {
                                status = true;
                                imprimirResultado(producto(this.matrizA, this.matrizB));
                            }
                        }
                    } while (!status);
                    break;

                case 4: // Traspuesta
                    do {
                        if (crearMatriz()) {
                            status = true;
                            imprimirResultado(transponer(this.singleMatrix));
                        }
                    } while (!status);
                    break;

                case 5: // Diagonal principial
                    do {
                        if (crearMatriz()) {
                            if (esCuadrada(this.singleMatrix)) {
                                status = true;
                                diagonalPrincipal(this.singleMatrix);
                            }
                        }
                    } while (!status);
                    break;

                case 6: // Simetrica
                    do {
                        if (crearMatriz()) {
                            if (esCuadrada(this.singleMatrix)) {
                                status = true;
                                esSimetrica(this.singleMatrix);
                            }
                        }

                    } while (!status);
                    break;

                case 7: // Potencia
                    break;

                case 8: // Resta
                    do {
                        if (crearDosMatrices()) {
                            if (mismoOrden(this.matrizA, this.matrizB)) {
                                status = true;
                                imprimirResultado(resta(this.matrizA, this.matrizB));
                            }
                        }
                    } while (!status);
                    break;

                default:
                    System.out.println("Opcion no valida \n");
                    break;
            }
        }

        sc.close();
    }

    /**
     * Imprime las opciones del menu por pantalla
     */
    private void imprimirMenu() {
        System.out.println("1. Suma de dos matrices");
        System.out.println("2. Producto de un escalar por una matriz");
        System.out.println("3. Producto de dos matrices.");
        System.out.println("4. Transponer una matriz");
        System.out.println("5. Diagonal principal de una matriz");
        System.out.println("6. Comprobar si una matriz es simétrica");
        System.out.println("7. Potencia de una matriz cuadrada");
        System.out.println("8. Resta de dos matrices");
        System.out.println("0. Salir");
    }

    /**
     * Imprime la matriz pasado como parametro
     *
     * @param matriz La matriz que deseamos imprimir
     */
    private void imprimirResultado(int[][] matriz) {
        System.out.println("La matriz resultante es:");

        for (int[] fila : matriz) {
            System.out.println(Arrays.toString(fila));
        }

        System.out.println();
    }

    /**
     * Comprueba si dos matrices tiene el mismo orden (mismo tamaño)
     *
     * @param matrizA La primera matriz a evaluar
     * @param matrizB La segunda matriz a evaluar
     * @return <code>true</code> si tienen el mismo orden y <code>false</code> si no
     */
    private boolean mismoOrden(int[][] matrizA, int[][] matrizB) {
        boolean mismoOrden = false;

        int filas1 = matrizA.length;
        int col1 = matrizA[0].length;
        int filas2 = matrizB.length;
        int col2 = matrizB[0].length;

        if (filas1 != filas2 || col1 != col2) {
            System.out.println("Las matrices no tienen el mismo orden." +
                    "La matriz B debe tener el mismo numero de filas y columnas" +
                    "que la matriz A.\n" +
                    "Introduzca de nuevo los tamaños.\n");
        } else {
            mismoOrden = true;
        }

        return mismoOrden;
    }

    /**
     * Comprueba si una matriz tiene el tamaño correcto
     *
     * @param filas las filas de la matriz
     * @param col   las columnas de la matriz
     * @return <code>true</code> si tiene un tamaño correcto y <code>false</code> si no
     */
    private boolean tamanioCorrecto(int filas, int col) {
        return (filas > 0 && col > 0);
    }

    /**
     * Comprueba si una matriz es multiplicable
     *
     * @param f Las filas de la matriz A
     * @param c Las columnas de la matriz B
     * @return <code>true</code> si son multiplicables y <code>false</code> si no lo son
     */
    private boolean sePuedenMultiplicar(int f, int c) {
        boolean multiplicable = (f == c);

        if (!multiplicable) {
            System.out.println("No se pueden multiplicar." +
                    "Las matriz A debe tener las mimas filas que columnas la matriz B.\n" +
                    "Introduzca de nuevo los mataños.\n");
        }

        return multiplicable;
    }

    /**
     * Comprueba si una matriz es cuadrada
     *
     * @param matriz La matriz la cual queremos comprobar si es cuadrada
     * @return <code>true</code> si la matriz lo es y <code>false</code> si no
     */
    private boolean esCuadrada(int[][] matriz) {
        boolean esCuadrada = matriz.length == matriz[0].length;

        if (!esCuadrada) {
            System.out.println("La matriz ha de tener el mismo numero de filas y columnas.\n" +
                    "Introduzca de nuevo el tamaño.\n");
        }

        return esCuadrada;
    }

    /**
     * Inicializa una matriz con el tamanio y valores introducidos por el usuario
     *
     * @return <code>true</code> si se ha creado correctamente y <code>false</code> si no se ha podido
     */
    private boolean crearMatriz() {
        boolean status = false;
        int fil, col;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca las filas:");
            fil = sc.nextInt();

            System.out.println("Introduzca las columnas:");
            col = sc.nextInt();

            if ((!tamanioCorrecto(fil, col))) {
                System.out.println("Tamaño de la matriz incorrecto. " +
                        "Las filas y columnas deben ser mayores que 0. " +
                        "Introduzca los tamaños de nuevo. \n");
            } else {
                this.singleMatrix = new int[fil][col];
            }

        } while (!tamanioCorrecto(fil, col));

        if (this.singleMatrix != null) {
            for (int i = 0; i < this.singleMatrix.length; i++) {
                for (int j = 0; j < this.singleMatrix[0].length; j++) {
                    System.out.println("Introduzca el valor de la posicion: " + i + ", " + j);
                    this.singleMatrix[i][j] = sc.nextInt();
                }
            }

            status = true;
        }

        return status;
    }

    /**
     * Genera dos matrices con el tamaño y valores introducidor por el usuario
     *
     * @return <code>true</code> si se han creado correctamente y <code>false</code> si no se ha podido
     */
    private boolean crearDosMatrices() {
        boolean status = false;
        int filasA, colA, filasB, colB;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Numero de filas de la primera matriz: ");
            filasA = sc.nextInt();

            System.out.println("Numero de columnas de la primera matriz: ");
            colA = sc.nextInt();

            System.out.println("Numero de filas de la segunda matriz: ");
            filasB = sc.nextInt();

            System.out.println("Numero de columnas de la segunda matriz: ");
            colB = sc.nextInt();

            if (!tamanioCorrecto(filasA, colA) || !tamanioCorrecto(filasB, colB)) {
                System.out.println("Tamaño de la matriz incorrecto. " +
                        "Las filas y columnas deben ser mayores que 0. " +
                        "Introduzca los tamaños de nuevo. \n");
            } else {
                this.matrizA = new int[filasA][colA];
                this.matrizB = new int[filasB][colB];
            }

        } while (!tamanioCorrecto(filasA, colA) || !tamanioCorrecto(filasB, colB));

        if (this.matrizA != null && this.matrizB != null) {

            System.out.println("Matriz A:");
            for (int i = 0; i < matrizA.length; i++) {
                for (int j = 0; j < matrizA[0].length; j++) {
                    System.out.println("Introduzca el valor de la posicion: " + i + ", " + j);
                    this.matrizA[i][j] = sc.nextInt();
                }
            }

            System.out.println("Matriz B:");
            for (int i = 0; i < this.matrizB.length; i++) {
                for (int j = 0; j < this.matrizB[0].length; j++) {
                    System.out.println("Introduzca el valor de la posicion: " + i + ", " + j);
                    this.matrizB[i][j] = sc.nextInt();
                }
            }

            status = true;
        }

        return status;
    }

    /**
     * Suma dos matrices de igual tamanio
     *
     * @param matriz1 La matriz A que vamos a sumar
     * @param matriz2 La matriz B que vamos a sumar
     * @return La matriz resultante de la suma
     */
    public int[][] suma(int[][] matriz1, int[][] matriz2) {
        int[][] suma = new int[matriz1.length][matriz1[0].length];

        for (int i = 0; i < suma.length; i++) {
            for (int j = 0; j < suma[0].length; j++) {
                suma[i][j] = matriz1[i][j] + matriz2[i][j];
            }
        }

        return suma;
    }

    /**
     * Realiza el producto de un escalar por una matriz
     *
     * @param matriz la matriz por la cual vamos a realizar el producto
     * @return La matriz resultante del producto por el escalar
     */
    public int[][] escalar(int[][] matriz, int num) {
        int[][] resultado;

        resultado = new int[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                resultado[i][j] = matriz[i][j] * num;
            }
        }

        return resultado;
    }

    /**
     * Realiza el producto de 2 matrices
     * Para ello las columnas de la matriz A tienen que ser las mismas que las filas de la matriz B
     *
     * @param matriz1 La matriz A
     * @param matriz2 La matriz B
     * @return La matriz resultante del producto
     */
    public int[][] producto(int[][] matriz1, int[][] matriz2) {
        int[][] resultado = new int[matriz1.length][matriz2[0].length];
        int suma = 0;

        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz2[0].length; j++) {
                for (int k = 0; k < matriz2.length; k++) {
                    suma += matriz1[i][k] * matriz2[k][j];
                }
                resultado[i][j] = suma;
                suma = 0;
            }
        }

        return resultado;
    }

    /**
     * Obtiene la matriz traspuesta de una matriz
     *
     * @param matriz la matriz de la cual la queremos obtener
     * @return la matriz traspuesta
     */
    public int[][] transponer(int[][] matriz) {
        int[][] resultado = new int[matriz[0].length][matriz.length];

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                resultado[i][j] = matriz[j][i];
            }
        }

        return resultado;
    }

    /**
     * Halla la diagonal principal de una matriz cuadrada
     *
     * @param matriz La matriz de la que se desea obtener la diagonal
     * @return La diagonal de la matriz
     */
    public int[] diagonalPrincipal(int[][] matriz) {
        int[] diagonal = new int[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i == j) {
                    diagonal[i] = matriz[i][j];
                }
            }
        }

        System.out.println("La diagonal principal es: ");
        System.out.println(Arrays.toString(diagonal));

        return diagonal;
    }

    /**
     * Comprueba si una matriz es simetrica
     *
     * @param matriz La matriz de la cual lo deseamos comprobar
     * @return <code>true</code> si es simetrica y <code>false</code> si no
     */
    public boolean esSimetrica(int[][] matriz) {
        boolean esSimetrica = true;
        int[][] traspuesta = transponer(matriz);

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != traspuesta[i][j]) {
                    esSimetrica = false;
                    break;
                }
            }
        }

        if (!esSimetrica) {
            System.out.println("No es simetrica.\n");
        } else {
            System.out.println("Es simetrica.\n");
        }

        return esSimetrica;
    }

    /**
     * Calcula la potencia de una matriz
     *
     * @param matriz    La matriz de la cual queremos calcular su potencia
     * @param exponente El numero al cual vamos a elevar la potencia
     * @return La matriz resultado de la potencia
     */
    public int[][] potencia(int[][] matriz, int exponente) {
        int[][] resultado = new int[matriz.length][matriz[0].length];

        return resultado;
    }

    public int[][] resta(int[][] matriz1, int[][] matriz2) {
        int[][] resultado;
        int[][] matrizResta = escalar(matriz2, -1);

        resultado = suma(matriz1, matrizResta);

        return resultado;
    }
}
