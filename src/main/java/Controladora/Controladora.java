package Controladora;

import Modelo.Instrumento;
import Modelo.ListaTipoInstrumento;
import Modelo.ModelTabINSTRUMENTOS;
import Modelo.ModelTabTipeInstrument;
import Modelo.PDFReportGenerator;
import Modelo.TipoInstrumento;
import Vista.CalibracionesJPanel;
import Vista.InstruJPanel;
import Vista.TipInstruJPanel;
import Vista.VenPri;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private PDFReportGenerator pdf;

    //Lista global de tipos de instrumentos
    //-------------------------------------------------
    public Controladora(VenPri v, TipInstruJPanel ti, InstruJPanel i, CalibracionesJPanel c) {
        this.VenPricipal = v;
        v.setVisible(true);

        this.PanTIPOSInstru = ti;
        this.PanInstru = i;
        this.PanCali = c;
        VenPricipal.getTABpri().addTab("Tipos de Instrumentos", PanTIPOSInstru);
        VenPricipal.getTABpri().addTab("Instrumentos", PanInstru);
        VenPricipal.getTABpri().addTab("Calibraciones", PanCali);
        //Creación de la lista de tipos de instrumentos--------------------------------------------------
        this.listaTipos = new ListaTipoInstrumento();
        admiTIPOSinstru = new ModelTabTipeInstrument(this.listaTipos);//Se le ingresa la lista global
        ti.getTablaListTipeInstrument().setModel(admiTIPOSinstru.getModelo());
        this.PanTIPOSInstru.getTablaListTipeInstrument().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int fila = PanTIPOSInstru.getTablaListTipeInstrument().getSelectedRow();
                    if (fila != -1) {
                        TipoInstrumento t = listaTipos.getLista().get(fila);
                        PanTIPOSInstru.getCodigoTextField().setText(t.getCodigo());
                        PanTIPOSInstru.getCodigoTextField().setEnabled(false);
                        PanTIPOSInstru.getNombreTextField().setText(t.getNombre());
                        PanTIPOSInstru.getUnidadTexttField().setText(t.getUnidad());

                        PanTIPOSInstru.getBotonBorrar().setEnabled(true);

                    }
                }
            }

        });

        admiinstru = new ModelTabINSTRUMENTOS();
        i.getTablaDInstrumentos().setModel(admiinstru.getModelito());
        //En el evento de ADD Tipo de Instrumento se tiene que agregar la siguiente liniea de codigo
        admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
        ///////Todo lo que va a escuchar/////////////
        PanInstru.getbGuardar().addActionListener(this);
        ///////Todo lo que va a escuchar/////////////

        this.PanTIPOSInstru.getBotonGuardar().addActionListener(this);
        this.PanTIPOSInstru.getBotonLimpiar().addActionListener(this);
        this.PanTIPOSInstru.getBotonBorrar().addActionListener(this);
        this.PanTIPOSInstru.getBotonBusqueda().addActionListener(this);
        this.PanTIPOSInstru.getBotonReporte().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //-----------------------------------------Panel Tipo de Instrumento-----------------------------------------------------

        if (e.getSource().equals(this.PanTIPOSInstru.getBotonGuardar())) {
            if (this.PanTIPOSInstru.getCodigoTextField().getText().equals("") || this.PanTIPOSInstru.getNombreTextField().getText().equals("") || this.PanTIPOSInstru.getUnidadTexttField().getText().equals("")) {
                JOptionPane.showMessageDialog(this.VenPricipal, "Debes completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (this.PanTIPOSInstru.getCodigoTextField().isEnabled()) {
                    TipoInstrumento t = new TipoInstrumento();
                    t.setCodigo(this.PanTIPOSInstru.getCodigoTextField().getText());
                    t.setNombre(this.PanTIPOSInstru.getNombreTextField().getText());
                    t.setUnidad(this.PanTIPOSInstru.getUnidadTexttField().getText());
                    this.admiTIPOSinstru.insertarTipoInstrumento(t);
                    JOptionPane.showMessageDialog(this.VenPricipal, t.getNombre() + " ingresado al sistemas", "Gardar", JOptionPane.INFORMATION_MESSAGE);

                    this.PanTIPOSInstru.getCodigoTextField().setText("");
                    this.PanTIPOSInstru.getNombreTextField().setText("");
                    this.PanTIPOSInstru.getUnidadTexttField().setText("");
                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                } else {//Editar 
                    int fila = this.admiTIPOSinstru.getLista().getElementoPos(this.PanTIPOSInstru.getCodigoTextField().getText());
                    TipoInstrumento t = this.admiTIPOSinstru.getLista().getLista().get(fila);
                    t.setNombre(this.PanTIPOSInstru.getNombreTextField().getText());
                    t.setUnidad(this.PanTIPOSInstru.getUnidadTexttField().getText());
                    this.admiTIPOSinstru.getModelo().setValueAt(t.getNombre(), fila, 1);
                    this.admiTIPOSinstru.getModelo().setValueAt(t.getUnidad(), fila, 2);
                    this.PanTIPOSInstru.getCodigoTextField().setText("");
                    this.PanTIPOSInstru.getNombreTextField().setText("");
                    this.PanTIPOSInstru.getUnidadTexttField().setText("");
                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                    this.PanTIPOSInstru.getCodigoTextField().setEnabled(true);

                }

            }
        }
        if (e.getSource().equals(this.PanTIPOSInstru.getBotonLimpiar())) {
            if (this.PanTIPOSInstru.getCodigoTextField().getText().equals("") || this.PanTIPOSInstru.getNombreTextField().getText().equals("") || this.PanTIPOSInstru.getUnidadTexttField().getText().equals("")) {

            } else {
                this.PanTIPOSInstru.getCodigoTextField().setEnabled(true);
                this.PanTIPOSInstru.getCodigoTextField().setText("");
                this.PanTIPOSInstru.getNombreTextField().setText("");
                this.PanTIPOSInstru.getUnidadTexttField().setText("");
                this.PanTIPOSInstru.getBotonBorrar().setEnabled(false);
            }
        }
        if (e.getSource().equals(this.PanTIPOSInstru.getBotonBorrar())) {
            if (this.PanTIPOSInstru.getCodigoTextField().isEnabled() == false) {
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
                    int fila = this.admiTIPOSinstru.getLista().getElementoPos(this.PanTIPOSInstru.getCodigoTextField().getText());
                    this.admiTIPOSinstru.getModelo().removeRow(fila);
                    this.admiTIPOSinstru.getLista().getLista().remove(fila);
                    this.PanTIPOSInstru.getCodigoTextField().setText("");
                    this.PanTIPOSInstru.getNombreTextField().setText("");
                    this.PanTIPOSInstru.getUnidadTexttField().setText("");
                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                    this.PanTIPOSInstru.getCodigoTextField().setEnabled(true);
                    JOptionPane.showMessageDialog(this.VenPricipal, "Tipo de instrumento eliminado con éxito", "Borrar", JOptionPane.INFORMATION_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(this.VenPricipal, "Selecciona un Tipo de instrumento ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource().equals(this.PanTIPOSInstru.getBotonBusqueda())) {
            if (this.PanTIPOSInstru.getNombreBusquedaTextFiled().getText().equals("")) {
                JOptionPane.showMessageDialog(this.VenPricipal, "Digitar dato para buscar tipo de instrumento", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                TipoInstrumento t = this.admiTIPOSinstru.getLista().buscaTipoInsturmento(this.PanTIPOSInstru.getNombreBusquedaTextFiled().getText());
                if (t != null) {
                    this.PanTIPOSInstru.getCodigoTextField().setEnabled(false);
                    this.PanTIPOSInstru.getCodigoTextField().setText(t.getCodigo());
                    this.PanTIPOSInstru.getNombreTextField().setText(t.getNombre());
                    this.PanTIPOSInstru.getUnidadTexttField().setText(t.getUnidad());
                    this.PanTIPOSInstru.getBotonBorrar().setEnabled(true);
                    this.PanTIPOSInstru.getNombreBusquedaTextFiled().setText("");
                    

                } else {
                    JOptionPane.showMessageDialog(this.VenPricipal, "Tipo de instrumento no resgistrado", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        if(e.getSource().equals(this.PanTIPOSInstru.getBotonReporte())){
            try {
                this.pdf=new PDFReportGenerator(this.admiTIPOSinstru.getLista());
                JOptionPane.showMessageDialog(this.VenPricipal, "Informe generado","Informe",JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
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
// hola
//hola
//holaaaaaa que dolor
