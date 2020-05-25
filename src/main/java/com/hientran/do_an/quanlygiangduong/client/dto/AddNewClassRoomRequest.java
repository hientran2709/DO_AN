package com.hientran.do_an.quanlygiangduong.client.dto;

import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewClassRoomRequest {
    private ClassRoomDTO classRoomDTO;
}
