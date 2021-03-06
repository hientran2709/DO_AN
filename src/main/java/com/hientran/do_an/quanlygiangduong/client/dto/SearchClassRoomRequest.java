package com.hientran.do_an.quanlygiangduong.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchClassRoomRequest {
    private String classroomNo;
    private String building;
    private Boolean conditionRoom;
    private String roomType;
}
