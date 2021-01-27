package com.example.demo1.model.services;

import com.example.demo1.model.dto.TariffDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demo1.containers.StringContainer.CURRENCY;
import static com.example.demo1.containers.StringContainer.LOCALE_UKR;

/**
 * @author Ivan Mieshkov
 */
public class GeneratePdf {

    public static ByteArrayOutputStream getPdfFile(List<TariffDto> tariffs, String language) {

        String currency = language.equals(LOCALE_UKR) ? "грн" : "UAH";

        Document document = new Document();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(60);
            table.setWidths(new int[] {3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for(TariffDto tariff : tariffs) {
//                String name = language.equals(LOCALE_UKR)
//                                ? tariff.getTariff().getTariffNameUkr()
//                                : tariff.getTariff().getTariffNameEn();
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(tariff.getName()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(tariff.getTariff().getTariffPrice()) + " " + currency));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, bout);
            document.open();
            document.add(table);
            document.close();

        } catch (DocumentException e) {
            Logger.getLogger(GeneratePdf.class.getName()).log(Level.SEVERE, null, e);
        }
        return bout;
    }
}
