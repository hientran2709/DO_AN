package com.hientran.do_an.quanlygiangduong.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomEmptyResponse {
    private Map<String,Integer> roomEmpty;
}
