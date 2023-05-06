package com.company;

import java.util.Scanner;

public class Roundrobin {

    //método para encontrar el tiempo de espera para todos
    static void encontrarTiempo(int proceso[], int n, int bt[], int wt[], int qtum) {

        //tiempos
        int rem_bt[] = new int[n];

        for (int i = 0; i < n; i++) {
            rem_bt[i] = bt[i];
        }
        int ct = 0;
        while (true) {
            boolean done = true;

            for (int i = 0; i < n; i++) {

                if (rem_bt[i] > 0) {

                    done = false;
                    //Verifica los procesos que estan pendientes
                    if (rem_bt[i] > qtum) {

                        ct += qtum;

                        rem_bt[i] -= qtum;
                    }
                    else {
                        ct = ct + rem_bt[i];

                        wt[i] = ct - bt[i];

                        rem_bt[i] = 0;
                    }
                }
            }

            if (done == true) {
                break;
            }
        }
    }

    //Metodo para calcular el tiempo que tarda en cada proceso en dar la vuelta
    static void calcularTiempo(int proceso[], int n, int bt[], int wt[], int tat[]) {
        //calcular el tiempo de respuesta sumando
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }

    static void tiempoFinalizacion(int proceso[], int n, int bt[], int qtum) {
        int wt[] = new int[n];
        int tat[] = new int[n];
        int totalWt = 0;
        int totalTat = 0;


            //función para encontrar el tiempo de espera para  el proceso
        encontrarTiempo(proceso, n, bt, wt, qtum);

        //función para encontrar el tiempo de respuesta para  el proceso
        calcularTiempo(proceso, n, bt, wt, tat);

        //muestra todos los detalles de los procesos
        System.out.println("PROCESO:  "  + " RAFAGA DE TIEMPO:  " + " TIEMPO DE ESPERA:  " + " TIEMPO DE VUELTA: ");

            //calcular tiempo total de espera y el tiempo total
        for (int i = 0; i < n; i++) {

            totalWt = totalWt + wt[i];
            totalTat = totalTat + tat[i];

            System.out.println(" " + (i + 1) + "\t\t\t\t" + bt[i] + "\t\t\t\t\t" + wt[i] + "\t\t\t\t\t\t" + tat[i]);

        }
        System.out.println("PROMEDIO DE TIEMPO DE ESPERA: " + (float) totalWt / (float) n);
        System.out.println("PROMEDIO DE TIEMPO DE RESPUESTA: " + (float) totalTat / (float) n);

    }

    //metoo principal
    public static void main(String[] args) {
        Scanner  tec = new Scanner(System.in);
        //Numero de los procesos
        int noProcesos=0;

        System.out.print("INGRESE EL NUMERO DE PROCESOS A REALIZAR -> ");
        noProcesos= tec.nextInt();
        int proceso[] = new int [noProcesos] ;
        int [] rafagaTiempo = new int [noProcesos];
        for (int i = 0; i <noProcesos ; i++) {
            System.out.print("NO. DE PROCESO: ");
            proceso[i]=tec.nextInt();
            System.out.print("INGRESE LA RAFAGA DE TIEMPO: ");
            rafagaTiempo[i]=tec.nextInt();
        }
        //cantidad del tiempo
        int qtum = 4;

        tiempoFinalizacion(proceso, noProcesos, rafagaTiempo, qtum);
    }
}



