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
        lista = new ArrayList();
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
}
