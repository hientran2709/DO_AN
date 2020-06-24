package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.AddUserRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.AddUserResponse;
import com.hientran.do_an.quanlygiangduong.domain.Role;
import com.hientran.do_an.quanlygiangduong.domain.User;
import com.hientran.do_an.quanlygiangduong.domain.UserRole;
import com.hientran.do_an.quanlygiangduong.domain.UserRolePK;
import com.hientran.do_an.quanlygiangduong.repository.UserRepository;
import com.hientran.do_an.quanlygiangduong.repository.UserRoleRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.UserDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRepository userRoleRepository;


    public AddUserResponse addUser(AddUserRequest request) throws ServiceException {
        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            List<User> newUser = request.getUserDTO().stream().map(userMapper::toEntity).collect(Collectors.toList());
            List<UserDTO> userLatest = userRepository.saveAll(newUser).stream().map(UserDTO::new).collect(Collectors.toList());
            List<UserRole> newUserRoles = new ArrayList<>();
            request.getUserDTO().stream().forEach(c -> {
                newUserRoles.addAll(convertUserRole(c));
            });
            userRoleRepository.saveAll(newUserRoles);

            AddUserResponse response = new AddUserResponse();
            response.setUserDTO(userLatest);
            return  response;
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw e;
        }

    }
    public List<UserRole> convertUserRole(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        List<UserRole> userRoles = userDTO.getRoles().stream().map(c -> {
            //save role to user_role
            UserRole userRole = new UserRole();
            Role role = new Role();
            role.setName(c);
            userRole.setId(new UserRolePK(user.getUserId(), c));
            userRole.setRole(role);
            userRole.setUser(user);
            return userRole;
        }).collect(Collectors.toList());
       return userRoles;
    }

}
