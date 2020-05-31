package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role", schema = "hientv9-doan", catalog = "")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return userId == userRole.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
