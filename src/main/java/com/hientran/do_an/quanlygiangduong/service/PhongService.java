package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewPhongRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewPhongResponse;
import com.hientran.do_an.quanlygiangduong.client.dto.UpdateInfoPhongRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.UpdateInfoPhongResponse;
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
    private PhongRepository phongRepository;

    @Autowired
    private PhongMapper phongMapper;

    public AddNewPhongResponse addNewPhong(AddNewPhongRequest request) throws ServiceException {
        if (request == null){
            ServiceUtil.generateEmptyPayloadError();
        }
        if (request.getPhongDTO() == null)
            throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("AddPhong", ValidationError.NotNull,"request is not null"))
                    .build();

        Phong newPhong = phongMapper.phongDTOToPhong(request.getPhongDTO());
        newPhong.setId(null);
        Optional<Phong> updatedPhong = Optional.of(
                Optional.of(phongRepository.save(newPhong)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(phong -> {
                    log.debug("Updated Information for User: {}", phong);
                    return phong;
                });
        if (!updatedPhong.isPresent()){
            throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("AddPhong", ValidationError.AssertFalse,"cant add new phong"))
                    .build();
        }

        AddNewPhongResponse response = new AddNewPhongResponse();
        response.setTitle("AddPhong");
        response.setErrorCode("validate.constrains.SaveSuccess");
        return response;
    }

    public UpdateInfoPhongResponse updateInfoPhong(UpdateInfoPhongRequest request) throws ServiceException {

        if (request == null){
            ServiceUtil.generateEmptyPayloadError();
        }
        Phong newPhong = phongMapper.phongDTOToPhong(request.getPhongDTO());
        Optional<Phong> updatedPhong = Optional.of(
                Optional.of(phongRepository.save(newPhong)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(phong -> {
                    log.debug("Updated Information for User: {}", phong);
                    return phong;
                });
        if (!updatedPhong.isPresent()){
            throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("UpdatePhong", ValidationError.AssertFalse,"cant update phong"))
                    .build();
        }
        UpdateInfoPhongResponse response = new UpdateInfoPhongResponse();
        response.setTitle("UpdatePhong");
        response.setErrorCode("validate.constrains.SaveSuccess");
        return response;
    }


}
