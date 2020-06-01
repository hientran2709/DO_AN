package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.ClassRoomStatusInfoRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.ClassRoomStatusInfoResponse;
import com.hientran.do_an.quanlygiangduong.client.dto.RoleRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.RoleResponse;
import com.hientran.do_an.quanlygiangduong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleResource {
    @Autowired
    private RoleService roleService;

    @PostMapping("/addnew")
    public ResponseEntity<BaseDataResponse<RoleResponse>> addRole(
            @RequestBody BaseDataRequest<RoleRequest> request) throws ServiceException, Exception {
        try {
            RoleResponse response = roleService.addRole(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            return ResponseUtil.generateErrorResponse(e);
        }
    }
}
