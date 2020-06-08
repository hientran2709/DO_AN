package com.hientran.do_an.quanlygiangduong.service;

import com.fis.egp.common.domain.OptimizedPage;
import com.fis.egp.common.exception.ServiceException;
import com.fis.egp.common.util.ServiceUtil;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewsFeedRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewsFeedResponse;
import com.hientran.do_an.quanlygiangduong.client.dto.GetNewsFeedRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.GetNewsFeedResponse;
import com.hientran.do_an.quanlygiangduong.domain.NewsFeed;
import com.hientran.do_an.quanlygiangduong.repository.NewsFeedRepository;
import com.hientran.do_an.quanlygiangduong.service.dto.NewsFeedDTO;
import com.hientran.do_an.quanlygiangduong.service.mapper.NewsFeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsFeedService {
    @Autowired
    private NewsFeedRepository newsFeedRepository;

    @Autowired
    private NewsFeedMapper newsFeedMapper;

    public GetNewsFeedResponse getNewsFeed(GetNewsFeedRequest request) throws ServiceException {
        try {
            GetNewsFeedResponse response = new GetNewsFeedResponse();
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            Integer pageNumber = request.getPageNumber() < 0 ? 0 : request.getPageNumber();
            Integer pageSize = request.getPageSize() <= 0 ? 10 : request.getPageSize();
            Page<NewsFeedDTO> newsFeedDTOS = newsFeedRepository.getAllNewsFeed(PageRequest.of(pageNumber, pageSize)).map(NewsFeedDTO::new);
            response.setPageInfo(OptimizedPage.convert(newsFeedDTOS));
            return response;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
    public AddNewsFeedResponse addNewsFeed(AddNewsFeedRequest request) throws ServiceException {
        try {
            AddNewsFeedResponse response = new AddNewsFeedResponse();
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            List<NewsFeedDTO> updateNewsFeed = new ArrayList<>();
            List<NewsFeed> newNewsFeeds = request.getNewsFeedDTOS().stream().map(newsFeedMapper::dtoToNewsFeed).collect(Collectors.toList());
            newNewsFeeds.forEach(c ->{
                c.setCreatedDate(Instant.now());
                Optional<NewsFeedDTO> newsFeedDTO = Optional.of(newsFeedRepository.save(c))
                        .map(NewsFeedDTO::new);
                updateNewsFeed.add(newsFeedDTO.get());
            });
            response.setNewsFeedDTOS(updateNewsFeed);
            return  response;
        }catch (ServiceException e){
            throw e;
        }catch (Exception e){
            throw  e;
        }
    }
}