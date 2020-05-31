package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.ClassRoomStatusInfoRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.ClassRoomStatusInfoResponse;
import com.hientran.do_an.quanlygiangduong.domain.ClassList;
import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import com.hientran.do_an.quanlygiangduong.domain.ClassroomStatusInfo;
import com.hientran.do_an.quanlygiangduong.domain.User;
import com.hientran.do_an.quanlygiangduong.repository.ClassListRepository;
import com.hientran.do_an.quanlygiangduong.repository.ClassRoomRepository;
import com.hientran.do_an.quanlygiangduong.repository.ClassRoomStatusInfoRepository;
import com.hientran.do_an.quanlygiangduong.repository.UserRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomStatusInfoDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.ClassRoomStatusInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassRoomStatusInfoService {
    @Autowired
    private ClassRoomStatusInfoRepository classRoomStatusInfoRepository;

    @Autowired
    private ClassRoomStatusInfoMapper classRoomStatusInfoMapper;

    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private ClassListRepository classListRepository;
    @Autowired
    private UserRepository userRepository;



    public ClassRoomStatusInfoResponse addClassRoomStatusInfo(ClassRoomStatusInfoRequest request) throws ServiceException {
        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            ClassroomStatusInfo newClassroomStatus = classRoomStatusInfoMapper.dtoToClassroomStatusInfo(request.getClassRoomStatusInfoDTO());
            Optional<ClassRoom> classRoom = classRoomRepository.findById(request.getClassRoomStatusInfoDTO().getClassroomId());
            Optional<ClassList> classList = classListRepository.findById(request.getClassRoomStatusInfoDTO().getClassId());
            newClassroomStatus.setClassList(classList.get());
            newClassroomStatus.setClassRoom(classRoom.get());
            Optional<ClassroomStatusInfo> updateRoomStatus = Optional.of(classRoomStatusInfoRepository.save(newClassroomStatus));
            if (!updateRoomStatus.isPresent())
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("RoomStatus","cant not add new"))
                        .build();
            ClassRoomStatusInfoResponse response = new ClassRoomStatusInfoResponse();
            response.setClassRoomStatusInfoDTO(updateRoomStatus.map(ClassRoomStatusInfoDTO::new).get());
            return response;
        }catch(ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }
}
