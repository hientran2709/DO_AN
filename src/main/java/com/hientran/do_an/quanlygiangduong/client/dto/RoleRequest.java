package com.hientran.do_an.quanlygiangduong.client.dto;

import com.hientran.do_an.quanlygiangduong.domain.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    List<Roles> roles;
}
