package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.Phong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhongDTO {

    private Integer id;


    private String soPhong;


    private String toaNha;


    private boolean tinhTrang;

    public PhongDTO(Phong phong) {
        this.id = phong.getId();
        this.soPhong = phong.getSoPhong();
        this.toaNha = phong.getToaNha();
        this.tinhTrang = phong.isTinhTrang();
    }
}
