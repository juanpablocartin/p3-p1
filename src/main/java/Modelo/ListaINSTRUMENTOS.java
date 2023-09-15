package Modelo;

import java.util.ArrayList;


public class ListaINSTRUMENTOS  {
     public   ArrayList<Instrumento>list;
 //-------------------------------------------------------------------------------------------
     ListaINSTRUMENTOS(){
            list = new ArrayList();
            inicializarLista();
     }
    
 //-------------------------------------------------------------------------------------------
   public   Object [ ]  creaFilallena (Instrumento e) {  
           Object [ ] filAux= new Object  [ 6 ] ; 
            filAux[0]= e.getSerie();
            filAux[1]= e.getDescripcion();
            filAux[2]= e.getMin();
            filAux[3]= e.getMax();
            filAux[4]= e.getTolerancia();
            return filAux;
      }     
   
//-------------------------------------------------------------------------------------------
     public   Object [ ]  retornaFila (int i ) {  
           Object [ ] filAux= new Object  [ 5 ] ; 
           if (i < list.size()) {
                    Instrumento e= list.get(i);
                    filAux[0]= e.getSerie();
                    filAux[1]= e.getDescripcion();
                    filAux[2]= e.getMin();
                    filAux[3]= e.getMax();
                    filAux[4]= e.getTolerancia();
           }
            return filAux;
      }      
   
//-----------------------------------------------------------------------------------------------
    public int getCantidad(){
        return list.size();
   }
  //-----------------------------------------------------------------------------------------------
        public Instrumento  getElemento(int i){
        return list.get(i);
   }
    
  //-------------------------------------------------------------------------------------------
    public void insertarInstru(Instrumento e){
        list.add(e);
    }
    //-----------------------------------------------------------------------------------------------
    public ArrayList<Instrumento>  getArrayList(){
             return list;
   }
    //-------------------------------------------------------------------------------------------       
      public  void     inicializarLista(){
          TipoInstrumento ti=new TipoInstrumento("111","Tipo de Instrumento Demo","Grados C");
                 Instrumento e= new Instrumento ("111","Descripcion del Instrumento",2,4,1,ti); list.add(e);
       }
   //---------------------------------------------------------------------------------------------
      
      void eliminarElemento(int pos)   {
           list.remove(pos); 
      }
      int getINDEX(Instrumento e){
           for (int i = 0; i < list.size(); i++)      {
                if(list.get(i).getSerie().equals(e.getSerie())){
                    return i;
               }
        }
           return -1;
      }
      
}
