package org.example;

import java.util.*;

public class Examen {

    final static Random rng = new Random();     //Definimos las variables estáticas que no vamos a cambiar
    final static Scanner sc = new Scanner(System.in);
    final static Integer [][] carton = new Integer [3][3];

    public static void main(String[] args) {

        int bolas = rng.nextInt(30) + 11; //vamos a crear un numero aleatorio entre 30 y 40

        System.out.println("*** BIENVENIDO AL BINGO DEL CASINO CANTÁBRICO ***");

        Integer [] sorteo = new Integer [bolas];

        for (int i = 0; i < bolas; i++) {
            int aux = rng.nextInt(90)+1; //generaremos un numero aleatorio entre el 1 y el 90
            while (Arrays.asList(sorteo).contains(aux)) {
                aux = rng.nextInt(90)+1; //Siempre que el numero esté dentro del soreo el auxiliar se re-calculará
            }
            sorteo[i] = rng.nextInt(90) + 1; //cuando deje de estar repetido se asignará
        }
        System.out.println(bolas + " bolas extraídas hasta ahora: " + Arrays.toString(sorteo)); //sacamos las bolas por pantalla

        System.out.println("*** Introduce los datos de tu carton ***");
        for (int i = 0; i < carton.length; i++) {
            System.out.println("Fila " + (i+1));
            String [] vAux = new String[3];
            String aux = sc.nextLine();

            if (aux.matches("\\d{1,2}-\\d{1,2}-\\d{1,2}")){ //le pedimos al usuario que introduzca un número de dos cifras separados por un gion
                vAux = aux.split("-");
                for (int e = 0; e < vAux.length; e++) {
                    if (Integer.parseInt(vAux[e]) < 90 && Integer.parseInt(vAux[e]) > 0) { //siempre que no sea manor que 0 o mayor que 90 será válido
                        carton[i][e] = Integer.parseInt(vAux[e]);
                    } else {
                        System.out.println("Introduce un valor válido");
                        return;
                    }
                }
            } else {
                System.out.println("Introduce un valor válido en el formato correcto: N-N-N"); //Si ha introducido una letra o algo que no este en el formato, no funcionará
                return;
            }
        }

        System.out.println("Datos del cartón introducido: ");
        for (int i = 0; i < carton.length; i++) {
            for (int j = 0; j < carton[i].length; j++) {
                System.out.print(carton[i][j] + " "); //recorreremos el carton en horizontal y vertical y lo mostraremos por pantalla
            }
            System.out.println();
        }

        System.out.println("PREMIOS:");
        int price = 0;


        for (int i = 0; i < carton.length; i++) {
            for (int j = 0; j < carton[i].length; j++) {
                if(Arrays.asList(sorteo).contains(carton[i][j])){
                    price++; //vamos a contar cuantos numeros coinciden
                }
            }
        }

        if (price == 9){ //si coinciden los 9, cantamos bingo
            System.out.println("BINGO!");
        } else {
            System.out.println("No hay BINGO\n");
            int aux = 0;
            int aux2 = 0;


            for (int j = 0; j <= carton.length; j++) {

                aux = 0;

                for (int k = 0; k < carton[j].length; k++) { //si no hay bingo recorreremos cada fila, sumando 1 al auxiliar
                    if(Arrays.asList(sorteo).contains(carton[j][k])){
                        aux++;
                    }
                }

                if (aux == 3){
                    System.out.println("Linea "+ ( j +1 )  +": CORRECTA"); //si el auxiliar llega a 3 es que se ha hecho una linea
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
