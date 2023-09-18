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
public class ModelTabCalibraciones {
    private DefaultTableModel modelo;
    private ListaCalibraciones lista;

    public ModelTabCalibraciones() {
        modelo = new DefaultTableModel();
        lista = new ListaCalibraciones();
    }

    public ModelTabCalibraciones(ListaCalibraciones lista) {
        this.lista = lista;
    }

    public ModelTabCalibraciones(DefaultTableModel modelo, ListaCalibraciones lista) {
        this.modelo = modelo;
        this.lista = lista;
    }
    
    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public ListaCalibraciones getList() {
        return lista;
    }

    public void setList(ListaCalibraciones list) {
        this.lista = list;
    }
    public void formatoModelo(){
        String[] s = {"Numero", "Fecha", "Mediciones"};
        modelo.setColumnIdentifiers(s);
    }
    public void ingresar(Calibracion c){
       lista.ingresar(c);
       modelo.addColumn(new Object[]{c.getNum(), c.getFecha().toString(), c.getCantMediciones()});
    }
    public void eliminarPorNumero(String s){
        
    }
    public void eliminarPorPosicion(int i){
        
    }
    public int getPos(String s){
        
        return 0;
        
    }
    public boolean existe(String s){
        return false;        
    }
    public Calibracion getElementoPorNumero(String s){
        
        return null;
        
    }
    public Calibracion getElementoPorPos(int i){
        
        return null;
        
    }
    public void editarCalibracion(){
        
    }
    public void actualizarTabla(){
        modelo.setRowCount(0);
        for(int i=0; i<lista.tamano(); i++){
            modelo.addRow(new Object[]{lista.get(i).getCantMediciones()});
        }
        // terminar de agregar todos los datos de calibraciones que van en la tabla
    }
}
