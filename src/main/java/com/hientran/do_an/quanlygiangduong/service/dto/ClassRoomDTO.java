package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomDTO {

    private Integer id;

    private String classroomNo;

    private String building;

    private boolean conditionRoom;

    private Integer roomType;

    public ClassRoomDTO(ClassRoom classRoom) {
        this.id = classRoom.getId();
        this.classroomNo = classRoom.getClassroomNo();
        this.building = classRoom.getBuilding();
        this.conditionRoom = classRoom.isConditionRoom();
        this.roomType = classRoom.getRoomType();
    }
}
