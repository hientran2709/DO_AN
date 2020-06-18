package com.hientran.do_an.quanlygiangduong.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBuildingResponse {
    private Set<String> buildings;
}
