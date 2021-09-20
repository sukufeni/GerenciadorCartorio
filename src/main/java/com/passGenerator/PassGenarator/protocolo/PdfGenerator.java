package com.passGenerator.PassGenarator.protocolo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

    public static ByteArrayInputStream generateProtocolo(Protocolo protocolo) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[] { 1, 3, 3 });

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Paragraph title = new Paragraph("chunk");
            title.setAlignment(Element.ALIGN_CENTER);

            Phrase dataEntrega = new Phrase(
                    "Encontra-se Protocolado neste Cartório na data de: " + protocolo.getDataCriacao());

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(title);
            document.addHeader("titulo", "Recibo de Protocolo");
            document.add(dataEntrega);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}