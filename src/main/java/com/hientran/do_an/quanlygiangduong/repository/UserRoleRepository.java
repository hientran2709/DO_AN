package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.UserRole;
import com.hientran.do_an.quanlygiangduong.domain.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
    List<UserRole> findByUser_UserId(String userId);
}
