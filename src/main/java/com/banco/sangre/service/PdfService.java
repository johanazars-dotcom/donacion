package com.banco.sangre.service;

import com.banco.sangre.dto.DonanteResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.awt.Color;

@Service
public class PdfService {

    public byte[] exportarDonantesPdf(List<DonanteResponse> donantes) throws Exception {
        Document document = new Document(PageSize.A4.rotate()); // Horizontal para que quepa todo
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        
        // Titulo
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Reporte de Donantes - Banco de Sangre", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" ")); 

        // Tabla
        PdfPTable table = new PdfPTable(5); // 5 columnas
        table.setWidthPercentage(100);
        
        // Encabezados
        String[] headers = {"Nombre", "Documento", "Tipo Sangre", "Peso", "Correo"};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        // Datos
        for (DonanteResponse d : donantes) {
            table.addCell(d.getNombres() + " " + d.getApellidos());
            table.addCell(d.getDocumento());
            table.addCell(d.getTipoSangre());
            table.addCell(d.getPeso().toString() + " kg");
            table.addCell(d.getCorreo());
        }

        document.add(table);
        document.close();
        return out.toByteArray();
    }
}