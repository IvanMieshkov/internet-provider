package com.mieshkov.corplan.model.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mieshkov.corplan.model.entities.Tariff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mieshkov.corplan.containers.StringContainer.LOCALE_UKR;

/**
 * @author Ivan Mieshkov
 */
public class GeneratePdf {

    public static final String FONT = "fonts/ArialCR.TTF";

    public static ByteArrayOutputStream getPdfFile(List<Tariff> tariffs, String language) {

        String currency = language.equals(LOCALE_UKR) ? "грн" : "UAH";

        Document document = new Document();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(60);
            table.setWidths(new int[] {3, 3});

            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font headFont = new Font(bf,16,Font.NORMAL);

            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for(Tariff tariff : tariffs) {
                String name = language.equals(LOCALE_UKR)
                                ? tariff.getNameUkr()
                                : tariff.getNameEn();
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(name, headFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(tariff.getPrice()) + " " + currency));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, bout);
            document.open();
            document.add(table);
            document.close();

        } catch (DocumentException | IOException e) {
            Logger.getLogger(GeneratePdf.class.getName()).log(Level.SEVERE, null, e);
        }
        return bout;
    }
}
