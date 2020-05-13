package com.hientran.do_an.quanlygiangduong.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPhongRequest {
    private Integer numChair;
    private Integer numTable;
    private Boolean airConditional;
}
