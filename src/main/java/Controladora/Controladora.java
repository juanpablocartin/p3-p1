package Controladora;

import Modelo.Instrumento;
import Modelo.ListaTipoInstrumento;
import Modelo.ModelTabINSTRUMENTOS;
import Modelo.ModelTabTipeInstrument;
import Modelo.TipoInstrumento;
import Vista.CalibracionesJPanel;
import Vista.InstruJPanel;
import Vista.TipInstruJPanel;
import Vista.VenPri;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Controladora implements ActionListener {

    private VenPri VenPricipal;
    public boolean BOOLvp = false;
    private TipInstruJPanel PanTIPOSInstru;
    public boolean BOOLti = false;
    private InstruJPanel PanInstru;
    public boolean BOOLi = false;
    private CalibracionesJPanel PanCali;
    public boolean BOOLcali = false;

    private ModelTabINSTRUMENTOS admiinstru;
    private ModelTabTipeInstrument admiTIPOSinstru;
    //------------------------------------------------
    private ListaTipoInstrumento listaTipos;

    //Lista global de tipos de instrumentos
    //-------------------------------------------------
    public Controladora(VenPri v, TipInstruJPanel ti, InstruJPanel i, CalibracionesJPanel c) {
        this.VenPricipal = v;
        v.setVisible(true);

        this.PanTIPOSInstru = ti;
        
        this.PanInstru = i;
        PanInstru.getbEditar().setEnabled(false);
        
        this.PanCali = c;
        VenPricipal.getTABpri().addTab("Tipos de Instrumentos", PanTIPOSInstru);
        VenPricipal.getTABpri().addTab("Instrumentos", PanInstru);
        VenPricipal.getTABpri().addTab("Calibraciones", PanCali);
        //Creación de la lista de tipos de instrumentos--------------------------------------------------
        this.listaTipos = new ListaTipoInstrumento();
        admiTIPOSinstru = new ModelTabTipeInstrument(this.listaTipos);//Se le ingresa la lista global
        ti.getTablaListTipeInstrument().setModel(admiTIPOSinstru.getModelo());

        admiinstru = new ModelTabINSTRUMENTOS();
        i.getTablaDInstrumentos().setModel(admiinstru.getModelito());
        //En el evento de ADD Tipo de Instrumento se tiene que agregar la siguiente liniea de codigo
        admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
        
        ///////Todo lo que va a escuchar/////////////
        this.PanInstru.getbGuardar().addActionListener(this);
        this.PanInstru.getbLimpiar().addActionListener(this);
        this.PanInstru.getbBorrar().addActionListener(this);
        this.PanInstru.getbBuscar().addActionListener(this);
        this.PanInstru.getbReporte().addActionListener(this);
        this.PanInstru.getbEditar().addActionListener(this);
        
        this.PanTIPOSInstru.getBotonGuardar().addActionListener(this);
        this.PanTIPOSInstru.getBotonLimpiar().addActionListener(this);
        ///////Todo lo que va a escuchar/////////////

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //-----------------------------------------Panel Tipo de Instrumento-----------------------------------------------------

        if (e.getSource().equals(this.PanTIPOSInstru.getBotonGuardar())) {
            if (this.PanTIPOSInstru.getCodigoTextField().getText().equals("") || this.PanTIPOSInstru.getNombreTextField().getText().equals("") || this.PanTIPOSInstru.getUnidadTexttField().getText().equals("")) {
                JOptionPane.showMessageDialog(this.VenPricipal, "Debes completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                TipoInstrumento t = new TipoInstrumento();
                t.setCodigo(this.PanTIPOSInstru.getCodigoTextField().getText());
                t.setNombre(this.PanTIPOSInstru.getNombreTextField().getText());
                t.setUnidad(this.PanTIPOSInstru.getUnidadTexttField().getText());
                this.admiTIPOSinstru.insertarTipoInstrumento(t);
                JOptionPane.showMessageDialog(this.VenPricipal, t.getNombre() + " ingresado al sistemas", "Gardar", JOptionPane.INFORMATION_MESSAGE);

                admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                this.PanTIPOSInstru.getCodigoTextField().setText("");
                this.PanTIPOSInstru.getNombreTextField().setText("");
                this.PanTIPOSInstru.getUnidadTexttField().setText("");
                admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
            }
        }
        if (e.getSource().equals(this.PanTIPOSInstru.getBotonLimpiar())) {
            if (this.PanTIPOSInstru.getCodigoTextField().getText().equals("") || this.PanTIPOSInstru.getNombreTextField().getText().equals("") || this.PanTIPOSInstru.getUnidadTexttField().getText().equals("")) {

            } else {
                this.PanTIPOSInstru.getCodigoTextField().setText("");
                this.PanTIPOSInstru.getNombreTextField().setText("");
                this.PanTIPOSInstru.getUnidadTexttField().setText("");
                this.PanTIPOSInstru.getBotonBorrar().setEnabled(false);
            }
        }
        if (e.getSource().equals(this.PanTIPOSInstru.getBotonBorrar())) {
            if (this.PanTIPOSInstru.getCodigoTextField().isEnabled()) {
                ArrayList<Instrumento> lista = this.admiinstru.getLista().getArrayList();
                Iterator<Instrumento> itr = lista.iterator();
                int cant = 0;
                while (itr.hasNext()) {
                    if (itr.next().getTipDinstrumentos().getCodigo().equals(this.PanTIPOSInstru.getCodigoTextField().getText())) {
                        cant++;
                    }
                }
                if (cant != 0) {
                    JOptionPane.showMessageDialog(this.VenPricipal, "No se puede eliminar el tipo de instrumento. El sistema cuenta con " + cant + this.PanTIPOSInstru.getNombreTextField().getText(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.listaTipos.getLista().remove(this.listaTipos.getElementoPos(this.PanTIPOSInstru.getCodigoTextField().getText()));
                    JOptionPane.showMessageDialog(this.VenPricipal, "Tipo de instrumento eliminado con exito", "Eliminación de Tipo de Insttrumento", JOptionPane.INFORMATION_MESSAGE);
                    this.PanTIPOSInstru.getCodigoTextField().setText("");
                    this.PanTIPOSInstru.getNombreTextField().setText("");
                    this.PanTIPOSInstru.getUnidadTexttField().setText("");
                    this.PanTIPOSInstru.getBotonBorrar().setEnabled(false);

                }

            } else {
                JOptionPane.showMessageDialog(this.VenPricipal, "Selecciona un Tipo de instrumento ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //------------------------------------------------------Fin panel Tipo de Instrumento--------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
        if (e.getSource().equals(PanInstru.getbGuardar())) {
            if (PanInstru.getTxSerie().getText().equals("") || PanInstru.getTxTolerancia().getText().equals("") || PanInstru.getTxMin().getText().equals("") || PanInstru.getTxMax().getText().equals("") || PanInstru.getTxDescripcion().getText().equals("") || PanInstru.getTxCB_Tipo().getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Debes completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Instrumento auxInstru=new Instrumento(
                        PanInstru.getTxSerie().getText(),
                        PanInstru.getTxDescripcion().getText(),
                        Integer.parseInt(PanInstru.getTxMin().getText()),
                        Integer.parseInt(PanInstru.getTxMax().getText()),
                        Integer.parseInt(PanInstru.getTxTolerancia().getText()),
                        this.admiTIPOSinstru.getTipDinstruXnombre(PanInstru.getTxCB_Tipo().getSelectedItem().toString()));
                admiinstru.insertarInstru(auxInstru);
                
            }
            return;
        }
//--------------------------------------------------------------------------------
        if (e.getSource().equals(PanInstru.getbLimpiar())){
            PanInstru.getTxSerie().setText("");
            PanInstru.getTxTolerancia().setText("");
            PanInstru.getTxMin().setText("");
            PanInstru.getTxMax().setText("");
            PanInstru.getTxDescripcion().setText("");
            PanInstru.getTxCB_Tipo().setSelectedIndex(-1);
                  
                  return;
        } 
//--------------------------------------------------------------------------------
        if (e.getSource().equals( PanInstru.getbBuscar()) ){

                  if (PanInstru.getTxDescriAbuscar().getText().equals (""))    {
                         JOptionPane.showMessageDialog(null, "Debe poner una Descripccion a buscar", "Error", JOptionPane.ERROR_MESSAGE);
                  }
                  else   {
                      String aux="";
                      int auxSerieINDEX=-1;
                      aux=admiinstru.getSerieDEdecripcionX(PanInstru.getTxDescriAbuscar().getText());
                      
                      if (aux!=""){
                          if (admiinstru.BuscarXserie(aux)==true){
                            PanInstru.getTxSerie().setText(admiinstru.getInstruCONserie(aux).getSerie());
                            PanInstru.getTxDescripcion().setText(admiinstru.getInstruCONserie(aux).getDescripcion());
                            PanInstru.getTxMin().setText(Integer.toString(admiinstru.getInstruCONserie(aux).getMin()));
                            PanInstru.getTxMax().setText(Integer.toString(admiinstru.getInstruCONserie(aux).getMax()));
                            PanInstru.getTxTolerancia().setText(Integer.toString(admiinstru.getInstruCONserie(aux).getTolerancia()));
                            PanInstru.getTxCB_Tipo().setSelectedIndex(
                            admiTIPOSinstru.getIndexDtipoInstruXcodigo(admiinstru.getInstruCONserie(aux).getTipDinstrumentos().getCodigo())
                            );
                            PanInstru.getbEditar().setEnabled(true);
                          } 
                     }
                  }
                  return;
                  
        }
 //--------------------------------------------------------------------------------
       if (e.getSource().equals( PanInstru.getbEditar()) ){ 
           String auxSerie;
           auxSerie=admiinstru.getSerieDEdecripcionX(PanInstru.getTxDescriAbuscar().getText());
           int auxSerieINDEX=-1;
           auxSerieINDEX=admiinstru.getIndexDEserie(PanInstru.getTxSerie().getText());
                            admiinstru.update(auxSerieINDEX,
                                    PanInstru.getTxSerie().getText(),
                                    PanInstru.getTxDescripcion().getText(),
                                    Integer.parseInt(PanInstru.getTxMin().getText()),
                                    Integer.parseInt(PanInstru.getTxMax().getText()),
                                    Integer.parseInt(PanInstru.getTxTolerancia().getText()),
                                    admiinstru.getInstruCONserie(auxSerie).getTipDinstrumentos());
            PanInstru.getTxSerie().setText("");
           PanInstru.getTxTolerancia().setText("");
           PanInstru.getTxMin().setText("");
           PanInstru.getTxMax().setText("");
           PanInstru.getTxDescripcion().setText("");
           PanInstru.getTxCB_Tipo().setSelectedIndex(-1);
           PanInstru.getTxDescriAbuscar().setText("");
           PanInstru.getbEditar().setEnabled(false);
       } 
//--------------------------------------------------------------------------------
    }

}
//////////  Lest seee
// hola
//hola
//holaaaaaa que dolor
