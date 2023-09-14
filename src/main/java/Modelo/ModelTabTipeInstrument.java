/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class ModelTabTipeInstrument {

    private DefaultTableModel modelo;
    private ListaTipoInstrumento lista;

    public ModelTabTipeInstrument() {
        modelo = new DefaultTableModel();
        lista = new ListaTipoInstrumento();
        this.darFormato();
    }

    public ModelTabTipeInstrument(ListaTipoInstrumento lista) {
        modelo = new DefaultTableModel();
        this.lista = lista;
        this.darFormato();
    }

    private void darFormato() {
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Unidad");
    }
    public void insertarFilaModelo(Object[] fila){
        modelo.addRow(fila);
    }
    public void insertarTipoInstrumento(TipoInstrumento t){
        this.lista.ingresaTipoInstrumento(t);
        this.insertarFilaModelo(this.lista.retornFila(this.lista.getCantidad()-1));
    }
    
    public DefaultTableModel getModelo(){return modelo;}
    public ListaTipoInstrumento getLista(){return this.lista;}
    
     public void modificaCOMBOBOX(JComboBox<String> c){
        c.removeAllItems();
        for (int i = 0; i < lista.getCantidad(); i++){
             c.addItem(lista.getElementoJP(i).getNombre());
        }
    }
}
