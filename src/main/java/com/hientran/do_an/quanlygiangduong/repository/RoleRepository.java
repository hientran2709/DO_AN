package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
}
