package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.RegisterUseRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUseRoomDTO {

    private Integer id;

    private Date usedDate;

    private int schoolShift;

    private Integer numOfStudents;

    private String reaSon;

    private Integer status;

    public RegisterUseRoomDTO(RegisterUseRoom registerUseRoom) {
        this.id = registerUseRoom.getId();
        this.usedDate = registerUseRoom.getUsedDate();
        this.schoolShift = registerUseRoom.getSchoolShift();
        this.numOfStudents = registerUseRoom.getNumOfStudents();
        this.reaSon = registerUseRoom.getReaSon();
        this.status = registerUseRoom.getStatus();
    }
}
