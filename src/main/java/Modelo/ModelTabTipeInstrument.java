package Modelo;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class ModelTabTipeInstrument {

    private DefaultTableModel modelo;
    private ListaTipoInstrumento lista;

    public DefaultTableModel getModelo(){
        return modelo;
    }
    public ModelTabTipeInstrument() {
        modelo = new DefaultTableModel();
        lista = new ListaTipoInstrumento();
        this.darFormato();
        this.inicializarModelo();
    }

    public ModelTabTipeInstrument(ListaTipoInstrumento lista) {
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
            
        };
        this.lista = lista;
        this.darFormato();
        
    }

    private void darFormato() {
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Unidad");
    }
    public void insertarTipoInstrumento(TipoInstrumento t){
        this.lista.ingresaTipoInstrumento(t);
        this.insertarFilaModelo(this.lista.retornFila(this.lista.getCantidad()-1));
        
    }
    public void insertarFilaModelo(Object[] fila){
        modelo.addRow(fila);
    }
    
    public ListaTipoInstrumento getLista(){return this.lista;}
//-------------------------------------------------------------------------------------- 
     public void modificaCOMBOBOX(JComboBox<String> c){
        c.removeAllItems();
        for (int i = 0; i < lista.getCantidad(); i++){
             c.addItem(lista.getElementoJP(i).getNombre());
        }
        c.setSelectedIndex(-1);
    }
     public  void inicializarModelo(){
         for (int i = 0; i < lista.getCantidad(); i++)      {
               Object [ ] filAux=  lista.retornFila(i);
                modelo.addRow(filAux);
        }
              
    }
   
}
