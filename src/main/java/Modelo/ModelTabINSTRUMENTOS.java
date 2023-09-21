package Modelo;

import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class ModelTabINSTRUMENTOS {
    
    private  DefaultTableModel modelito;
    private ListaINSTRUMENTOS lis; 

//---------------------------------------------------------------------------
    public DefaultTableModel getModelito() {
        return modelito;
    }  
  //--------------------------------------------------------------------
        public ModelTabINSTRUMENTOS() {
               this.modelito= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
            
        };
               this.lis= new ListaINSTRUMENTOS();  
               this.darFormatoModelo();
               this.inicializarModelo(); 
        }
   //--------------------------------------------------------------------
        public void  darFormatoModelo() {
        // Se define las columnas
        modelito.addColumn("No.Serie");
        modelito.addColumn("Descripcion");
        modelito.addColumn("Min");
        modelito.addColumn("Max");
        modelito.addColumn("Tolerancia");
  }
//-----------------------------------------------------------------------------------------
     public void  insertarInstru( Instrumento c){
          lis.insertarInstru(c);
         insertarFilaModelo (  lis. retornaFila (lis.getCantidad() -1) );
   }
   //--------------------------------------------------------------------------------------------------------
      public void  insertarFilaModelo ( Object [ ] filaAux){
             modelito.addRow( filaAux);
   }
     //--------------------------------------------------------------------------------------------------------  
   public void borrarRegistro (int linea) {
         modelito.removeRow(linea);
         lis.eliminarElemento(linea);
   }
      //--------------------------------------------------------------------------------------------------------
   //DefaultTableModel getModelo(){
     //   return modelito;
   //}
        //--------------------------------------------------------------
      public  void inicializarModelo(){
            for (int i = 0; i < lis.getCantidad(); i++)      {
                Object [ ] filAux=  lis.retornaFila (i);
                modelito.addRow(filAux);
            }
              
       }
 
//-----------------------------------------------------------------------
    public ListaINSTRUMENTOS getLista() {
        return lis;
    }

       //--------------------------------------------------------------------  
    //--------------------------------------------------------------------  
    public int getIndexDEdescripcion(String s){
        int j=-1;
        for (int i = 0; i < lis.getCantidad(); i++)      {
               if(lis.getElemento(i).getDescripcion().equals(s)){ 
                   j=i;
               }
        }
        return j;
    }
    public  Instrumento getInstruCONserie(String s){
        for (int i = 0; i < lis.getCantidad(); i++)      {
                if(lis.getElemento (i).getSerie().equals(s)){  
                    return lis.getElemento (i);
               }
        }
        return null;
    }
    public void selectATindex(int i){
        //Este metodo fue implemtado para selecionar la row que se busco
    }
    public String infoDobjeto(int i){
        String s;
        s="Serie: "+lis.getElemento(i).getSerie()+"\n Descripcion: "+lis.getElemento(i).getDescripcion();
        return s;
    }
    public void update(int index,String serie, String descrip,int min, int max , int tole,TipoInstrumento tipoDinstru){
        lis.getElemento(index).setSerie(serie);
        lis.getElemento(index).setDescripcion(descrip);
        lis.getElemento(index).setMin(min);
        lis.getElemento(index).setMax(max);
        lis.getElemento(index).setTolerancia(tole);
        lis.getElemento(index).setTipDinstrumentos(tipoDinstru);
        modelito.removeRow(index);
        insertarFilaModelo(  lis. retornaFila (lis.getINDEX(lis.getElemento(index))) );
    }
    public String getSerieDEdecripcionX(String s){
        for (int i = 0; i < lis.getCantidad(); i++)      {
                if(lis.getElemento (i).getDescripcion().equals(s)){  
                    return lis.getElemento (i).getSerie();
               }
        }
        return "";
    }
    public boolean BuscarXserie(String s){
        for (int i = 0; i < lis.getCantidad(); i++) {
            if (lis.getElemento(i).getSerie().equals(s)) {
                return true;
            }
        }
         return false;
    }
  
   
}
