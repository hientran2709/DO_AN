package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.RoleRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.RoleResponse;
import com.hientran.do_an.quanlygiangduong.domain.Role;
import com.hientran.do_an.quanlygiangduong.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleResponse addRole(RoleRequest request) throws ServiceException,Exception {
        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            List<Role> addRoles = new ArrayList<>();
            request.getRoles().stream().forEach(c -> {
                Role role = roleRepository.save(c);
                addRoles.add(role);
            });
            RoleResponse response = new RoleResponse();
            response.setRoles(addRoles);
            return response;
        } catch (ServiceException e) {
            throw e;
        }catch (Exception e){
            throw e;
        }
    }
}
