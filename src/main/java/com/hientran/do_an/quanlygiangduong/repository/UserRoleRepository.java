package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.UserRole;
import com.hientran.do_an.quanlygiangduong.domain.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
}
