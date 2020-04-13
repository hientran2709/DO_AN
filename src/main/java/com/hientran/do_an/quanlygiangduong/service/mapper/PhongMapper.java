package com.hientran.do_an.quanlygiangduong.service.mapper;

import com.hientran.do_an.quanlygiangduong.domain.Phong;
import com.hientran.do_an.quanlygiangduong.service.dto.PhongDTO;
import org.springframework.stereotype.Service;

@Service
public class PhongMapper {
    public Phong phongDTOToPhong(PhongDTO phongDTO) {
        if (phongDTO == null) {
            return null;
        } else {
            Phong phong = new Phong();
            phong.setId(phongDTO.getId());
            phong.setSophong(phongDTO.getSophong());
            phong.setToanha(phongDTO.getToanha());
            phong.setTinhtrang(phongDTO.isTinhtrang());
            return phong;
        }
    }
}
