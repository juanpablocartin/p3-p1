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
public class Calibracion {
    
    private String num;
    private String numSerieInstrumento;
    private Fecha fecha;
    private int cantMediciones;
    private ListaMediciones mediciones;

    public Calibracion(String num, String numSerieInstrumento, Fecha fecha, int cantMediciones) {
        this.num = num;
        this.numSerieInstrumento = numSerieInstrumento;
        this.fecha = fecha;
        this.cantMediciones = cantMediciones;
        mediciones = new ListaMediciones(cantMediciones);
    }

    public ListaMediciones getMediciones() {
        return mediciones;
    }

    public void setMediciones(ListaMediciones mediciones) {
        this.mediciones = mediciones;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumSerieInstrumento() {
        return numSerieInstrumento;
    }

    public void setNumSerieInstrumento(String numSerieInstrumento) {
        this.numSerieInstrumento = numSerieInstrumento;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public int getCantMediciones() {
        return cantMediciones;
    }

    public void setCantMediciones(int cantMediciones) {
        this.cantMediciones = cantMediciones;
    }

    
    void setNumReferenciaMediciones(int min, int max){
       mediciones.numReferencia(min, max);
        
    }

    
    
    
}
