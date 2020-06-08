package com.hientran.do_an.quanlygiangduong.client.dto;

import com.fis.egp.common.domain.OptimizedPage;
import com.hientran.do_an.quanlygiangduong.service.dto.NewsFeedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNewsFeedResponse {
    private OptimizedPage<NewsFeedDTO> pageInfo;
}
