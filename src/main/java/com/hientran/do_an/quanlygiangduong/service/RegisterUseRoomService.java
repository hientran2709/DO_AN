package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.fis.egp.common.util.StringUtils;
import com.hientran.do_an.quanlygiangduong.client.dto.RegisterUseRoomRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.RegisterUseRoomResponse;
import com.hientran.do_an.quanlygiangduong.config.RegisterStatus;
import com.hientran.do_an.quanlygiangduong.domain.ClassList;
import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import com.hientran.do_an.quanlygiangduong.domain.RegisterUseRoom;
import com.hientran.do_an.quanlygiangduong.repository.ClassListRepository;
import com.hientran.do_an.quanlygiangduong.repository.ClassRoomRepository;
import com.hientran.do_an.quanlygiangduong.repository.RegisterUseRoomRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.CLassListDTO;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomDTO;
import com.hientran.do_an.quanlygiangduong.service.dto.RegisterUseRoomDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.RegisterUseRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUseRoomService {
    @Autowired
    private RegisterUseRoomRepository registerUseRoomRepository;
    @Autowired
    private RegisterUseRoomMapper registerUseRoomMapper;
    @Autowired
    private ClassListRepository classListRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;

    public RegisterUseRoomResponse registerUseRoom(RegisterUseRoomRequest request) throws ServiceException {
        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            RegisterUseRoom newRegister = registerUseRoomMapper.toEntity(request.getRegisterUseRoomDTO());
            String className = request.getRegisterUseRoomDTO().getCLassListDTO().getClassName();
            String course = request.getRegisterUseRoomDTO().getCLassListDTO().getCourse();
            if (StringUtils.isEmpty(className))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("ClassName","cant not null"))
                        .build();
            if (StringUtils.isEmpty(course))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("Course","cant not null"))
                        .build();
            Optional<ClassList> classList = classListRepository.findByClassNameAndCourse(className,course);

            String classroomNo = request.getRegisterUseRoomDTO().getClassRoomDTO().getClassroomNo();
            String building = request.getRegisterUseRoomDTO().getClassRoomDTO().getBuilding();
            if (StringUtils.isEmpty(classroomNo))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("classroomNo","cant not null"))
                        .build();
            if (StringUtils.isEmpty(building))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("building","cant not null"))
                        .build();
            Optional<ClassRoom> classRoom = classRoomRepository.findByClassroomNoAndBuilding(classroomNo,building);

            newRegister.setClassRoom(classRoom.get());
            newRegister.setClassList(classList.get());
            newRegister.setStatus(RegisterStatus.WAIT_APPROVE.getValue());

            Optional<RegisterUseRoomDTO> registerLatest = Optional.of(registerUseRoomRepository.save(newRegister)).map(RegisterUseRoomDTO::new);
            RegisterUseRoomResponse response = new RegisterUseRoomResponse();
            response.setRegisterUseRoomDTO(registerLatest.get());
            response.getRegisterUseRoomDTO().setClassRoomDTO(classRoom.map(ClassRoomDTO::new).get());
            response.getRegisterUseRoomDTO().setCLassListDTO(classList.map(CLassListDTO::new).get());
            return response;
        } catch (ServiceException e) {
            throw e;
        }catch (Exception e){
            throw e;
        }
    }





}
