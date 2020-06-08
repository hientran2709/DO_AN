package com.hientran.do_an.quanlygiangduong.service.mapper;

import com.hientran.do_an.quanlygiangduong.domain.NewsFeed;
import com.hientran.do_an.quanlygiangduong.service.dto.NewsFeedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class NewsFeedMapper {
    public NewsFeed dtoToNewsFeed(NewsFeedDTO newsFeedDTO) {
        if (newsFeedDTO == null)
            return null;
        ModelMapper modelMapper = new ModelMapper();
        NewsFeed newsFeed = modelMapper.map(newsFeedDTO, NewsFeed.class);
        return newsFeed;
    }
}
