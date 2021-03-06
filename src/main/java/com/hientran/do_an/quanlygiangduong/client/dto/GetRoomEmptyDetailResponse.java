package com.hientran.do_an.quanlygiangduong.client.dto;

import com.hientran.do_an.quanlygiangduong.domain.ClassroomStatusInfo;
import com.hientran.do_an.quanlygiangduong.service.dto.ClassRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomEmptyDetailResponse {
    private Map<String, List<ClassRoomDTO>> roomDetails;
}
