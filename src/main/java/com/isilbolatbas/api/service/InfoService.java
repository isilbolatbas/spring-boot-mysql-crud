package com.isilbolatbas.api.service;

import com.isilbolatbas.api.model.Info;
import com.isilbolatbas.api.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

        infoRepository.save(info);}


}
