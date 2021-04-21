package es.dam1.prg;

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
        this.singleMatrix = null;
        this.matrizA = null;
        this.matrizB = null;
    }

    /**
     * Simula el menu de una calculadora
     */
    public void menu() {
        int op;
        boolean salir = false, status;
        Scanner sc = new Scanner(System.in);

        while (!salir) {
            status = false;
            imprimirMenu();
            op = sc.nextInt();

            switch (op) {
                case 0:
                    salir = true;
                    break;

                case 1:
                    do {
                        if (crearDosMatrices()) {
                            if (mismoOrden(this.matrizA, this.matrizB)) {
                                status = true;
                                suma(this.matrizA, this.matrizB);
                            }
                        }
                    } while (!status);
                    break;

                case 2:
                    if (crearMatriz()) {
                        escalar(this.singleMatrix);
                    }
                    break;

                case 3:
                    do {
                        if (crearDosMatrices()) {
                            if (sePuedenMultiplicar(this.matrizA.length, this.matrizB[0].length)) {
                                status = true;
                                producto(this.matrizA, this.matrizB);
                            }
                        }
                    } while (!status);
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
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
    public void imprimirMenu() {
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

        if (filas1 != filas2 && col1 != col2) {
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
        boolean correcto = (filas > 0 && col > 0);

        return correcto;
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
        }

        status = true;

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
        }

        status = true;

        return status;
    }

    /**
     * Suma dos matrices de igual tamanio
     *
     * @param matriz1
     * @param matriz2
     * @return La matriz resultante de la suma
     */
    public int[][] suma(int[][] matriz1, int[][] matriz2) {
        int[][] suma = new int[matriz1.length][matriz1[0].length];

        for (int i = 0; i < suma.length; i++) {
            for (int j = 0; j < suma[0].length; j++) {
                suma[i][j] = this.matrizA[i][j] + this.matrizB[i][j];
            }
        }

        for (int[] fil : suma) {
            System.out.println(Arrays.toString(fil));
        }

        return suma;
    }

    /**
     * Realiza el producto de un escalar por una matriz
     *
     * @param matriz la matriz por la cual vamos a realizar el producto
     * @return La matriz resultante del producto por el escalar
     */
    public int[][] escalar(int[][] matriz) {
        int num;
        int[][] resultado;
        Scanner sc = new Scanner(System.in);

        resultado = new int[matriz.length][matriz[0].length];

        System.out.println("Introduzca el numero por el cual dese realizar la multiplicacion:");
        num = sc.nextInt();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                resultado[i][j] = matriz[i][j] * num;
            }
        }

        System.out.println("La matriz resultante es:");
        for (int[] fil : resultado) {
            System.out.println(Arrays.toString(fil));
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

        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz2[0].length; j++) {
                for (int k = 0; k < matriz2.length; k++) {
                    resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                }
            }
        }

        System.out.println("La matriz resultante es:");
        for (int[] i : resultado) {
            System.out.println(Arrays.toString(i));
        }

        return resultado;
    }
}
