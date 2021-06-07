package com.isilbolatbas.api.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.isilbolatbas.api.model.InfoDetail;
import com.isilbolatbas.api.model.SampleBean;
import com.isilbolatbas.api.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("api/document")
public class JasperDemoController {

    @Autowired
    private InfoRepository infoRepository;

    @GetMapping()
    public void getDocument(HttpServletResponse response) throws IOException, JRException {

        String sourceFileName = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "info.jrxml").getAbsolutePath();
        Collection<InfoDetail> dataList = infoRepository.findAllInfoDetails();
// creating datasource from bean list
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        Map parameters = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
    }
}
