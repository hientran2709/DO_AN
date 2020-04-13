package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.fis.egp.um.client.rest.dto.*;
import com.fis.egp.um.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/unsecurety/api")
public class RegisterResource {
    private final Logger log = LoggerFactory.getLogger(RegisterResource.class);
    @Autowired
    private RegisterService registerService;

    /**
     * API REGISTER
     */
    @PostMapping("/register")
    public ResponseEntity<BaseDataResponse<RegisterResponse>> register(
        @RequestBody BaseDataResponse<RegisterRequest> request) throws Exception, ServiceException {

        try {
            RegisterResponse response = registerService.register(request.getBody());

            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/getorginfo")
    public ResponseEntity<BaseDataResponse<BusinessQueryResponse>> getInfo(
        @RequestBody BaseDataResponse<BusinessQueryRequest> orgCode) throws ServiceException, Exception {
        try {
            BusinessQueryResponse response = registerService.getBusinessInfo(orgCode.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/registerdoc")
    public ResponseEntity<BaseDataResponse<RegisterDocResponse>> updateFile(
        @RequestBody BaseDataResponse<RegisterDocRequest> request) throws ServiceException, Exception {
        try {
            RegisterDocResponse response = registerService.updateFile(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }







    @PostMapping("/registers-regiscode")
    public ResponseEntity<BaseDataResponse<GetRegisterByRegisCodeResponse>> getByRegisCode(
        @RequestBody BaseDataResponse<GetRegisterByRegisCodeRequest> request) throws ServiceException, Exception {
        try {
            GetRegisterByRegisCodeResponse response = registerService.getRegisterByRegisCode(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/getorgmanager")
    public ResponseEntity<BaseDataResponse<GetOrgManagerResponse>> orgManager(
        @RequestBody BaseDataResponse<GetOrgManagerRequest> request) throws ServiceException, Exception {
        try {
            GetOrgManagerResponse response = registerService.orgManager(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/get-register-list")
    public ResponseEntity<BaseDataResponse<RegisterListResponse>> getRegisterList(
        @RequestBody BaseDataRequest<RegisterListRequest> request) {

        try {
            RegisterListResponse response = registerService.getRegisterList(request.getBody());

            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }


}
