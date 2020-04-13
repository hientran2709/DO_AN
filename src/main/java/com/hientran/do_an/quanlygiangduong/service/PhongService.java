package com.hientran.do_an.quanlygiangduong.service;

import com.hientran.do_an.quanlygiangduong.client.dto.AddNewPhongRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewPhongResponse;
import com.hientran.do_an.quanlygiangduong.domain.Phong;
import com.hientran.do_an.quanlygiangduong.repository.PhongRepository;
import com.hientran.do_an.quanlygiangduong.service.mapper.PhongMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhongService {
    private static final Logger log = LoggerFactory.getLogger(PhongService.class);
    @Autowired
    private PhongRepository repos;

    @Autowired
    private PhongMapper phongMapper;

    public AddNewPhongResponse addNewPhong(AddNewPhongRequest request){
        if (request == null){
            AddNewPhongResponse response = new AddNewPhongResponse();
            response.setTitle("request");
            response.setErrorCode("validate.constrains.NotNull");
        }

        Phong newPhong = phongMapper.phongDTOToPhong(request.getPhongDTO());
        Optional<Phong> updatedPhong = Optional.of(
                Optional.of(repos.save(newPhong)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(phong -> {
                    log.debug("Updated Information for User: {}", phong);
                    return phong;
                });
        if (!updatedPhong.isPresent()){
            AddNewPhongResponse response = new AddNewPhongResponse();
            response.setTitle("AddPhong");
            response.setErrorCode("validate.constrains.SaveFail");
        }

        AddNewPhongResponse response = new AddNewPhongResponse();
        response.setTitle("AddPhong");
        response.setErrorCode("validate.constrains.SaveSuccess");
        return response;
    }
}
