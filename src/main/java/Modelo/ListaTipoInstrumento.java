/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Carlos
 */
public class ListaTipoInstrumento {

    private ArrayList<TipoInstrumento> lista;
    
//-----------------------------------------------------------------------------------
    
    public ListaTipoInstrumento() {
        this.lista = new ArrayList();
        this.inicializarLista();
    }
    
//-----------------------------------------------------------------------------------
    
    public int getCantidad() {
        return this.lista.size();
    }
    
//-----------------------------------------------------------------------------------
    
    public void ingresaTipoInstrumento(TipoInstrumento t) {
        this.lista.add(t);
    }
    
//-----------------------------------------------------------------------------------
    
    public int getElementoPos(String dato) {
        int contador1 = 0;
        int contador2 = 0;
        TipoInstrumento aux2 = null;
        Iterator<TipoInstrumento> itr = lista.iterator();
        while (itr.hasNext()) {
            aux2 = itr.next();
            if (aux2.getCodigo().equals(dato)) {
                contador2 = contador1;
            } else if (aux2.getNombre().equals(dato)) {
                contador2 = contador1;
            } else if (aux2.getUnidad().equals(dato)) {
                contador2 = contador1;
            }
            contador1++;
        }

        return contador2;
    }
    
//-----------------------------------------------------------------------------------
    
    public TipoInstrumento buscaTipoInsturmento(String dato) {
        /*El tipo de instrumento se puede buscar mediante
        su código, nombre o unidad */
        TipoInstrumento aux = null;
        TipoInstrumento aux2 = null;
        Iterator<TipoInstrumento> itr = lista.iterator();
        while (itr.hasNext()) {
            aux2 = itr.next();
            if (aux2.getCodigo().equals(dato)) {
                aux = aux2;
            } else if (aux2.getNombre().equals(dato)) {
                aux = aux2;
            } else if (aux2.getUnidad().equals(dato)) {
                aux = aux2;
            }
        }

        return aux;
    }
    
//-----------------------------------------------------------------------------------
    
    public ArrayList<TipoInstrumento> getLista() {
        return this.lista;
    }

//-----------------------------------------------------------------------------------
    
    public Object[] creaFilaLLena(TipoInstrumento t) {
        Object[] fila = new Object[3];
        fila[0] = t.getCodigo();
        fila[1] = t.getNombre();
        fila[2] = t.getUnidad();

        return fila;
    }
    
//-----------------------------------------------------------------------------------
    
    public Object[] retornFila(int n) {
        Object[] fila = new Object[3];
        if (n < this.lista.size()) {
            TipoInstrumento t = lista.get(n);
            fila[0] = t.getCodigo();
            fila[1] = t.getNombre();
            fila[2] = t.getUnidad();
        }
        return fila;
    }
    public TipoInstrumento  getElementoJP(int i){
        return lista.get(i);
   }
    public  void     inicializarLista(){
//        TipoInstrumento tii=new TipoInstrumento("111","Tipo de Instrumento 111","Grados C");
//        lista.add(tii);
//        tii=new TipoInstrumento("222","Tipo de Instrumento 222","Grados C");
//        lista.add(tii);
//        tii=new TipoInstrumento("333","Tipo de Instrumento 333","Grados C");
//        lista.add(tii);
//        tii=new TipoInstrumento("444","Tipo de Instrumento 444","Grados C");
//        lista.add(tii);
//        tii=new TipoInstrumento("555","Tipo de Instrumento 555","Grados C");
//        lista.add(tii);
    }
    
}
