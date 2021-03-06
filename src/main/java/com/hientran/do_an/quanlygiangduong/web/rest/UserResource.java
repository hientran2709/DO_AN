package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Autowired
    private UserService userService;
    @PostMapping("/add-new")
    public ResponseEntity<BaseDataResponse<AddUserResponse>> addUser(
            @RequestBody BaseDataRequest<AddUserRequest> request) throws ServiceException, Exception {
        try {
            AddUserResponse response = userService.addUser(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseDataResponse<LoginResponse>> login(
            @RequestBody BaseDataRequest<LoginRequest> request) throws ServiceException, Exception {
        try {
            LoginResponse response = userService.login(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<BaseDataResponse<ForgotPasswordResponse>> resetPassword(
            @RequestBody BaseDataRequest<ForgotPasswordRequest> request) throws ServiceException, Exception {
        try {
            ForgotPasswordResponse response = userService.resetPassword(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/change-password")
    public ResponseEntity<BaseDataResponse<ChangePasswordResponse>> changePassword(
            @RequestBody BaseDataRequest<ChangePasswordRequest> request) throws ServiceException, Exception {
        try {
            ChangePasswordResponse response = userService.changePassword(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            return ResponseUtil.generateErrorResponse(e);
        }
    }
}
