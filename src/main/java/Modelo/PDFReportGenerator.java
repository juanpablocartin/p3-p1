/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Carlos
 */
import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import static com.itextpdf.text.PageSize.A4;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.Iterator;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.Element;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFReportGenerator {

    private Document documento;
    private ListaTipoInstrumento lista;
    private ListaINSTRUMENTOS listaInstru;
    private ListaCalibraciones listaCalib;
    private ListaMediciones listaMed;

    public PDFReportGenerator(ListaTipoInstrumento l) throws FileNotFoundException {

        lista = l;
        documento = new Document();
        this.generaDocumento();

    }

    public PDFReportGenerator(ListaINSTRUMENTOS l) throws FileNotFoundException {

        listaInstru = l;
        documento = new Document();
        this.generaDocumento2();

    }

    public PDFReportGenerator(ListaCalibraciones listaCalib) throws FileNotFoundException {
        this.listaCalib = listaCalib;
        documento = new Document();
        this.generaDocumento3();

    }

    private void generaDocumento() {
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("InformeTipos.pdf"));
            documento.open();
            documento.setPageSize(A4);

            Iterator<TipoInstrumento> itr = lista.getLista().iterator();
            Paragraph titulo = new Paragraph("Reporte tipos de instrumento");
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20f);
            documento.add(new com.itextpdf.text.pdf.draw.LineSeparator(0.5f, 100, null, 0, -5f));

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell(new Paragraph("Código"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("Nombre"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("Unidad"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            TipoInstrumento t;
            while (itr.hasNext()) {
                t = itr.next();
                tabla.addCell(t.getCodigo());
                tabla.addCell(t.getNombre());
                tabla.addCell(t.getUnidad());
            }
            documento.add(titulo);

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generaDocumento2() {
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("InformeInstrumentos.pdf"));
            documento.open();
            documento.setPageSize(A4);

            Iterator<Instrumento> itr = listaInstru.getArrayList().iterator();
            Paragraph titulo = new Paragraph("Reporte tipos de instrumento");
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20f);
            documento.add(new com.itextpdf.text.pdf.draw.LineSeparator(0.5f, 100, null, 0, -5f));

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell(new Paragraph("Serie"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("Descripcion"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("min"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("max"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("Tolerancia"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            cell = new PdfPCell(new Paragraph("tipDinstrumentos"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(cell);
            ////---------------------------------------------------------------------------------
            ////---------------------------------------------------------------------------------
            Instrumento t;

            while (itr.hasNext()) {
                t = itr.next();
                tabla.addCell(t.getSerie());
                tabla.addCell(t.getDescripcion());
                tabla.addCell(Integer.toString(t.getMin()));
                tabla.addCell(Integer.toString(t.getMax()));
                tabla.addCell(Integer.toString(t.getTolerancia()));
                tabla.addCell(t.getTipDinstrumentos().getCodigo());
            }
            documento.add(titulo);

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generaDocumento3() {
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("InformeCalibraciones.pdf"));
            documento.open();
            documento.setPageSize(A4);

            Iterator<Calibracion> it = listaCalib.getCalibraciones().iterator();
            Iterator<Medicion> it2;

            Paragraph titulo = new Paragraph("********Reporte calibraciones*********");
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(30f);
            documento.add(new com.itextpdf.text.pdf.draw.LineSeparator(0.5f, 100, null, 0, -5f));
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            
            PdfPCell cell = new PdfPCell(new Paragraph("Numero"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tabla.addCell(cell);

            cell = new PdfPCell(new Paragraph("Fecha"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tabla.addCell(cell);

            cell = new PdfPCell(new Paragraph("Mediciones"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tabla.addCell(cell);

            if (!listaCalib.getCalibraciones().isEmpty()) {
                Calibracion c;
                while (it.hasNext()) {
                    c = it.next();
                    tabla.addCell(c.getNum());
                    tabla.addCell(c.getFechaCalibracion());
                    tabla.addCell(String.valueOf(c.getCantMediciones()));
                }
            }
            documento.add(titulo);
            documento.add(tabla);
            documento.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Document getDocumento() {
        return documento;
    }

}
