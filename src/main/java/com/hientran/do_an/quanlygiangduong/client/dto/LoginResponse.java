package com.hientran.do_an.quanlygiangduong.client.dto;

import com.hientran.do_an.quanlygiangduong.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private UserDTO userDTO;
}
