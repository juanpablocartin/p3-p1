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
            mediciones.add(new Medicion(i + 1));
        }
    }

    public ListaMediciones() {
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public Medicion get(int i) {
        return mediciones.get(i);
    }

    public ArrayList<Medicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(ArrayList<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    public void numReferencia(int min, int max) {
        int rango = max - min;
        int aumento = rango / tamano;
        int numRef = min;

        mediciones.get(0).setReferencia(min);
        mediciones.get(tamano - 1).setReferencia(max);
        for (int i = 1; i < tamano - 1; i++) {
            numRef += aumento;
            mediciones.get(i).setReferencia(numRef);
        }
    }

}
