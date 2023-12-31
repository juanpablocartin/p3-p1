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

//    public ModeloTabMediciones(DefaultTableModel modelo, ListaMediciones mediciones) {
//        this.modelo = modelo;
//        this.mediciones = mediciones;
//    }

    public ModeloTabMediciones(ListaMediciones mediciones) {
        this.mediciones = mediciones;
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
            
        };
        formatoModelo();
    }

    public ModeloTabMediciones() {

    }

    public ModeloTabMediciones(int tam) {
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
            
        };
        mediciones = new ListaMediciones(tam);
        formatoModelo();

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

    public Medicion getElemento(int i) {
        return mediciones.get(i);
    }

    public void setMediciones(ListaMediciones mediciones) {
        this.mediciones = mediciones;
    }

    public void editarMedicion(int pos, int numLect) {
        this.mediciones.get(pos).setLectura(numLect);
    }

    private void formatoModelo() {
        String[] s = {"Medida", "Referencia", "Lectura"};
        modelo.setColumnIdentifiers(s);
    }

    public boolean celdaEditable(int col) {
        return col == 2;
    }

    public void actualizarTabla() {
        modelo.setRowCount(0);
        for (int i = 0; i < mediciones.getTamano(); i++) {
            modelo.addRow(new Object[]{mediciones.get(i).getNumero(), mediciones.get(i).getReferencia(), mediciones.get(i).getLectura()});
        }
    }

    public void numeroLectura(int min, int max) {
        int rango = max - min;
        int aumento = rango / mediciones.getTamano();
        int numRef = min;

        mediciones.get(0).setReferencia(min);
        mediciones.get(mediciones.getTamano() - 1).setReferencia(max);
        for (int i = 1; i < mediciones.getTamano() - 1; i++) {
            numRef += aumento;
            mediciones.get(i).setReferencia(numRef);
        }
    }
}
