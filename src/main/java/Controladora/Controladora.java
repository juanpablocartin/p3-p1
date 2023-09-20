package Controladora;

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

public class Controladora implements ActionListener {

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

        // set listeners de botons calibraciones
        this.PanCali.getJBguardar().addActionListener(this);
        this.PanCali.getJBborrar().addActionListener(this);
        this.PanCali.getJBbuscar().addActionListener(this);
        this.PanCali.getJBlimpiar().addActionListener(this);
        this.PanCali.getJBReporte().addActionListener(this);
//        this.PanCali.get
        contCalibraciones = 0;

        listCalib = new ListaCalibraciones();
        listMed = new ListaMediciones();
        adminCalibraciones = new ModelTabCalibraciones(listCalib);
        this.PanCali.getJTableCalibraciones().setModel(adminCalibraciones.getModelo());
        adminMediciones = new ModeloTabMediciones(listMed);
        this.PanCali.getJTableMediciones().setModel(adminMediciones.getModelo());
//        instrumentoSeleccionado = new Instrumento();
//        adminMediciones.getModelo().
//        this.PanCali.getJTableMediciones().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    int filaSeleccionada = PanCali.getJTableMediciones().getSelectedRow();
//                    int colSeleccionada = PanCali.getJTableMediciones().getSelectedColumn();
//                    if (filaSeleccionada != -1) {
//                        if (adminMediciones.celdaEditable(colSeleccionada)) {
//                            String s = adminMediciones.getModelo().getValueAt(filaSeleccionada, 2).toString();
//                            adminMediciones.editarMedicion(filaSeleccionada, Integer.parseInt(s));
//                        }
//                    }
//                }
//            }
//
//        });

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

                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
                    this.PanTIPOSInstru.getCodigoTextField().setText("");
                    this.PanTIPOSInstru.getNombreTextField().setText("");
                    this.PanTIPOSInstru.getUnidadTexttField().setText("");
                    admiTIPOSinstru.modificaCOMBOBOX(PanInstru.getTxCB_Tipo());
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
                System.out.println(this.admiTIPOSinstru.getTipDinstruXnombre(PanInstru.getTxCB_Tipo().getSelectedItem().toString()).toString());
                System.out.println("\n");

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
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro", "Error", JOptionPane.ERROR_MESSAGE);
                }

                return;

            }
        }

//--------------------------------------------------------------------------------
        if (e.getSource().equals(PanInstru.getbBuscar())) {

            if (PanInstru.getTxDescriAbuscar().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe poner una Descripcion a buscar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String aux = "";
                int auxSerieINDEX = -1;
                aux = admiinstru.getSerieDEdecripcionX(PanInstru.getTxDescriAbuscar().getText());

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
                        } else {
                            PanInstru.getTxSerie().setText("");
                            PanInstru.getTxTolerancia().setText("");
                            PanInstru.getTxMin().setText("");
                            PanInstru.getTxMax().setText("");
                            PanInstru.getTxDescripcion().setText("");
                            PanInstru.getTxCB_Tipo().setSelectedIndex(-1);
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro", "Error", JOptionPane.ERROR_MESSAGE);
                }

                return;

            }
        }
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

        }

//--------------------------------------------------------------------------------
        if (e.getSource().equals(PanInstru.getbBorrar())) {
            if (PanInstru.getTablaDInstrumentos().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar un Instrumento de la tabla para borrar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                admiinstru.getLista().getElemento(PanInstru.getTablaDInstrumentos().getSelectedRow());
                admiinstru.borrarRegistro(PanInstru.getTablaDInstrumentos().getSelectedRow());
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
            MANIinstrumrnto = admiinstru.getInstruCONserie(aux);
            StringBuilder s = new StringBuilder();
            s.append(this.MANIinstrumrnto.getSerie() + " - " + this.MANIinstrumrnto.getDescripcion() + "(" + this.MANIinstrumrnto.getMin() + " - " + this.MANIinstrumrnto.getMax() + " Grados celcius)");
            PanCali.getJLInstrumentoSeleccionado().setText(s.toString());
            this.adminCalibraciones.setList(MANIinstrumrnto.getCalibraciones());
            this.adminCalibraciones.actualizarTabla();

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
                int pos = adminCalibraciones.getList().buscarPorNum(s);
                actualizarMediciones(pos);
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
//            this.adminMediciones.getModelo().setRowCount(0);
            this.PanCali.getJTableCalibraciones().clearSelection();

        }

        if (e.getSource().equals(this.PanCali.getJBborrar())) {
            if (PanCali.getTFbuscarPorNumero().getText().compareTo("") != 0) {
                String s = this.PanCali.getTFbuscarPorNumero().getText();
                int filaSeleccionada = adminCalibraciones.getList().buscarPorNum(s);
                adminCalibraciones.eliminarPorPos(filaSeleccionada);
                PanCali.getJTableCalibraciones().setModel(adminCalibraciones.getModelo());

                // limpiar TFs
                this.PanCali.getTFbuscarPorNumero().setText("");
                this.PanCali.getTFnumeroCalibracion().setText("");
                this.PanCali.getTFmedicionesCalibracion().setText("");
                this.PanCali.getTFfechaCalibracion().setText("");
                PanCali.getJBborrar().setEnabled(false);
                this.adminCalibraciones.setList(new ListaCalibraciones());
                adminCalibraciones.actualizarTabla();
                this.adminMediciones.setMediciones(new ListaMediciones());
                adminMediciones.actualizarTabla();
            }

        }
        if (e.getSource().equals(this.PanCali.getJBReporte())) {
            try {
                JOptionPane.showMessageDialog(this.VenPricipal, "Informe generado", "Informe", JOptionPane.INFORMATION_MESSAGE);
                this.pdf = new PDFReportGenerator(adminCalibraciones.getList());

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(this.PanCali.getJBbuscar())) {
            String s = this.PanCali.getTFbuscarPorNumero().getText();
            if (this.adminCalibraciones.getList().existe(s)) {
                int pos = adminCalibraciones.getList().buscarPorNum(s);
                this.PanCali.getJTableCalibraciones().setRowSelectionInterval(pos, pos);
                PanCali.getJBborrar().setEnabled(true);
                PanCali.getTFnumeroCalibracion().setText(s);
                PanCali.getTFfechaCalibracion().setText(this.adminCalibraciones.getElementoPorPos(pos).getFechaCalibracion());
                String cantM = String.valueOf(this.adminCalibraciones.getElementoPorPos(pos).getCantMediciones());
                PanCali.getTFmedicionesCalibracion().setText(cantM);
                adminMediciones.setMediciones(adminCalibraciones.getElementoPorPos(pos).getMediciones());
                adminMediciones.actualizarTabla();
                PanCali.getJTableMediciones().setModel(adminMediciones.getModelo());

            } else {
                JOptionPane.showMessageDialog(null, "No hay coincidencias", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void actualizarMediciones(int pos) {
        int filaSeleccionada = PanCali.getJTableMediciones().getSelectedRow();
        int colSeleccionada = PanCali.getJTableMediciones().getSelectedColumn();
//        adminMediciones.setMediciones(adminCalibraciones.getList().get(pos).getMediciones());
//        adminMediciones.actualizarTabla();
        if (filaSeleccionada > -1) {
            if (adminMediciones.celdaEditable(colSeleccionada)) {
                String s = adminMediciones.getModelo().getValueAt(filaSeleccionada, 2).toString();
                adminMediciones.editarMedicion(filaSeleccionada, Integer.parseInt(s));
            }
        }

    }

    public void agregarCalibracion() {
        Calibracion c;
        contCalibraciones++;
        String s = String.valueOf(contCalibraciones);
        c = new Calibracion(s, "", PanCali.getTFfechaCalibracion().getText(), Integer.parseInt(PanCali.getTFmedicionesCalibracion().getText()));
        c.setMediciones(new ListaMediciones(c.getCantMediciones()));
        c.numReferenciaMed(this.MANIinstrumrnto.getMin(), this.MANIinstrumrnto.getMax());
        this.adminCalibraciones.ingresar(c);
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
