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
public class ListaCalibraciones {

    private ArrayList<Calibracion> calibraciones;

    public ListaCalibraciones() {
        calibraciones = new ArrayList();
    }

    public ArrayList<Calibracion> getCalibraciones() {
        return calibraciones;
    }

    public void setCalibraciones(ArrayList<Calibracion> calibraciones) {
        this.calibraciones = calibraciones;
    }

    public void ingresar(Calibracion c) {
        calibraciones.add(c);
    }

    public int tamano() {
        return calibraciones.size();
    }

    public Calibracion get(int i) {
        return calibraciones.get(i);
    }

//    public void eliminarPorNum(String num) {
//        // hacer
//    }
//
//    public void eliminarPorPos(int i) {
//        calibraciones.remove(i);
//    }

    public boolean existe(String num) {
        for (int i = 0; i < calibraciones.size(); i++) {
            if (calibraciones.get(i).getNum().compareTo(num) == 0) {
                return true;
            }
        }
        return false;
    }

    public int buscarPorNum(String num) {
        int pos = -1;
        for (int i = 0; i < calibraciones.size(); i++) {
            if (calibraciones.get(i).getNum().compareTo(num) == 0) {
                pos = i;
                break;
            }
        }
        return pos;
    }
}
