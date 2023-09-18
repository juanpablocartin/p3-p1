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
    }

    public ArrayList<Calibracion> getCalibraciones() {
        return calibraciones;
    }

    public void setCalibraciones(ArrayList<Calibracion> calibraciones) {
        this.calibraciones = calibraciones;
    }
    public void ingresar(Calibracion c){
        calibraciones.add(c);
    }
    public int tamano(){
        return calibraciones.size();
    }
    public Calibracion get(int i){
        return calibraciones.get(i);
    }
    public void eliminarPorNum(String num){
        // hacer
    }
    public void eliminarPorPos(int i){
        //hacer
    }
//    public int buscar(String num){
//        int pos=-1;
//        for(int i=0; i<calibraciones.size(); i++){
//            if(calibraciones.get(i).getNum().g)
//        }
//        return pos;
//    }
}
