package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.ClassroomStatusInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomStatusInfoDTO {

    private Integer id;
    private String day;
    private Date usedDate;
    private String shift;
    private Integer user_id;
    private ClassRoomDTO classRoomDTO = new ClassRoomDTO();
    private CLassListDTO cLassListDTO = new CLassListDTO();

    public ClassRoomStatusInfoDTO(ClassroomStatusInfo classroomStatusInfo) {
        this.id = classroomStatusInfo.getId();
        this.day = classroomStatusInfo.getDay();
        this.usedDate = classroomStatusInfo.getUsedDate();
        this.shift = classroomStatusInfo.getShift();
    }
}

