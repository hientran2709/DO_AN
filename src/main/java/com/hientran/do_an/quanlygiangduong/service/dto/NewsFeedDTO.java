package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.NewsFeed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsFeedDTO {
    private Integer Id;
    private String title;
    private String content;
    private Instant createdDate;

    public NewsFeedDTO(NewsFeed newsFeed) {
        Id = newsFeed.getId();
        this.title = newsFeed.getTitle();
        this.content = newsFeed.getContent();
        this.createdDate = newsFeed.getCreatedDate();
    }
}
