package com.hientran.do_an.quanlygiangduong.service.mapper;

import com.hientran.do_an.quanlygiangduong.domain.Infrastructure;
import com.hientran.do_an.quanlygiangduong.service.dto.InfrastructureDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureMapper {
    public Infrastructure dtoToInfrastructure(InfrastructureDTO infrastructureDTO){
        if (infrastructureDTO == null)
            return null;
        ModelMapper modelMapper = new ModelMapper();
        Infrastructure infrastructure = modelMapper.map(infrastructureDTO,Infrastructure.class);
        return infrastructure;
    }

}
