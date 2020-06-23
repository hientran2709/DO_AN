package com.hientran.do_an.quanlygiangduong.config;

import lombok.Getter;

@Getter
public enum RegisterStatus {
    WAIT_APPROVE("WAIT_APPROVE"),
    CANCEL("CANCEL"),
    APPROVED("APPROVED");

    private String value;

    RegisterStatus(String value) {
        this.value = value;
    }
}
