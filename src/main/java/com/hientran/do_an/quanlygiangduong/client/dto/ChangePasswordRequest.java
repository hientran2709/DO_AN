package com.hientran.do_an.quanlygiangduong.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    private String UserName;
    private String password;
    private String newPassword;
}
