package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.AddUserRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.AddUserResponse;
import com.hientran.do_an.quanlygiangduong.domain.User;
import com.hientran.do_an.quanlygiangduong.repository.UserRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.UserDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public AddUserResponse addUser(AddUserRequest request) throws ServiceException {
        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            List<User> newUsers = userMapper.toEntity(request.getUserDTO());
            List<UserDTO> userLatest = userRepository.saveAll(newUsers).stream().map(UserDTO::new).collect(Collectors.toList());
            AddUserResponse response = new AddUserResponse();
            response.setUserDTO(userLatest);
            return  response;
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }

    }
}
