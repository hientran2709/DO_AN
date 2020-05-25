package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.config.MyConstants;
import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import com.hientran.do_an.quanlygiangduong.repository.ClassRoomRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.ClassRoomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    public JavaMailSender emailSender;

    @Transactional
    public AddNewClassRoomResponse addNewPhong(AddNewClassRoomRequest request) throws ServiceException {
        try {
            if (request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            if (request.getClassRoomDTO() == null)
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("PhongInfo", ValidationError.NotNull,"request is not null"))
                        .build();

            Optional<ClassRoom> existed = classRoomRepository.findByClassroomNoAndBuilding(request.getClassRoomDTO().getClassroomNo(),request.getClassRoomDTO().getBuilding());
            if (existed.isPresent())
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("AddPhong", ValidationError.Duplicate,"Phong da ton tai"))
                        .build();

            ClassRoom newClassRoom = classRoomMapper.phongDTOToPhong(request.getClassRoomDTO());
//                if (request.getClassRoomDTO().getId() == null)
            Optional<ClassRoomDTO> updatedPhong = Optional.of(
                    Optional.of(classRoomRepository.save(newClassRoom)))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(classRoom -> {
                        log.debug("Updated Information for User: {}", classRoom);
                        return classRoom;
                    }).map(ClassRoomDTO::new);
            if (!updatedPhong.isPresent())
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("ClassRoom","cant not add new"))
                        .build();
            //---send mail---
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(MyConstants.FRIEND_EMAIL);
            message.setSubject("Test Simple Email");
            message.setText("Hello, Im testing Simple Email");
            this.emailSender.send(message);
            //---end---
            AddNewClassRoomResponse response = new AddNewClassRoomResponse();
            response.setClassRoomDTO(updatedPhong.get());
            return response;
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }

//    public UpdateInfoClassRoomResponse updateInfoPhong(UpdateInfoClassRoomRequest request) throws ServiceException ,Exception{
//
//        try {
//            if (request == null){
//                ServiceUtil.generateEmptyPayloadError();
//            }
//            ClassRoom newClassRoom = classRoomMapper.phongDTOToPhong(request.getClassRoomDTO());
//            Optional<ClassRoom> updatedPhong = Optional.of(
//                    Optional.of(classRoomRepository.save(newClassRoom)))
//                    .filter(Optional::isPresent)
//                    .map(Optional::get)
//                    .map(classRoom -> {
//                        log.debug("Updated Information for User: {}", classRoom);
//                        return classRoom;
//                    });
//            if (!updatedPhong.isPresent()){
//                throw ServiceExceptionBuilder.newBuilder()
//                        .addError(new ValidationErrorResponse("UpdatePhong", ValidationError.AssertFalse,"cant update phongInfo"))
//                        .build();
//            }
//            UpdateInfoClassRoomResponse response = new UpdateInfoClassRoomResponse();
//            response.setTitle("UpdatePhong");
//            response.setErrorCode("validate.constrains.SaveSuccess");
//            return response;
//        }catch (ServiceException e){
//            throw e;
//        }catch (Exception e){
//            throw e;
//        }
//    }
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
