package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.RegisterUseRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUseRoomDTO {

    private Integer id;
    private Date usedDate;
    private String schoolShift;
    private Integer numOfStudents;
    private String reaSon;
    private String status;
    private ClassRoomDTO classRoomDTO = new ClassRoomDTO();
    private CLassListDTO cLassListDTO = new CLassListDTO();

    public RegisterUseRoomDTO(RegisterUseRoom registerUseRoom) {
        this.id = registerUseRoom.getId();
        this.usedDate = registerUseRoom.getUsedDate();
        this.schoolShift = registerUseRoom.getSchoolShift();
        this.numOfStudents = registerUseRoom.getNumOfStudents();
        this.reaSon = registerUseRoom.getReaSon();
        this.status = registerUseRoom.getStatus();
    }
}
