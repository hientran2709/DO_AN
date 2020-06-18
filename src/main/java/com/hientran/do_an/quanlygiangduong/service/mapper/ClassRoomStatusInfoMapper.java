package com.hientran.do_an.quanlygiangduong.service.mapper;

import com.hientran.do_an.quanlygiangduong.domain.ClassroomStatusInfo;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomStatusInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomStatusInfoMapper {
    public ClassroomStatusInfo toEntity(ClassRoomStatusInfoDTO classRoomStatusInfoDTO){
        if (classRoomStatusInfoDTO == null)
            return null;
        ModelMapper modelMapper = new ModelMapper();
        ClassroomStatusInfo classRoomStatusInfo = modelMapper.map(classRoomStatusInfoDTO,ClassroomStatusInfo.class);
        return classRoomStatusInfo;
    }
}
