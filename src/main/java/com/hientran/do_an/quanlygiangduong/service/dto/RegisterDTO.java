package com.hientran.do_an.quanlygiangduong.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.HashSet;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private Integer id;


    private String orgCode;

    private String orgName;


    private String inChargeName;


    private String registerCode;

    private int status;

    private Instant createdDate;

    private String preRegisType;

    private String regisType;

    private String repName;

    private String repIdType;

    private String reaSon;

    private String repIdNo;

    private Instant updatedDate;

    private String createdBy;

    private String updatedBy;

    private String chapterCode;

    @JsonProperty("files")
    private Set<RegisterFileDTO> registerFiles = new HashSet<>();

    public RegisterDTO(Register register) {

        this.id = register.getId();
        this.orgCode = register.getOrgCode();
        this.orgName = register.getOrgName();

        this.inChargeName = register.getInChargeName();

        this.registerCode = register.getRegisterCode();
        this.status = register.getStatus();
        this.createdDate = register.getCreatedDate();
        this.preRegisType = register.getPreRegisType();
        this.regisType = register.getRegisType();
        this.repName = register.getRepName();
        this.repIdType = register.getRepIdType();
        this.repIdNo = register.getRepIdNo();
        this.chapterCode = register.getChapterCode();

        this.updatedDate = register.getupdatedDate();
        this.reaSon = register.getReaSon();
        this.createdBy = register.getCreatedBy();
        this.updatedBy = register.getupdatedBy();

        this.registerFiles = register.getRegisterFiles().stream()
                .map(RegisterFileDTO::new)
                .collect(Collectors.toSet());

    }
}
