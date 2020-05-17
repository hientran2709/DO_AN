package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.Infrastructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfrastructureDTO {
    private Integer id;
    private Integer numOfTable;
    private String tableType;
    private Integer numOfChair;
    private Boolean projector;
    private Boolean sound;
    private Boolean refresher;

    public InfrastructureDTO(Infrastructure infrastructure) {
        this.id = infrastructure.getId();
        this.numOfTable = infrastructure.getNumOfTable();
        this.numOfChair = infrastructure.getNumOfChair();
        this.tableType = infrastructure.getTableType();
        this.projector = infrastructure.isProjector();
        this.sound = infrastructure.isSound();
        this.refresher = infrastructure.isRefresher();
    }
}
