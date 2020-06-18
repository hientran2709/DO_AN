package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.Infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure,Integer> {
    Optional<Infrastructure> findByClassRoom_Id(Integer id);
}
