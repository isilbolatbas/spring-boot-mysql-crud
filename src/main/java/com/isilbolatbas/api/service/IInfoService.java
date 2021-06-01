package com.isilbolatbas.api.service;

import com.isilbolatbas.api.model.Info;
import java.util.List;

public interface IInfoService {

    public List<Info> findAllInfo();
    public Info getInfoById(Integer id);
    public Info saveInfo(Info info);
    public void deleteInfo(Integer id);
    public void updateInfo(Info info);
}
