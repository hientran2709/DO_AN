package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.service.ClassRoomService;
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
public class ClassRoomResource {

    private Logger log = LoggerFactory.getLogger(ClassRoomResource.class);
    @Autowired
    private ClassRoomService service;

    @PostMapping("/addnew")
    public ResponseEntity<BaseDataResponse<AddNewClassRoomResponse>> addnew(
            @RequestBody BaseDataRequest<AddNewClassRoomRequest> request) throws ServiceException, Exception {
        try {
            AddNewClassRoomResponse response = service.addNewPhong(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }
//    @PostMapping("/update-phong")
//    public ResponseEntity<BaseDataResponse<UpdateInfoClassRoomResponse>> updateInfoPhong(
//            @RequestBody BaseDataRequest<UpdateInfoClassRoomRequest> request) throws ServiceException, Exception {
//        try {
//            UpdateInfoClassRoomResponse response = service.updateInfoPhong(request.getBody());
//            return ResponseUtil.wrap(response);
//        } catch (ServiceException e) {
//            log.error(this.getClass().getName(), e);
//            return ResponseUtil.generateErrorResponse(e);
//        } catch (Exception e) {
//            log.error(this.getClass().getName(), e);
//            return ResponseUtil.generateErrorResponse(e);
//        }
//    }

    @PostMapping("/search-phong")
    public ResponseEntity<BaseDataResponse<SearchClassRoomResponse>> searchClassroom(
            @RequestBody BaseDataRequest<SearchClassRoomRequest> request) throws ServiceException, Exception {
        try {
            SearchClassRoomResponse response = service.searchClassroom(request.getBody());
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
