/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author luare
 */
public class ListaMediciones {

    private int tamano;
    private ArrayList<Medicion> mediciones;

    public ListaMediciones(int tamano) {
        this.tamano = tamano;
        mediciones = new ArrayList();

        for (int i = 0; i < tamano; i++) {
            mediciones.set(i, new Medicion());
        }
    }

    public void numReferencia(int min, int max) {
        int rango = max - min;        
        int aumento = rango / tamano;
        int numRef=min;
        for(int i=0; i<tamano; i++){
            mediciones.get(i).setReferencia(numRef);
            numRef += aumento;
        }
    }

}
