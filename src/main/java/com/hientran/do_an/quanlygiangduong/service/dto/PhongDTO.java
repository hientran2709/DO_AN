package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.Phong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhongDTO {

    private Integer id;


    private String sophong;


    private String toanha;


    private boolean tinhtrang;

    public PhongDTO(Phong phong) {
        this.id = phong.getId();
        this.sophong = phong.getSophong();
        this.toanha = phong.getToanha();
        this.tinhtrang = phong.isTinhtrang();
    }
}
