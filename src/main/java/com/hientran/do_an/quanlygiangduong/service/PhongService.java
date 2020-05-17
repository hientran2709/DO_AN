package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.domain.Phong;
import com.hientran.do_an.quanlygiangduong.repository.PhongRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.PhongDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.PhongMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhongService {
    private static final Logger log = LoggerFactory.getLogger(PhongService.class);
    @Autowired
    private PhongRepository phongRepository;

    @Autowired
    private PhongMapper phongMapper;

    public AddNewPhongResponse addNewPhong(AddNewPhongRequest request) throws ServiceException {
        try {
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
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }

    public UpdateInfoPhongResponse updateInfoPhong(UpdateInfoPhongRequest request) throws ServiceException ,Exception{

        try {
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
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }
    public SearchPhongResponse searchPhong(SearchPhongRequest request) throws ServiceException {
        if(request == null)
            ServiceUtil.generateEmptyPayloadError();
        if(request.getPhongDTO() == null)
            throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("Request", ValidationError.NotNull,"request can't null"))
                    .build();
        PhongDTO phong = request.getPhongDTO();
        List<PhongDTO> existed = phongRepository.searchPhong(phong.getId(),phong.getSoPhong(),phong.getToaNha(),phong.isTinhTrang()).stream()
                .map(phongMapper::toDTO).collect(Collectors.toList());
        if (existed == null)
            throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("Phong", ValidationError.NotFound,"Phong is not found"))
                    .build();
        SearchPhongResponse response = new SearchPhongResponse();
        response.setPhongDTO(existed);
        return response;
    }


}
