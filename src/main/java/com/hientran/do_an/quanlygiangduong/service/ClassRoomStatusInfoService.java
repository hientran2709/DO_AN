package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.client.rest.dto.ValidationErrorResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceExceptionBuilder;
import com.fis.egp.common.util.ServiceUtil;
import com.fis.egp.common.util.StringUtils;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.config.Shift;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            ClassroomStatusInfo newClassroomStatus = classRoomStatusInfoMapper.toEntity(request.getClassRoomStatusInfoDTO());
            String classroomNo = request.getClassRoomStatusInfoDTO().getClassRoomDTO().getClassroomNo();
            String building = request.getClassRoomStatusInfoDTO().getClassRoomDTO().getBuilding();
            if (StringUtils.isEmpty(classroomNo))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("classroomNo","cant not null"))
                        .build();
            if (StringUtils.isEmpty(building))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("building","cant not null"))
                        .build();

            Optional<ClassRoom> classRoom = classRoomRepository.findByClassroomNoAndBuilding(classroomNo,building);
            //define
            String className = request.getClassRoomStatusInfoDTO().getCLassListDTO().getClassName();
            String course = request.getClassRoomStatusInfoDTO().getCLassListDTO().getCourse();
            Optional<ClassList> classList = classListRepository.findByClassNameAndCourse(className,course);

//            String currentUser = SecurityUtils.getCurrentUserLogin().get();
            String currentUser = "hientv9@gmail.com";
            Optional<User> user = userRepository.findByEmail(currentUser);
            newClassroomStatus.setClassList(classList.get());
            newClassroomStatus.setClassRoom(classRoom.get());
            newClassroomStatus.setUser(user.get());
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

    public GetRoomEmptyResponse getRoomEmpty(GetRoomEmptyRequest request) throws ServiceException,Exception {
        try {
            GetRoomEmptyResponse response = new GetRoomEmptyResponse();
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            if (StringUtils.isEmpty(request.getBuilding()))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("Building","cant not null"))
                        .build();
            if (request.getUsedDate() == null)
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("UsedDate","cant not null"))
                        .build();
            List<ClassRoom> classRooms = classRoomRepository.findByBuilding(request.getBuilding());
            if (classRooms.isEmpty())
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("ClassRoom","Not Found"))
                        .build();
            Map<String,Integer> getRoom = new HashMap<>();
            List<ClassroomStatusInfo> existed = classRoomStatusInfoRepository.findByClassRoom_BuildingAndUsedDate(request.getBuilding(),request.getUsedDate());
            if (existed.isEmpty()){
                getRoom.put(Shift.CA_1.getValue(),classRooms.size());
                getRoom.put(Shift.CA_2.getValue(),classRooms.size());
                getRoom.put(Shift.CA_3.getValue(),classRooms.size());
                getRoom.put(Shift.CA_4.getValue(),classRooms.size());
                getRoom.put(Shift.CA_5.getValue(),classRooms.size());
                response.setRoomEmpty(getRoom);
                return response;
            }
            List<ClassroomStatusInfo> ca1 = existed.stream().filter(i -> i.getShift().equals(Shift.CA_1.getValue())).collect(Collectors.toList());
            List<ClassroomStatusInfo> ca2 = existed.stream().filter(i -> i.getShift().equals(Shift.CA_2.getValue())).collect(Collectors.toList());
            List<ClassroomStatusInfo> ca3 = existed.stream().filter(i -> i.getShift().equals(Shift.CA_3.getValue())).collect(Collectors.toList());
            List<ClassroomStatusInfo> ca4 = existed.stream().filter(i -> i.getShift().equals(Shift.CA_4.getValue())).collect(Collectors.toList());
            List<ClassroomStatusInfo> ca5 = existed.stream().filter(i -> i.getShift().equals(Shift.CA_5.getValue())).collect(Collectors.toList());

            getRoom.put(Shift.CA_1.getValue(),classRooms.size()-ca1.size());
            getRoom.put(Shift.CA_2.getValue(),classRooms.size()-ca2.size());
            getRoom.put(Shift.CA_3.getValue(),classRooms.size()-ca3.size());
            getRoom.put(Shift.CA_4.getValue(),classRooms.size()-ca4.size());
            getRoom.put(Shift.CA_5.getValue(),classRooms.size()-ca5.size());
            response.setRoomEmpty(getRoom);
            return response;
        } catch (ServiceException e) {
            throw e;
        }catch (Exception e){
            throw e;
        }
    }

    public GetRoomEmptyDetailResponse getRoomDetail(GetRoomEmptyDetailRequest request) throws ServiceException,Exception {
        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            if (StringUtils.isEmpty(request.getBuilding()))
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("Building","cant not null"))
                        .build();
            if (request.getUsedDate() == null)
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("UsedDate","cant not null"))
                        .build();
            List<ClassRoom> classRooms = classRoomRepository.findByBuilding(request.getBuilding());
            if (classRooms.isEmpty())
                throw ServiceExceptionBuilder.newBuilder()
                        .addError(new ValidationErrorResponse("ClassRoom","Not Found"))
                        .build();
            Map<String,List<ClassroomStatusInfo>> roomDetail = new HashMap<>();
            List<ClassroomStatusInfo> existed = classRoomStatusInfoRepository.findByClassRoom_BuildingAndUsedDateAndShift(request.getBuilding(),request.getUsedDate(),request.getShift());

            roomDetail.put(request.getShift(),existed);
            GetRoomEmptyDetailResponse response = new GetRoomEmptyDetailResponse();
            response.setRoomDetails(roomDetail);
            return response;
        } catch (ServiceException e) {
            throw e;
        }catch (Exception e){
            throw e;
        }
    }
}
