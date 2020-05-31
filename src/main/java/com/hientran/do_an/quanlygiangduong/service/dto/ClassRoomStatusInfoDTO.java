package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.ClassroomStatusInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomStatusInfoDTO {
    private Integer id;

    private Integer classroomId;

    private String day;

    private Date usedDate;

    private Integer classId;

    private Boolean shift1;

    private Boolean shift2;

    private Boolean shift3;

    private Boolean shift4;

    private Boolean shift5;

    public ClassRoomStatusInfoDTO(ClassroomStatusInfo classroomStatusInfo) {
        this.id = classroomStatusInfo.getId();
        this.day = classroomStatusInfo.getDay();
        this.usedDate = classroomStatusInfo.getUsedDate();
        this.shift1 = classroomStatusInfo.getShift1();
        this.shift2 = classroomStatusInfo.getShift2();
        this.shift3 = classroomStatusInfo.getShift3();
        this.shift4 = classroomStatusInfo.getShift4();
        this.shift5 = classroomStatusInfo.getShift5();
    }
}

