package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePK implements Serializable {

    @NotNull
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "role_name")
    private String roleName;
}
