package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.config.ValidationError;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.config.MyConstants;
import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import com.hientran.do_an.quanlygiangduong.domain.Infrastructure;
import com.hientran.do_an.quanlygiangduong.repository.ClassRoomRepository;
import com.hientran.do_an.quanlygiangduong.repository.InfrastructureRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomDTO;
import com.hientran.do_an.quanlygiangduong.service.dto.InfrastructureDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.ClassRoomMapper;
import com.hientran.do_an.quanlygiangduong.service.mapper.InfrastructureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClassRoomService {
    private static final Logger log = LoggerFactory.getLogger(ClassRoomService.class);
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private ClassRoomMapper classRoomMapper;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private InfrastructureMapper infrastructureMapper;
    @Autowired
    private InfrastructureRepository infrastructureRepository;

    @Transactional
    public AddNewClassRoomResponse addNewPhong(AddNewClassRoomRequest request) throws ServiceException {
        try {
            if (request == null){
                ServiceUtil.generateEmptyPayloadError();
            }
            Optional<ClassRoom> existed = classRoomRepository.findByClassroomNoAndBuilding(request.getClassRoomDTO().getClassroomNo(),request.getClassRoomDTO().getBuilding());
            if (existed.isPresent())
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("AddPhong", ValidationError.Duplicate,"Phong da ton tai"))
                        .build();

            ClassRoom newClassRoom = classRoomMapper.phongDTOToPhong(request.getClassRoomDTO());
//                if (request.getClassRoomDTO().getId() == null)
            Optional<ClassRoom> updatedPhong = Optional.of(
                    Optional.of(classRoomRepository.saveAndFlush(newClassRoom)))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(classRoom -> {
                        log.debug("Updated Information for User: {}", classRoom);
                        return classRoom;
                    });
            if (!updatedPhong.isPresent())
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("ClassRoom","cant not add new"))
                        .build();
            Infrastructure newInfrastructure = infrastructureMapper .dtoToInfrastructure(request.getClassRoomDTO().getInfrastructureDTO());
            newInfrastructure.setClassRoom(updatedPhong.get());
            InfrastructureDTO infrastructureDTOLatest = Optional.of(infrastructureRepository.save(newInfrastructure)).map(InfrastructureDTO::new).get();
            infrastructureDTOLatest.setClassroomId(newClassRoom.getId());
            //---send mail---
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(MyConstants.FRIEND_EMAIL);
            message.setSubject("Register_Use_Room");
            message.setText("bạn đã đăng kí phòng thành công");
            this.emailSender.send(message);
            //---end---
            AddNewClassRoomResponse response = new AddNewClassRoomResponse();
            response.setClassRoomDTO(updatedPhong.map(ClassRoomDTO::new).get());
            response.getClassRoomDTO().setInfrastructureDTO(infrastructureDTOLatest);
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
        List<ClassRoomDTO> existed = classRoomRepository.searchClassroom(request.getClassroomNo(),request.getBuilding(),request.getConditionRoom(),request.getRoomType()).stream()
                .map(classRoomMapper::toDTO).collect(Collectors.toList());
        existed.stream().forEach(c ->{
            Optional<InfrastructureDTO> infrastructure = infrastructureRepository.findByClassRoom_Id(c.getId()).map(InfrastructureDTO::new);
            c.setInfrastructureDTO(infrastructure.get());
        });
        SearchClassRoomResponse response = new SearchClassRoomResponse();
        response.setClassRoomDTO(existed);
        return response;
    }

    public GetAllBuildingResponse getAllBuilding(GetAllBuildingRequest request) throws Exception, ServiceException {
        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            Set<String> buildings = classRoomRepository.getBuilding();
            GetAllBuildingResponse response = new GetAllBuildingResponse();
            response.setBuildings(buildings);
            return response;
        }catch (Exception e){
            throw e;
        }catch (ServiceException e){
            throw e;
        }
    }


}
