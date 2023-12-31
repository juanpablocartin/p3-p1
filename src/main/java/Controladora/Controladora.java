package Controladora;

import Archivos.ArchivosXML;
import Modelo.Calibracion;
//import Modelo.Fecha;
import Modelo.Instrumento;
import Modelo.ListaCalibraciones;
import Modelo.ListaMediciones;
import Modelo.ListaTipoInstrumento;
import Modelo.Medicion;
import Modelo.ModelTabCalibraciones;
import Modelo.ModelTabINSTRUMENTOS;
import Modelo.ModelTabTipeInstrument;
import Modelo.ModeloTabMediciones;
import Modelo.PDFReportGenerator;
import Modelo.TipoInstrumento;
import Vista.CalibracionesJPanel;
import Vista.InstruJPanel;
import Vista.TipInstruJPanel;
import Vista.VenPri;
import java.awt.Color;
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
import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controladora implements ActionListener {//lililil

    private VenPri VenPricipal;
    private TipInstruJPanel PanTIPOSInstru;
    private InstruJPanel PanInstru;
    private CalibracionesJPanel PanCali;

    private ModelTabINSTRUMENTOS admiinstru;
    private ModelTabTipeInstrument admiTIPOSinstru;

    //------------------------------------------------
    private ListaTipoInstrumento listaTipos;
    private PDFReportGenerator pdf;

    private Instrumento MANIinstrumrnto = null;
    //Lista global de tipos de instrumentos
    //-------------------------------------------------
    // -------------------------------- Calibraciones y mediciones ----------------------------------------
    ListaCalibraciones listCalib;
    ListaMediciones listMed;
    int contCalibraciones;
    private ModelTabCalibraciones adminCalibraciones;
    private ModeloTabMediciones adminMediciones;
    Instrumento instrumentoSeleccionado;

    private ArchivosXML archivos;

    public Controladora(VenPri v, TipInstruJPanel ti, InstruJPanel i, CalibracionesJPanel c) {

        this.VenPricipal = v;
        v.setVisible(true);
        this.PanTIPOSInstru = ti;

        this.PanInstru = i;
        PanInstru.getbEditar().setEnabled(false);
        PanInstru.getbEditarCali().setEnabled(false);

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
        this.PanInstru.getbGuardar().addActionListener(this);
        this.PanInstru.getbLimpiar().addActionListener(this);
        this.PanInstru.getbBorrar().addActionListener(this);
        this.PanInstru.getbBuscar().addActionListener(this);
        this.PanInstru.getbReporte().addActionListener(this);
        this.PanInstru.getbEditar().addActionListener(this);

        this.PanInstru.getbEditarCali().addActionListener(this);
        //this.VenPricipal.getTABpri().(this);

        this.PanTIPOSInstru.getBotonGuardar().addActionListener(this);
        this.PanTIPOSInstru.getBotonLimpiar().addActionListener(this);
        ///////Todo lo que va a escuchar/////////////

        this.PanTIPOSInstru.getBotonGuardar().addActionListener(this);
        this.PanTIPOSInstru.getBotonLimpiar().addActionListener(this);
        this.PanTIPOSInstru.getBotonBorrar().addActionListener(this);
        this.PanTIPOSInstru.getBotonBusqueda().addActionListener(this);
        this.PanTIPOSInstru.getBotonReporte().addActionListener(this);

        // limpiar text fields calibraciones
        this.PanCali.getTFbuscarPorNumero().setText("");
        this.PanCali.getTFfechaCalibracion().setText("");
        this.PanCali.getTFmedicionesCalibracion().setText("");
        this.PanCali.getJBborrar().setEnabled(false);
        this.PanCali.getTFnumeroCalibracion().setEnabled(false);
        this.PanCali.getTFnumMedicion().setEnabled(false);
        this.PanCali.getJBguardar().setEnabled(false);
        this.PanCali.getJBlimpiar().setEnabled(false);
        this.PanCali.getJBReporte().setEnabled(false);
        this.PanCali.getJBbuscar().setEnabled(false);

        // set listeners de botones calibraciones
        this.PanCali.getJBguardar().addActionListener(this);
        this.PanCali.getJBborrar().addActionListener(this);
        this.PanCali.getJBbuscar().addActionListener(this);
        this.PanCali.getJBlimpiar().addActionListener(this);
        this.PanCali.getJBReporte().addActionListener(this);

        contCalibraciones = 0;

        listCalib = new ListaCalibraciones();
        listMed = new ListaMediciones();
        adminCalibraciones = new ModelTabCalibraciones(listCalib);
        this.PanCali.getJTableCalibraciones().setModel(adminCalibraciones.getModelo());
        adminMediciones = new ModeloTabMediciones(listMed);
        this.PanCali.getJTableMediciones().setModel(adminMediciones.getModelo());
//        instrumentoSeleccionado = new Instrumento();
//        adminMediciones.getModelo().
        this.PanCali.getJTableMediciones().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int medSeleccionada = PanCali.getJTableMediciones().getSelectedRow();
                    int calibSeleccionada = PanCali.getJTableCalibraciones().getSelectedRow();
                    if (medSeleccionada > -1) {
                        Medicion m = MANIinstrumrnto.getCalibracionesL().getElementoPorPos(calibSeleccionada).getMedicionesL().getElemento(medSeleccionada);
                        PanCali.getTFnumMedicion().setText(String.valueOf(m.getNumero()));
                        PanCali.getTFnumLectura().setText(String.valueOf(m.getLectura()));
                    }
                }
            }

        });

        //Archivos---------------------------------------------------------------
        archivos = new ArchivosXML();
        archivos.leeTiposDeInstrumentos("tiposDeInstrumento.xml", admiTIPOSinstru);
        admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
        //------------------------------------------------------------------------
        VenPricipal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        VenPricipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                archivos.guardaInstrumentos(admiinstru.getLista());
            }
        });
        
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
                    JOptionPane.showMessageDialog(this.VenPricipal, t.getNombre() + " ingresado al sistemas", "Grdar", JOptionPane.INFORMATION_MESSAGE);

                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                    this.PanTIPOSInstru.getCodigoTextField().setText("");
                    this.PanTIPOSInstru.getNombreTextField().setText("");
                    this.PanTIPOSInstru.getUnidadTexttField().setText("");
                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                    this.PanTIPOSInstru.getCodigoTextField().setText("");
                    this.PanTIPOSInstru.getNombreTextField().setText("");
                    this.PanTIPOSInstru.getUnidadTexttField().setText("");
                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                    this.archivos.guardaTiposDeInstrumentos(this.admiTIPOSinstru.getLista());
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
                    this.archivos.guardaTiposDeInstrumentos(this.admiTIPOSinstru.getLista());

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
                    this.archivos.guardaTiposDeInstrumentos(this.admiTIPOSinstru.getLista());
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
        if (e.getSource().equals(this.PanTIPOSInstru.getBotonReporte())) {
            try {
                this.pdf = new PDFReportGenerator(this.admiTIPOSinstru.getLista());
                JOptionPane.showMessageDialog(this.VenPricipal, "Informe generado", "Informe", JOptionPane.INFORMATION_MESSAGE);
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
                Instrumento auxInstru = new Instrumento(
                        PanInstru.getTxSerie().getText(),
                        PanInstru.getTxDescripcion().getText(),
                        Integer.parseInt(PanInstru.getTxMin().getText()),
                        Integer.parseInt(PanInstru.getTxMax().getText()),
                        Integer.parseInt(PanInstru.getTxTolerancia().getText()),
                        this.admiTIPOSinstru.getTipDinstruXnombre(PanInstru.getTxCB_Tipo().getSelectedItem().toString()));
                admiinstru.insertarInstru(auxInstru);
                PanInstru.getTxSerie().setText("");
                PanInstru.getTxTolerancia().setText("");
                PanInstru.getTxMin().setText("");
                PanInstru.getTxMax().setText("");
                PanInstru.getTxDescripcion().setText("");
                PanInstru.getTxCB_Tipo().setSelectedIndex(-1);

            }
            return;
        }
//--------------------------------------------------------------------------------
        if (e.getSource().equals(PanInstru.getbLimpiar())) {
            PanInstru.getTxSerie().setText("");
            PanInstru.getTxTolerancia().setText("");
            PanInstru.getTxMin().setText("");
            PanInstru.getTxMax().setText("");
            PanInstru.getTxDescripcion().setText("");
            PanInstru.getTxCB_Tipo().setSelectedIndex(-1);

            return;
        }
        if (e.getSource().equals(PanInstru.getbBuscar())) {
            if (PanInstru.getTxDescriAbuscar().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe poner una Descripcion a buscar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String aux = "";
                int auxSerieINDEX = -1;
                aux = admiinstru.getSerieDEdecripcionX(PanInstru.getTxDescriAbuscar().getText());
                //Demaciado Importante ///////////////////////////////////////!!!!!!!!!!!!!!!!!!!!
                MANIinstrumrnto = admiinstru.getInstruCONserie(aux);
                //Demaciado Importante ///////////////////////////////////////!!!!!!!!!!!!!!!!!!!!
                if (aux != "") {
                    if (admiinstru.BuscarXserie(aux) == true) {

                        int siOno;
                        siOno = JOptionPane.showConfirmDialog(null, "EXITO SE ENCONTRO"
                                + "\nLe gustaria editar dicho instrumento ? ", "|-Si=Yes-| |-No=No-|", JOptionPane.YES_NO_OPTION);

                        if (siOno == JOptionPane.YES_OPTION) {
                            PanInstru.getTxDescriAbuscar().setEditable(false);
                            PanInstru.getTxSerie().setText(admiinstru.getInstruCONserie(aux).getSerie());
                            PanInstru.getTxDescripcion().setText(admiinstru.getInstruCONserie(aux).getDescripcion());
                            PanInstru.getTxMin().setText(Integer.toString(admiinstru.getInstruCONserie(aux).getMin()));
                            PanInstru.getTxMax().setText(Integer.toString(admiinstru.getInstruCONserie(aux).getMax()));
                            PanInstru.getTxTolerancia().setText(Integer.toString(admiinstru.getInstruCONserie(aux).getTolerancia()));
                            PanInstru.getTxCB_Tipo().setSelectedIndex(
                                    admiTIPOSinstru.getIndexDtipoInstruXcodigo(admiinstru.getInstruCONserie(aux).getTipDinstrumentos().getCodigo())
                            );
                            PanInstru.getbEditar().setEnabled(true);
                            PanInstru.getbEditarCali().setEnabled(true);
                        } else {
                            PanInstru.getTxSerie().setText("");
                            PanInstru.getTxTolerancia().setText("");
                            PanInstru.getTxMin().setText("");
                            PanInstru.getTxMax().setText("");
                            PanInstru.getTxDescripcion().setText("");
                            PanInstru.getTxCB_Tipo().setSelectedIndex(-1);
                        }

                    }
                    StringBuilder s = new StringBuilder();
                    s.append(this.MANIinstrumrnto.getSerie() + " - " + this.MANIinstrumrnto.getDescripcion() + "(" + this.MANIinstrumrnto.getMin() + " - " + this.MANIinstrumrnto.getMax() + " Grados celcius)");
                    PanCali.getJLInstrumentoSeleccionado().setText(s.toString());
                    this.adminCalibraciones = MANIinstrumrnto.getCalibracionesL();
                    this.adminCalibraciones.actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro", "Error", JOptionPane.ERROR_MESSAGE);
                }

                return;

            }
        }

//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
        if (e.getSource().equals(PanInstru.getbEditar())) {
            String auxSerie;
            auxSerie = admiinstru.getSerieDEdecripcionX(PanInstru.getTxDescriAbuscar().getText());

            int auxSerieINDEX = -1;
            auxSerieINDEX = admiinstru.getIndexDEdescripcion(PanInstru.getTxDescriAbuscar().getText());
            admiinstru.update(auxSerieINDEX,
                    PanInstru.getTxSerie().getText(),
                    PanInstru.getTxDescripcion().getText(),
                    Integer.parseInt(PanInstru.getTxMin().getText()),
                    Integer.parseInt(PanInstru.getTxMax().getText()),
                    Integer.parseInt(PanInstru.getTxTolerancia().getText()),
                    admiTIPOSinstru.getTipDinstruXnombre(PanInstru.getTxCB_Tipo().getSelectedItem().toString()));
            PanInstru.getTxSerie().setText("");
            PanInstru.getTxTolerancia().setText("");
            PanInstru.getTxMin().setText("");
            PanInstru.getTxMax().setText("");
            PanInstru.getTxDescripcion().setText("");
            PanInstru.getTxCB_Tipo().setSelectedIndex(-1);
            PanInstru.getTxDescriAbuscar().setText("");
            PanInstru.getTxDescriAbuscar().setEditable(true);
            PanInstru.getbEditar().setEnabled(false);
            PanInstru.getbEditarCali().setEnabled(false);
            
        }

//--------------------------------------------------------------------------------
        if (e.getSource().equals(PanInstru.getbBorrar())) {
            if (PanInstru.getTablaDInstrumentos().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar un Instrumento de la tabla para borrar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if(admiinstru.getLista().getElemento(PanInstru.getTablaDInstrumentos().getSelectedRow()).getCalibracionesL().getList().tamano()>0){
                    JOptionPane.showMessageDialog(null, "No se puede borrar un instrumento con calibracciones", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    //admiinstru.getLista().getElemento(PanInstru.getTablaDInstrumentos().getSelectedRow());
                    admiinstru.borrarRegistro(PanInstru.getTablaDInstrumentos().getSelectedRow());
                    //despues de Borrar
                }
            }
        }
//--------------------------------------------------------------------------------
        if (e.getSource().equals(this.PanInstru.getbReporte())) {
            try {
                this.pdf = new PDFReportGenerator(this.admiinstru.getLista());
                JOptionPane.showMessageDialog(this.VenPricipal, "Informe generado", "Informe", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//--------------------------------------------------------------------------------
        if (e.getSource().equals(this.PanInstru.getbEditarCali())) {
            VenPricipal.getTABpri().setSelectedIndex(2);
            String aux = "";
            aux = admiinstru.getSerieDEdecripcionX(PanInstru.getTxDescriAbuscar().getText());
            this.PanCali.getJBguardar().setEnabled(true);
            this.PanCali.getJBlimpiar().setEnabled(true);
            this.PanCali.getJBReporte().setEnabled(true);
            this.PanCali.getJBbuscar().setEnabled(true);
            StringBuilder s = new StringBuilder();
            s.append(this.MANIinstrumrnto.getSerie() + " - " + this.MANIinstrumrnto.getDescripcion() + "(" + this.MANIinstrumrnto.getMin() + " - " + this.MANIinstrumrnto.getMax() + " Grados celcius)");
            PanCali.getJLInstrumentoSeleccionado().setText(s.toString());
            this.PanCali.getJTableCalibraciones().setModel(MANIinstrumrnto.getCalibracionesL().getModelo());
            
            
            PanInstru.getTxSerie().setText("");
            PanInstru.getTxTolerancia().setText("");
            PanInstru.getTxMin().setText("");
            PanInstru.getTxMax().setText("");
            PanInstru.getTxDescripcion().setText("");
            PanInstru.getTxCB_Tipo().setSelectedIndex(-1);
            PanInstru.getTxDescriAbuscar().setText("");
            PanInstru.getTxDescriAbuscar().setEditable(true);
            PanInstru.getbEditar().setEnabled(false);
            PanInstru.getbEditarCali().setEnabled(false);
        }
//--------------------------------------------------------------------------------

        if (e.getSource().equals(this.PanCali.getJBguardar())) {
            if (!PanCali.getJBborrar().isEnabled()) {
                if (PanCali.getTFfechaCalibracion().getText().compareTo("") != 0 && PanCali.getTFmedicionesCalibracion().getText().compareTo("") != 0) {
                    agregarCalibracion();
                    this.PanCali.getTFnumeroCalibracion().setText("");
                    this.PanCali.getTFmedicionesCalibracion().setText("");
                    this.PanCali.getTFfechaCalibracion().setText("");
                } else {
                    JOptionPane.showMessageDialog(this.VenPricipal, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String s = this.PanCali.getTFbuscarPorNumero().getText();
                int pos = MANIinstrumrnto.getCalibracionesL().getList().buscarPorNum(s);
                actualizarMediciones(pos);
                adminMediciones.setMediciones(MANIinstrumrnto.getCalibracionesL().getElementoPorPos(pos).getMedicionesL().getMediciones());

            }

        }
        if (e.getSource().equals(this.PanCali.getJBlimpiar())) {
            this.PanCali.getTFnumeroCalibracion().setText("");
            this.PanCali.getTFmedicionesCalibracion().setText("");
            this.PanCali.getTFfechaCalibracion().setText("");
            PanCali.getJBborrar().setEnabled(false);
            this.PanCali.getTFbuscarPorNumero().setText("");
            this.adminMediciones.setMediciones(new ListaMediciones());
            adminMediciones.actualizarTabla();
            this.PanCali.getJTableMediciones().setModel(adminMediciones.getModelo());
            PanCali.getJTableCalibraciones().clearSelection();
            PanCali.getJTableMediciones().clearSelection();
            this.PanCali.getTFnumLectura().setText("");
            this.PanCali.getTFnumMedicion().setText("");

        }

        if (e.getSource().equals(this.PanCali.getJBborrar())) {
            if (PanCali.getTFbuscarPorNumero().getText().compareTo("") != 0) {
                String s = this.PanCali.getTFbuscarPorNumero().getText();

                int filaSeleccionada = MANIinstrumrnto.getCalibracionesL().getList().buscarPorNum(s);
                MANIinstrumrnto.getCalibracionesL().eliminarPorPos(filaSeleccionada);
                PanCali.getJTableCalibraciones().setModel(MANIinstrumrnto.getCalibracionesL().getModelo());

                // limpiar TFs
                this.PanCali.getTFbuscarPorNumero().setText("");
                this.PanCali.getTFnumeroCalibracion().setText("");
                this.PanCali.getTFmedicionesCalibracion().setText("");
                this.PanCali.getTFfechaCalibracion().setText("");
                PanCali.getJBborrar().setEnabled(false);

                this.adminMediciones.setMediciones(new ListaMediciones());
                adminMediciones.actualizarTabla();
            }

        }
        if (e.getSource().equals(this.PanCali.getJBReporte())) {
            try {
                JOptionPane.showMessageDialog(this.VenPricipal, "Informe generado", "Informe", JOptionPane.INFORMATION_MESSAGE);
                this.pdf = new PDFReportGenerator(MANIinstrumrnto.getCalibracionesL().getList());

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(this.PanCali.getJBbuscar())) {
            String s = this.PanCali.getTFbuscarPorNumero().getText();
            if (this.MANIinstrumrnto.getCalibracionesL().getList().existe(s)) {
                int pos = MANIinstrumrnto.getCalibracionesL().getList().buscarPorNum(s);
                this.PanCali.getJTableCalibraciones().setRowSelectionInterval(pos, pos);
                PanCali.getJBborrar().setEnabled(true);
                PanCali.getTFnumeroCalibracion().setText(s);
                PanCali.getTFfechaCalibracion().setText(this.MANIinstrumrnto.getCalibracionesL().getElementoPorPos(pos).getFechaCalibracion());
                String cantM = String.valueOf(this.MANIinstrumrnto.getCalibracionesL().getElementoPorPos(pos).getCantMediciones());
                PanCali.getTFmedicionesCalibracion().setText(cantM);
                adminMediciones.setMediciones(MANIinstrumrnto.getCalibracionesL().getElementoPorPos(pos).getMedicionesL().getMediciones());
                adminMediciones.actualizarTabla();
                PanCali.getJTableMediciones().setModel(adminMediciones.getModelo());

            } else {
                JOptionPane.showMessageDialog(null, "No hay coincidencias", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void actualizarMediciones(int pos) {
        int fila = PanCali.getJTableMediciones().getSelectedRow();
        String s = PanCali.getTFnumLectura().getText();
        MANIinstrumrnto.getCalibracionesL().getElementoPorPos(pos).getMedicionesL().editarMedicion(fila, Integer.parseInt(s));
        MANIinstrumrnto.getCalibracionesL().getElementoPorPos(pos).getMedicionesL().actualizarTabla();
        this.PanCali.getJTableMediciones().setModel(MANIinstrumrnto.getCalibracionesL().getElementoPorPos(pos).getMedicionesL().getModelo());
    }

    public void agregarCalibracion() {
        Calibracion c;
        contCalibraciones++;
        String s = String.valueOf(contCalibraciones);
        c = new Calibracion(s, "", PanCali.getTFfechaCalibracion().getText(), Integer.parseInt(PanCali.getTFmedicionesCalibracion().getText()));
        c.setMedicionesL(new ModeloTabMediciones(c.getCantMediciones()));
        c.setNumLecturaMediciones(this.MANIinstrumrnto.getMin(), this.MANIinstrumrnto.getMax());
        this.MANIinstrumrnto.getCalibracionesL().ingresar(c);
    }

    public void datosQuemados() {
        Calibracion c1 = new Calibracion("111", "111", "111", 2);
        c1.getMedicionesL().getMediciones().get(0).setLectura(1);
        c1.getMedicionesL().getMediciones().get(0).setNumero(1);
        c1.getMedicionesL().getMediciones().get(0).setReferencia(1);

        c1.getMedicionesL().getMediciones().get(1).setLectura(2);
        c1.getMedicionesL().getMediciones().get(1).setNumero(2);
        c1.getMedicionesL().getMediciones().get(1).setReferencia(2);

        Calibracion c2 = new Calibracion("222", "222", "222", 2);
        c2.getMedicionesL().getMediciones().get(0).setLectura(3);
        c2.getMedicionesL().getMediciones().get(0).setNumero(3);
        c2.getMedicionesL().getMediciones().get(0).setReferencia(3);

        c2.getMedicionesL().getMediciones().get(1).setLectura(4);
        c2.getMedicionesL().getMediciones().get(1).setNumero(5);
        c2.getMedicionesL().getMediciones().get(1).setReferencia(4);

        MANIinstrumrnto.getCalibracionesL().ingresar(c1);
        MANIinstrumrnto.getCalibracionesL().ingresar(c2);

    }

}

//estando en la que quiero cambiar "PULL" de esa misma rama pero del git
//despues mearge revision y seleciono el ultimo comite de donde voy a sacr la info nueva 
//despues accepto o no cosas 
//y despues comit+push con los cambios UNIDOS y listo :)
//////////  Lest seee
//hola
//hola
//hola hola
//holaaaaaa que dolor
