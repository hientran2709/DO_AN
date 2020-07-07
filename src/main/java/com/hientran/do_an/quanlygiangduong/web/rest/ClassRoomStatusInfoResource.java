package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.service.ClassRoomStatusInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room-status")
public class ClassRoomStatusInfoResource {

    private Logger log = LoggerFactory.getLogger(ClassRoomStatusInfoResource.class);

    @Autowired
    private ClassRoomStatusInfoService classRoomStatusInfoService;

    @PostMapping("/add-new")
    public ResponseEntity<BaseDataResponse<ClassRoomStatusInfoResponse>> addClassRoomStatusInfo(
            @RequestBody BaseDataRequest<ClassRoomStatusInfoRequest> request) throws ServiceException, Exception {
        try {
            ClassRoomStatusInfoResponse response = classRoomStatusInfoService.addClassRoomStatusInfo(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/room-empty")
    public ResponseEntity<BaseDataResponse<GetRoomEmptyResponse>> getRoomEmpty(
            @RequestBody BaseDataRequest<GetRoomEmptyRequest> request) throws ServiceException, Exception {
        try {
            GetRoomEmptyResponse response = classRoomStatusInfoService.getRoomEmpty(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/room-empty-detail")
    public ResponseEntity<BaseDataResponse<GetRoomEmptyDetailResponse>> getRoomDetail(
            @RequestBody BaseDataRequest<GetRoomEmptyDetailRequest> request) throws ServiceException, Exception {
        try {
            GetRoomEmptyDetailResponse response = classRoomStatusInfoService.getRoomDetail(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }
    @PostMapping("/teaching-schedule")
    public ResponseEntity<BaseDataResponse<TeachingScheduleResponse>> teachingSchedule(
            @RequestBody BaseDataRequest<TeachingScheduleRequest> request) throws ServiceException, Exception {
        try {
            TeachingScheduleResponse response = classRoomStatusInfoService.teachingSchedule(request.getBody());
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
