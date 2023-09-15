package Controladora;

import Modelo.ModelTabINSTRUMENTOS;
import Modelo.ModelTabTipeInstrument;
import Vista.CalibracionesJPanel;
import Vista.InstruJPanel;
import Vista.TipInstruJPanel;
import Vista.VenPri;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class Controladora implements ActionListener {
    private VenPri VenPricipal;
    public boolean BOOLvp=false;
    private TipInstruJPanel PanTIPOSInstru;
    public boolean BOOLti=false;
    private InstruJPanel PanInstru;
    public boolean BOOLi=false;
    private CalibracionesJPanel PanCali;
    public boolean BOOLcali=false;
    
    private ModelTabINSTRUMENTOS admiinstru;
    private ModelTabTipeInstrument admiTIPOSinstru;
    
    public Controladora(VenPri v,TipInstruJPanel ti,InstruJPanel i,CalibracionesJPanel c){
        this.VenPricipal=v;
        v.setVisible(true);
        
        this.PanTIPOSInstru=ti;
        this.PanInstru=i;
        this.PanCali=c;
        VenPricipal.getTABpri().addTab("Tipos de Instrumentos", PanTIPOSInstru);
        VenPricipal.getTABpri().addTab("Instrumentos", PanInstru);
        VenPricipal.getTABpri().addTab("Calibraciones", PanCali);
        
        admiTIPOSinstru=new ModelTabTipeInstrument();
        ti.getTablaListTipeInstrument().setModel(admiTIPOSinstru.getModelo());
        
        admiinstru=new ModelTabINSTRUMENTOS();
        i.getTablaDInstrumentos().setModel(admiinstru.getModelito());
        //En el evento de ADD Tipo de Instrumento se tiene que agregar la siguiente liniea de codigo
        admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
        ///////Todo lo que va a escuchar/////////////
        PanInstru.getbGuardar().addActionListener(this);
        ///////Todo lo que va a escuchar/////////////
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//--------------------------------------------------------------------------------
   
//--------------------------------------------------------------------------------
        if (e.getSource().equals( PanInstru.getbGuardar())){
                 if (PanInstru.getTxSerie().getText().equals ("")  ||  PanInstru.getTxTolerancia().getText().equals ("") || PanInstru.getTxMin().getText().equals("")||PanInstru.getTxMax().getText().equals("")||PanInstru.getTxDescripcion().getText().equals("")||PanInstru.getTxCB_Tipo().getSelectedIndex()==-1){
                     JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                 } 
                else {  
                            System.out.println("GOOD");
                 }
               return;
        }
//--------------------------------------------------------------------------------
//        if (e.getSource().equals( pc.getButActua())) {
//
//                  if (pc.getTabCiudades().getSelectedRow() == -1)    {
//                         JOptionPane.showMessageDialog(null, "Debes elegir la fila a actalizar", "Error", JOptionPane.ERROR_MESSAGE);
//                  }
//                  else   {
//                      
//                            JOptionPane.showMessageDialog(null, "El espacio de ID esta lleno pero tamabien puede ser editado -es una guia-", "Nota", JOptionPane.INFORMATION_MESSAGE);
//                            pc.getID().setText(admiMod.getLista().getElemento(pc.getTabCiudades().getSelectedRow()).getID());
//                            pc.getNombreADD().setText("");
//                            pc.getGMT().setSelectedIndex(-1);
//                            
//                        
//                       if (pc.getID().getText().equals ("")  ||  pc.getNombreADD().getText().equals ("") || pc.getGMT().getSelectedItem().equals(-1) ){
//                            JOptionPane.showMessageDialog(null, "Debes completar todos los campos para Actualizar", "Error", JOptionPane.ERROR_MESSAGE);
//                        }
//                       admiMod.update(pc.getTabCiudades().getSelectedRow(), pc.getID().getText(), pc.getNombreADD().getText(), Integer.parseInt(pc.getGMT().getSelectedItem().toString()));
//                  }
//                  return;
//        } 
////--------------------------------------------------------------------------------
//        if (e.getSource().equals( pc.getButBusc())) {
//
//                  if (pc.getNombreBUS().getText().equals (""))    {
//                         JOptionPane.showMessageDialog(null, "Debe poner un nombre de Ciudadad para buscar", "Error", JOptionPane.ERROR_MESSAGE);
//                  }
//                  else   {
//                      if(admiMod.getIndexDEnombre(pc.getNombreBUS().getText())==Integer.parseInt("-1")){
//                         JOptionPane.showMessageDialog(null, "Ese Nombre no esta registrado", "Error", JOptionPane.ERROR_MESSAGE);
//                      }
//                      else{
//                          //System.out.println("Okay");
//                          //admiMod.selectATindex(admiMod.getIndexDEnombre(pc.getNombreBUS().getText())); 
//                          JOptionPane.showMessageDialog(null, admiMod.infoDobjeto(admiMod.getIndexDEnombre(pc.getNombreBUS().getText())), "Exito", JOptionPane.INFORMATION_MESSAGE);
//                        }
//                  }
//                  return;
//                  
//        } 
    }

}
//////////  Lest seee
//Rama de desarrollo carlo s