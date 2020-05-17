package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.service.PhongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phong")
public class PhongResource {

    private Logger log = LoggerFactory.getLogger(PhongResource.class);
    @Autowired
    private PhongService service;

    @PostMapping("/addnew")
    public ResponseEntity<BaseDataResponse<AddNewPhongResponse>> addnew(
            @RequestBody BaseDataRequest<AddNewPhongRequest> request) throws ServiceException, Exception {
        try {
            AddNewPhongResponse response = service.addNewPhong(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/update-phong")
    public ResponseEntity<BaseDataResponse<UpdateInfoPhongResponse>> updateInfoPhong(
            @RequestBody BaseDataRequest<UpdateInfoPhongRequest> request) throws ServiceException, Exception {
        try {
            UpdateInfoPhongResponse response = service.updateInfoPhong(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/search-phong")
    public ResponseEntity<BaseDataResponse<SearchPhongResponse>> searchPhong(
            @RequestBody BaseDataRequest<SearchPhongRequest> request) throws ServiceException, Exception {
        try {
            SearchPhongResponse response = service.searchPhong(request.getBody());
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
