package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import com.hientran.do_an.quanlygiangduong.repository.ClassRoomRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.ClassRoomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassRoomService {
    private static final Logger log = LoggerFactory.getLogger(ClassRoomService.class);
    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private ClassRoomMapper classRoomMapper;

    public AddNewClassRoomResponse addNewPhong(AddNewClassRoomRequest request) throws ServiceException {
        try {
            if (request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if (request.getClassRoomDTO() == null)
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("PhongInfo", ValidationError.NotNull,"request is not null"))
                        .build();

            request.getClassRoomDTO().stream().forEach(c ->{
                Optional<ClassRoom> existed = classRoomRepository.findByClassroomNoAndBuilding(c.getClassroomNo(),c.getBuilding());
                if (existed.isPresent())
                    try {
                        throw ServiceExceptionBuilder.newBuilder()
                                .addError(new ValidationErrorResponse("AddPhong", ValidationError.Duplicate,"Phong da ton tai"))
                                .build();
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                ClassRoom newClassRoom = classRoomMapper.phongDTOToPhong(c);
                newClassRoom.setId(null);
                Optional<ClassRoom> updatedPhong = Optional.of(
                        Optional.of(classRoomRepository.save(newClassRoom)))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(classRoom -> {
                            log.debug("Updated Information for User: {}", classRoom);
                            return classRoom;
                        });
                if (updatedPhong.isPresent())
                    try {
                        throw ServiceExceptionBuilder.newBuilder()
                                .addError(new ValidationErrorResponse("AddPhong", ValidationError.AssertFalse,"cant not add new"))
                                .build();
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
            });

            AddNewClassRoomResponse response = new AddNewClassRoomResponse();
            response.setTitle("AddPhong");
            response.setErrorCode("validate.constrains.SaveSuccess");
            return response;
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }

    public UpdateInfoClassRoomResponse updateInfoPhong(UpdateInfoClassRoomRequest request) throws ServiceException ,Exception{

        try {
            if (request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            ClassRoom newClassRoom = classRoomMapper.phongDTOToPhong(request.getClassRoomDTO());
            Optional<ClassRoom> updatedPhong = Optional.of(
                    Optional.of(classRoomRepository.save(newClassRoom)))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(classRoom -> {
                        log.debug("Updated Information for User: {}", classRoom);
                        return classRoom;
                    });
            if (!updatedPhong.isPresent()){
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("UpdatePhong", ValidationError.AssertFalse,"cant update phongInfo"))
                        .build();
            }
            UpdateInfoClassRoomResponse response = new UpdateInfoClassRoomResponse();
            response.setTitle("UpdatePhong");
            response.setErrorCode("validate.constrains.SaveSuccess");
            return response;
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }
    public SearchClassRoomResponse searchClassroom(SearchClassRoomRequest request) throws ServiceException {
        if(request == null)
            ServiceUtil.generateEmptyPayloadError();
        if(request.getClassRoomDTO() == null)
            throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("Request", ValidationError.NotNull,"request can't null"))
                    .build();
        ClassRoomDTO phong = request.getClassRoomDTO();
        List<ClassRoomDTO> existed = classRoomRepository.searchClassroom(phong.getClassroomNo(),phong.getBuilding(),phong.isConditionRoom()).stream()
                .map(classRoomMapper::toDTO).collect(Collectors.toList());
        if (existed == null)
            throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("Phong", ValidationError.NotFound,"Phong is not found"))
                    .build();
        SearchClassRoomResponse response = new SearchClassRoomResponse();
        response.setClassRoomDTO(existed);
        return response;
    }


}
