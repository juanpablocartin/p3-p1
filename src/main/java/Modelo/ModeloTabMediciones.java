/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luare
 */
public class ModeloTabMediciones {
    private DefaultTableModel modelo;
    private ListaMediciones mediciones;

    public ModeloTabMediciones(DefaultTableModel modelo, ListaMediciones mediciones) {
        this.modelo = modelo;
        this.mediciones = mediciones;
    }

    public ModeloTabMediciones(ListaMediciones mediciones) {
        this.mediciones = mediciones;
        modelo = new DefaultTableModel();
    }

    public ModeloTabMediciones() {
    }
    

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public ListaMediciones getMediciones() {
        return mediciones;
    }

    public void setMediciones(ListaMediciones mediciones) {
        this.mediciones = mediciones;
    }
    
}
