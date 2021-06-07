package com.isilbolatbas.api.service;

import com.isilbolatbas.api.model.Info;
import com.isilbolatbas.api.model.InfoDetail;
import com.isilbolatbas.api.repository.InfoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InfoService implements IInfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public List<Info> findAllInfo() {
        return infoRepository.findAll();
    }

    @Override
    public Info getInfoById(Integer id) {
        return infoRepository.findById(id).get();
    }

    @Override
    public Info saveInfo(Info info) {
        return infoRepository.save(info);
    }


    @Override
    public void deleteInfo(Integer id) {
        infoRepository.deleteById(id);
    }


    @Override
    public void updateInfo(Info info) {

        infoRepository.save(info);
    }

    @Override
    public Collection<InfoDetail> findAllInfoDetail() {

       return infoRepository.findAllInfoDetails();
    }

    public String exportReport() throws JRException, FileNotFoundException {
        String path = "/home/isilbolatbas/Desktop";
        Collection<InfoDetail> employees = infoRepository.findAllInfoDetails();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:info.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();






        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/info.pdf");
        return "report generated in path : " + path;
    }


}
