package com.passGenerator.PassGenarator.protocolo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.passGenerator.PassGenarator.Cartorio.Cartorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

public class PdfGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

    public static ByteArrayInputStream generateProtocolo(Protocolo protocolo, String apresentante, Cartorio emitente) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            String titulo = new StringBuilder(emitente.getNomeCartorio() + "\n")
                    .append("CNPJ: " + emitente.getCodigoCartorio() + " \n")
                    .append("Rua Carmélio Santos, 28 - Centro, CEP: 45.780-000 \n")
                    .append("Itarantim - Bahia - Telefone (73) 3266-2537 \n").toString();
            Paragraph title = new Paragraph(titulo);
            title.setAlignment(Element.ALIGN_CENTER);

            Phrase dataEntrega = new Phrase(
                    "Encontra-se protocolado neste Cartório na data de: " + protocolo.getDataCriacao());

            String mainBlockString = new StringBuilder("Apresentante: " + apresentante + "\n")
                    .append("Titulo: " + protocolo.getQualidadeProtocolo() + "\n").append("Situação: Vigência \n")
                    .append("Data: " + protocolo.getDataEntrega() + "                                   Protocolo: "
                            + protocolo.getId())
                    .toString();
            Paragraph mainBlock = new Paragraph(mainBlockString);

            Paragraph dataAtual = new Paragraph("Itarantim, " + LocalDate.now().toString());
            dataAtual.setAlignment(Element.ALIGN_RIGHT);

            Paragraph footer = new Paragraph(
                    "_______________________________________________________________ \n" + apresentante);
            footer.setAlignment(Element.ALIGN_CENTER);
            Paragraph lineBreak = new Paragraph("\n");

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(title);
            document.addHeader("titulo", "Recibo de Protocolo");
            document.add(dataEntrega);
            document.add(mainBlock);
            document.add(dataAtual);
            document.add(lineBreak);
            document.add(lineBreak);
            document.add(lineBreak);
            document.add(lineBreak);
            document.add(lineBreak);
            document.add(footer);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}