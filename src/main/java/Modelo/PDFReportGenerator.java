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

public class PDFReportGenerator {

    private Document documento;
    private ListaTipoInstrumento lista;

    public PDFReportGenerator(ListaTipoInstrumento l) throws FileNotFoundException {

        lista = l;
        documento = new Document();
        this.generaDocumento();

    }

    private void generaDocumento() {
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("Informe.pdf"));
            documento.open();
            documento.setPageSize(A4);

            Iterator<TipoInstrumento> itr = lista.getLista().iterator();
            Paragraph titulo = new Paragraph("Reporte de tipos de instrumento");
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

    public Document getDocumento() {
        return documento;
    }

}
