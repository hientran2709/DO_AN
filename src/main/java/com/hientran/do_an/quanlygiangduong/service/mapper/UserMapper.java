package com.hientran.do_an.quanlygiangduong.service.mapper;


import com.hientran.do_an.quanlygiangduong.domain.User;
import com.hientran.do_an.quanlygiangduong.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User toEntity(UserDTO userDTO){
        if (userDTO == null)
            return null;
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO,User.class);
        return user;
    }

    public List<User> toEntity(List<UserDTO> userDTOS){
        List<User> newUsers = userDTOS.stream().map(c -> toEntity(c)).collect(Collectors.toList());
        return newUsers;
    }
}
