package com.mieshkov.corplan.controller;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dto.TariffDto;
import com.mieshkov.corplan.model.services.GeneratePdf;
import com.mieshkov.corplan.model.services.impl.TariffsServiceImpl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class DownloadServlet extends HttpServlet {

    private static final int BYTES_DOWNLOAD = 1024;


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        String language = (String) request.getSession().getAttribute(StringContainer.LANGUAGE);
        String service = request.getParameter(StringContainer.TARIFF_SERVICE);
        String sortBy = request.getParameter(StringContainer.SORT_BY);
        String order = request.getParameter(StringContainer.ORDER);

        response.setContentType("application/pdf;charset=UTF-8");
        response.addHeader("Content-Disposition", "inline; filename=" + "tariffs.pdf");
        ServletOutputStream out = response.getOutputStream();

        List<TariffDto> tariffs = new TariffsServiceImpl().getByService(service, language, sortBy, order);

        ByteArrayOutputStream baos = GeneratePdf.getPdfFile(tariffs, language);
        baos.writeTo(out);
    }
}
