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
          TipoInstrumento ti=new TipoInstrumento("555","Tipo de Instrumento 5","Grados F");
                 Instrumento e= new Instrumento ("111","Descri D Instru con Tipo=5",2,4,1,ti); 
                    Calibracion c1 = new Calibracion("111", "111", "111", 2);
                    c1.getMediciones().get(0).setLectura(1);
                    c1.getMediciones().get(0).setNumero(1);
                    c1.getMediciones().get(0).setReferencia(1);

                    c1.getMediciones().get(1).setLectura(2);
                    c1.getMediciones().get(1).setNumero(2);
                    c1.getMediciones().get(1).setReferencia(2);

                    Calibracion c2 = new Calibracion("222", "222", "222", 2);
                    c2.getMediciones().get(0).setLectura(3);
                    c2.getMediciones().get(0).setNumero(3);
                    c2.getMediciones().get(0).setReferencia(3);

                    c2.getMediciones().get(1).setLectura(4);
                    c2.getMediciones().get(1).setNumero(5);
                    c2.getMediciones().get(1).setReferencia(4);

                    e.getCalibraciones().ingresar(c1);
                    e.getCalibraciones().ingresar(c2);
                 list.add(e);
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
