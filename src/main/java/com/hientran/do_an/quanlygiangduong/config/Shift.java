package com.hientran.do_an.quanlygiangduong.config;

import lombok.Getter;

@Getter
public enum Shift {
    CA_1("CA_1"),
    CA_2("CA_2"),
    CA_3("CA_3"),
    CA_4("CA_4"),
    CA_5("CA_5");

    private String value;

    Shift(String value) {
        this.value = value;
    }
}
