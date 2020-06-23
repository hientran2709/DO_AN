package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewsFeedRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewsFeedResponse;
import com.hientran.do_an.quanlygiangduong.client.dto.RegisterUseRoomRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.RegisterUseRoomResponse;
import com.hientran.do_an.quanlygiangduong.service.RegisterUseRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterUseRoomResource {
    private Logger log = LoggerFactory.getLogger(RegisterUseRoomResource.class);
    @Autowired
    private RegisterUseRoomService registerUseRoomService;

    @PostMapping("/book-room")
    public ResponseEntity<BaseDataResponse<RegisterUseRoomResponse>> registerUseRoom(
            @RequestBody BaseDataRequest<RegisterUseRoomRequest> request) throws ServiceException, Exception {
        try {
            RegisterUseRoomResponse response = registerUseRoomService.registerUseRoom(request.getBody());
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
