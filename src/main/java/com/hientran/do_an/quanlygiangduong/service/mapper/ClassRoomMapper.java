package com.hientran.do_an.quanlygiangduong.service.mapper;

import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomDTO;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomMapper {
    public ClassRoom phongDTOToPhong(ClassRoomDTO classRoomDTO) {
        if (classRoomDTO == null) {
            return null;
        } else {
            ClassRoom classRoom = new ClassRoom();
            classRoom.setId(classRoomDTO.getId());
            classRoom.setClassroomNo(classRoomDTO.getClassroomNo());
            classRoom.setBuilding(classRoomDTO.getBuilding());
            classRoom.setConditionRoom(classRoomDTO.isConditionRoom());
            return classRoom;
        }
    }
    public ClassRoomDTO toDTO(ClassRoom classRoom){
        if (classRoom == null){
            return null;
        }else{
            ClassRoomDTO classRoomDTO = new ClassRoomDTO();
            classRoomDTO.setId(classRoom.getId());
            classRoomDTO.setClassroomNo(classRoom.getClassroomNo());
            classRoomDTO.setBuilding(classRoom.getBuilding());
            classRoomDTO.setConditionRoom(classRoom.isConditionRoom());
            return classRoomDTO;
        }
    }
}
