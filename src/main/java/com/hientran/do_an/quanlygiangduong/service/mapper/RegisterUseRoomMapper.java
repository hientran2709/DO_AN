package com.hientran.do_an.quanlygiangduong.service.mapper;


import com.hientran.do_an.quanlygiangduong.domain.RegisterUseRoom;
import com.hientran.do_an.quanlygiangduong.service.dto.RegisterUseRoomDTO;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseRoomMapper {
    public RegisterUseRoom toEntity(RegisterUseRoomDTO registerUseRoomDTO){
        RegisterUseRoom res = new RegisterUseRoom();
        res.setId(registerUseRoomDTO.getId());
        res.setNumOfStudents(registerUseRoomDTO.getNumOfStudents());
        res.setReaSon(registerUseRoomDTO.getReaSon());
        res.setSchoolShift(registerUseRoomDTO.getSchoolShift());
        res.setUsedDate(registerUseRoomDTO.getUsedDate());
        res.setStatus(registerUseRoomDTO.getStatus());
        return res;
    }
}
