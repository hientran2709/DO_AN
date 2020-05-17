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
            phong.setSoPhong(phongDTO.getSoPhong());
            phong.setToaNha(phongDTO.getToaNha());
            phong.setTinhTrang(phongDTO.isTinhTrang());
            return phong;
        }
    }
    public PhongDTO toDTO(Phong phong){
        if (phong == null){
            return null;
        }else{
            PhongDTO phongDTO = new PhongDTO();
            phongDTO.setId(phong.getId());
            phongDTO.setSoPhong(phong.getSoPhong());
            phongDTO.setToaNha(phong.getToaNha());
            phongDTO.setTinhTrang(phong.isTinhTrang());
            return phongDTO;
        }
    }
}
