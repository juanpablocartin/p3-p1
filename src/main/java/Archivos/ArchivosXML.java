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
    public void guardaInstrumentos(ListaINSTRUMENTOS lI){

        
    }



}
