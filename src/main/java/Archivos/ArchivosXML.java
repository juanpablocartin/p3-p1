/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos;//oooo

import Modelo.Calibracion;
import Modelo.Instrumento;
import Modelo.ListaCalibraciones;
import Modelo.ListaINSTRUMENTOS;
import Modelo.ListaTipoInstrumento;
import Modelo.Medicion;
import Modelo.ModelTabINSTRUMENTOS;
import Modelo.ModelTabTipeInstrument;
import Modelo.TipoInstrumento;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Carlos
 */
public class ArchivosXML {

    public ArchivosXML() {

    }

    public void guardaTiposDeInstrumentos(ListaTipoInstrumento lista) {
        try {
            //----------------------------------------------
            Iterator<TipoInstrumento> itr = lista.getLista().iterator();
            TipoInstrumento aux = null;
            //----------------------------------------------

            Element tiposDeInstrumento = new Element("tiposDeInstrumento");
            Document doc = new Document(tiposDeInstrumento);

            Element tipo;
            Element codigo;
            Element nombre;
            Element unidad;

            while (itr.hasNext()) {
                aux = itr.next();
                tipo = new Element("tipoDeInstrumento");
                codigo = new Element("codigo");
                codigo.setText(aux.getCodigo());
                nombre = new Element("nombre");
                nombre.setText(aux.getNombre());
                unidad = new Element("unidad");
                unidad.setText(aux.getUnidad());

                tipo.addContent(codigo);
                tipo.addContent(nombre);
                tipo.addContent(unidad);

                tiposDeInstrumento.addContent(tipo);

            }
            XMLOutputter xml = new XMLOutputter();
            xml.setFormat(Format.getPrettyFormat());
            xml.output(doc, new FileWriter("tiposDeInstrumento.xml"));
        } catch (IOException ex) {
            Logger.getLogger(ArchivosXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leeTiposDeInstrumentos(String ruta, ModelTabTipeInstrument modelo) {
        try {

            SAXBuilder builder = new SAXBuilder();
            File xml = new File(ruta);
            Document document = builder.build(xml);
            Element root = document.getRootElement();//Se obtiene la raiz
            List<Element> list = root.getChildren();//toma todos los hijos 

            for (int i = 0; i < list.size(); i++) {
                Element tipo = list.get(i);

                String codigo = tipo.getChildTextTrim("codigo");
                String nombre = tipo.getChildTextTrim("nombre");
                String unidad = tipo.getChildTextTrim("unidad");

                TipoInstrumento t = new TipoInstrumento(codigo, nombre, unidad);
                modelo.insertarTipoInstrumento(t);
            }

        } catch (JDOMException ex) {
            Logger.getLogger(ArchivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivosXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void guardaInstrumentos(ListaINSTRUMENTOS lista) {
        try {
            Element RootInstrumentos = new Element("RootInstrumentos");
            Document doc = new Document(RootInstrumentos);

            Element Instrumentos = new Element("Instrumentos");
            RootInstrumentos.addContent(Instrumentos);

            Element instru = new Element("instru");
            //public Instrumento(String Serie, String Descripcion, int min, int max,int Tolerancia, TipoInstrumento tipDinstrumentos)
            for (int i = 0; i < lista.getArrayList().size(); i++) {
                Instrumento aux = null;
                aux = lista.getArrayList().get(i);
                Element Serie = new Element("Serie");
                Serie.setText(aux.getSerie());
                Element Descripcion = new Element("Descripcion");
                Descripcion.setText(aux.getDescripcion());
                Element min = new Element("min");
                min.setText(Integer.toString(aux.getMin()));
                Element max = new Element("max");
                max.setText(Integer.toString(aux.getMax()));
                Element Tolerancia = new Element("Tolerancia");
                Tolerancia.setText(Integer.toString(aux.getTolerancia()));
                ////////////////////////////////////////////////////////////////
                Element TipoInstrumento = new Element("TipoInstrumento");
                Element codigo = new Element("codigo");
                codigo.setText(aux.getTipDinstrumentos().getCodigo());
                Element nombre = new Element("nombre");
                nombre.setText(aux.getTipDinstrumentos().getNombre());
                Element unidad = new Element("unidad");
                unidad.setText(aux.getTipDinstrumentos().getUnidad());
                TipoInstrumento.addContent(codigo);
                TipoInstrumento.addContent(nombre);
                TipoInstrumento.addContent(unidad);
                ////////////////////////////////////////////////////////////////
                Element Calibraciones = new Element("Calibraciones");
                Element cali = new Element("cali");
                for (int j = 0; j < aux.getCalibracionesL().getList().tamano(); j++) {
                    Calibracion auxCali = null;
                    auxCali = aux.getCalibracionesL().getElementoPorPos(j);

                    Element num = new Element("num");
                    num.setText(auxCali.getNum());
                    Element numSerieInstrumento = new Element("numSerieInstrumento");
                    numSerieInstrumento.setText(auxCali.getNumSerieInstrumento());
                    Element fechaCalibracion = new Element("fechaCalibracion");
                    fechaCalibracion.setText(auxCali.getFechaCalibracion());
                    Element cantMediciones = new Element("cantMediciones");
                    cantMediciones.setText(Integer.toString(auxCali.getCantMediciones()));
                    ////////////////////////////////////////////////////////////////
                    Element mediciones = new Element("mediciones");
                    Element medi = new Element("medi");
                    for (int m = 0; m < aux.getCalibracionesL().getList().getCalibraciones().get(j).getMedicionesL().getMediciones().getMediciones().size(); m++) {
                        Medicion auxMedi = null;
                        auxMedi = aux.getCalibracionesL().getList().getCalibraciones().get(j).getMedicionesL().getMediciones().get(m);

                        Element numeroM = new Element("numeroM");
                        numeroM.setText(Integer.toString(auxMedi.getNumero()));
                        Element referencia = new Element("referencia");
                        referencia.setText(Integer.toString(auxMedi.getReferencia()));
                        Element lectura = new Element("lectura");
                        lectura.setText(Integer.toString(auxMedi.getLectura()));

                        medi.addContent(numeroM);
                        medi.addContent(referencia);
                        medi.addContent(lectura);
                        mediciones.addContent(medi);
                        ////////////////////////////////////////////////////////////////
                    }
                    cali.addContent(num);
                    cali.addContent(numSerieInstrumento);
                    cali.addContent(fechaCalibracion);
                    cali.addContent(cantMediciones);
                    cali.addContent(mediciones);
                    Calibraciones.addContent(cali);

                }
                ////////////////////////////////////////////////////////////////
                instru.addContent(Serie);
                instru.addContent(Descripcion);
                instru.addContent(min);
                instru.addContent(max);
                instru.addContent(Tolerancia);
                instru.addContent(TipoInstrumento);
                instru.addContent(Calibraciones);
                Instrumentos.addContent(instru);
            }
            XMLOutputter xml = new XMLOutputter();
            xml.setFormat(Format.getPrettyFormat());
            xml.output(doc, new FileWriter("Ainstrumentos.xml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void leeInstrumentos(String ruta, ModelTabINSTRUMENTOS modelo) {

        try {

            SAXBuilder builder = new SAXBuilder();
            File xml = new File(ruta);
            Document doc = builder.build(xml);
            
            Element root = doc.getRootElement();
            
            Element instrumentos = root.getChild("instrumentos");
            List<Element> listC, listM, list;
            Calibracion c = null;
            TipoInstrumento tI=null;
//            Medicion m = null;
            
            list = instrumentos.getChildren("instru");
            
            for (int i = 0; i < list.size(); i++) {
                Element instru = list.get(i);
                String serie = instru.getChildTextTrim("serie");
                String descripcion = instru.getChildTextTrim("descripcion");
                String min = instru.getChildTextTrim("min");
                String max = instru.getChildTextTrim("max");
                String tolerancia = instru.getChildTextTrim("tolerancia");
                
                ////////////////////////////////////////////////////////////////
                Element tipoInstrumento = instru.getChild("TipoInstrumento");
                String codigo = tipoInstrumento.getChildTextTrim("codigo");
                String nombre = tipoInstrumento.getChildTextTrim("nombre");
                String unidad = tipoInstrumento.getChildTextTrim("unidad");
                tI = new TipoInstrumento(codigo, nombre, unidad);
                /////////////////////////////////////////////////////////////////

                // crea instrumento con datos
                Instrumento inst = new Instrumento(serie, descripcion, Integer.parseInt(min),
                        Integer.parseInt(max), Integer.parseInt(tolerancia),
                        tI);

                // recuperar calibraciones:
                // solo se recuperan la fecha, la cantidad de mediciones y las mediciones
                Element calibraciones = instru.getChild("calibraciones");
                listC = calibraciones.getChildren();
                for (int j = 0; j < listC.size(); j++) {
                    Element cali = listC.get(j);
                    
                    String numSerieInstrumento = cali.getChildTextTrim("numSerieInstrumento");
                    String fecha = cali.getChildTextTrim("fecha");
                    String cantMediciones = cali.getChildTextTrim("cantMediciones");                  
                    
                    // crea la calibracion con todos los datos
                    c = new Calibracion(String.valueOf(j + 1), serie, fecha,
                            Integer.parseInt(cantMediciones));

                    
                    Element mediciones = cali.getChild("mediciones");
                    listM = mediciones.getChildren();
                    for (int k = 0; k < listM.size(); k++) {
                        Element medi = listM.get(k);
                        String numeroM = medi.getChildTextTrim("numeroM");
                        String referencia = medi.getChildTextTrim("referencia");
                        String lectura = medi.getChildTextTrim("lectura");
                        c.getMedicionesL().editarMedicion(k, Integer.parseInt(lectura));
                        c.getMedicionesL().getElemento(k).setNumero(k + 1);
                    }
                    c.setNumRefMediciones(Integer.parseInt(min), Integer.parseInt(max));
                }
                inst.getCalibracionesL().ingresar(c);
                modelo.insertarInstru(inst);
            }

        } catch (JDOMException ex) {
            Logger.getLogger(ArchivosXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivosXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
