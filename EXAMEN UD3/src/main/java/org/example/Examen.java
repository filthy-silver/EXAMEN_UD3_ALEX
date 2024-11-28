package org.example;

import java.util.*;

public class Examen {

    final static Random rng = new Random();
    final static Scanner sc = new Scanner(System.in);
    final static Integer [][] carton = new Integer [3][3];

    public static void main(String[] args) {

        int bolas = rng.nextInt(30) + 11;

        System.out.println("*** BIENVENIDO AL BINGO DEL CASINO CANTÁBRICO ***");

        Integer [] sorteo = new Integer [bolas];

        for (int i = 0; i < bolas; i++) {
            int aux = rng.nextInt(90)+1;
            while (Arrays.asList(sorteo).contains(aux)) {
                aux = rng.nextInt(90)+1;
            }
            sorteo[i] = rng.nextInt(90) + 1;
        }
        System.out.println(bolas + " bolas extraídas hasta ahora: " + Arrays.toString(sorteo));

        System.out.println("*** Introduce los datos de tu carton ***");
        for (int i = 0; i < carton.length; i++) {
            System.out.println("Fila " + (i+1));
            String [] vAux = new String[3];
            String aux = sc.nextLine();

            if (aux.matches("\\d{1,2}-\\d{1,2}-\\d{1,2}")){
                vAux = aux.split("-");
                for (int e = 0; e < vAux.length; e++) {
                    if (Integer.parseInt(vAux[e]) < 91 && Integer.parseInt(vAux[e]) > 0) {
                        carton[i][e] = Integer.parseInt(vAux[e]);
                    } else {
                        System.out.println("Introduce un valor válido");
                        return;
                    }
                }
            } else {
                System.out.println("Introduce un valor válido en el formato correcto: N-N-N");
                return;
            }
        }

        System.out.println("Datos del cartón introducido: ");
        for (int i = 0; i < carton.length; i++) {
            for (int j = 0; j < carton[i].length; j++) {
                System.out.print(carton[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("PREMIOS:");
        int price = 0;


        for (int i = 0; i < carton.length; i++) {
            for (int j = 0; j < carton[i].length; j++) {
                if(Arrays.asList(sorteo).contains(carton[i][j])){
                    price++;
                }
            }
        }

        if (price == 9){
            System.out.println("BINGO!");
        } else {
            System.out.println("No hay BINGO\n");
            int aux = 0;
            int aux2 = 0;


            for (int j = 0; j <= carton.length; j++) {

                aux = 0;

                for (int k = 0; k < carton[j].length; k++) {
                    if(Arrays.asList(sorteo).contains(carton[j][k])){
                        aux++;
                    }
                }

                if (aux == 3){
                    System.out.println("Linea "+ ( j +1 )  +": CORRECTA");
                } else {
                    System.out.println("Linea "+ ( j +1 ) +": INCORRECTA");
                }

                aux2++;

                if (aux2 == 3){
                    return;
                }
            }
        }

    }

}
