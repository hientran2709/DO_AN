package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer> {

    Optional<Phong> findById(Integer id);
}
