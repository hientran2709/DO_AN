package com.hientran.do_an.quanlygiangduong.web.rest;

import com.fis.egp.common.client.rest.dto.BaseDataRequest;
import com.fis.egp.common.client.rest.dto.BaseDataResponse;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ResponseUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.*;
import com.hientran.do_an.quanlygiangduong.service.NewsFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news-feed")
public class NewsFeedResource {

    private Logger log = LoggerFactory.getLogger(NewsFeedResource.class);

    @Autowired
    private NewsFeedService newsFeedService;

    @PostMapping("/add-new")
    public ResponseEntity<BaseDataResponse<AddNewsFeedResponse>> addNewsFeed(
            @RequestBody BaseDataRequest<AddNewsFeedRequest> request) throws ServiceException, Exception {
        try {
            AddNewsFeedResponse response = newsFeedService.addNewsFeed(request.getBody());
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PostMapping("/get-all")
    public ResponseEntity<BaseDataResponse<GetNewsFeedResponse>> getNewsFeed(
            @RequestBody BaseDataRequest<GetNewsFeedRequest> request) throws ServiceException, Exception {
        try {
            GetNewsFeedResponse response = newsFeedService.getNewsFeed(request.getBody());
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
