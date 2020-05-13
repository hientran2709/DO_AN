package com.hientran.do_an.quanlygiangduong.client.dto;

import com.hientran.do_an.quanlygiangduong.service.dto.PhongDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInfoPhongRequest {
    private PhongDTO phongDTO;
}
