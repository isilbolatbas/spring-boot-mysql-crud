package com.isilbolatbas.api.controller;


import com.isilbolatbas.api.model.Info;
import com.isilbolatbas.api.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Api(value = "Info API Document")
public class InfoController {

    @Autowired
    InfoService infoService;

    @GetMapping("api/v1/info")
    @ApiOperation(value = "list of all Info")
    public List<Info> findAllInfos() {
        return infoService.findAllInfo();
    }

    @GetMapping("api/v1/info/{id}")
    @ApiOperation(value = "find by id from info table")
    public Info findInfoGetById(@PathVariable Integer id) {
        return infoService.getInfoById(id);
    }

    @PostMapping("api/v1/info")
    @ApiOperation(value = "save for Info")
    public Info saveInfo(@RequestBody Info info) {
        return infoService.saveInfo(info);

    }

    @DeleteMapping("api/v1/info/{id}")
    @ApiOperation(value = "delete by id from info table")
    public void deleteInfo(@PathVariable Integer id) {
        infoService.deleteInfo(id);
    }

/*    @PutMapping("api/v1/updateInfo/{id}")
    @ApiOperation(value = "update by id from info table")
    public Info updateInfo(@RequestBody Info info, @PathVariable Integer id) {
        Info detail = infoService.getInfoById(id);
        detail.setId(info.getId());
        detail.setFirstname(info.getFirstname());
        detail.setLastname(info.getLastname());
        detail.setEmail(info.getEmail());
        detail.setPhone(info.getPhone());
        detail.setBirthday(info.getBirthday());

        Info updateInfo = infoService.saveInfo(detail);
        return updateInfo;
    }*/

    @PutMapping("/api/v1/info/{id}")
    public void updateInfoSecondMethod(@RequestBody Info info, @PathVariable(name="id")Integer id){
        Info infoDetail = infoService.getInfoById(id);
        if(infoDetail != null){
            infoService.updateInfo(info);
        }
    }
}
