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
    private String fechaCalibracion;
    private int cantMediciones;
    private ModeloTabMediciones medicionesL;

    public Calibracion() {

    }

    public Calibracion(String num, String numSerieInstrumento, String fechaCalibracion, int cantMediciones) {
        this.num = num;
        this.numSerieInstrumento = numSerieInstrumento;
        this.fechaCalibracion = fechaCalibracion;
        this.cantMediciones = cantMediciones;
        medicionesL = new ModeloTabMediciones();
    }

    public ModeloTabMediciones getMedicionesL() {
        return medicionesL;
    }

    public void setMedicionesL(ModeloTabMediciones medicionesL) {
        this.medicionesL = medicionesL;
    }

    public String getFechaCalibracion() {
        return fechaCalibracion;
    }

    public void setFechaCalibracion(String fechaCalibracion) {
        this.fechaCalibracion = fechaCalibracion;
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

    public int getCantMediciones() {
        return cantMediciones;
    }

    public void setCantMediciones(int cantMediciones) {
        this.cantMediciones = cantMediciones;
    }

    public void setNumLecturaMediciones(int min, int max) {
        medicionesL.numeroLectura(min, max);

    }

}
