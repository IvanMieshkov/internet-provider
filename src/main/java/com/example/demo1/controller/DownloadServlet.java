package com.example.demo1.controller;

import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.services.GeneratePdf;
import com.example.demo1.model.services.impl.TariffsServiceImpl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class DownloadServlet extends HttpServlet {

    private static final int BYTES_DOWNLOAD = 1024;


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        String language = (String) request.getSession().getAttribute(LANGUAGE);
        String path = request.getRequestURI();
        String service = request.getParameter(TARIFF_SERVICE);
        String sortBy = request.getParameter(SORT_BY);
        String order = request.getParameter(ORDER);

        if(sortBy == null) {
            sortBy = "tariff_name_en";
        }
        if(order == null) {
            order = "ASC";
        }

        response.setContentType("application/pdf;charset=UTF-8");
        response.addHeader("Content-Disposition", "inline; filename=" + "tariffs.pdf");
        ServletOutputStream out = response.getOutputStream();

        List<TariffDto> tariffs = new TariffsServiceImpl().getByService(service, language, sortBy, order);

        ByteArrayOutputStream baos = GeneratePdf.getPdfFile(tariffs, language);
        baos.writeTo(out);
    }
}
