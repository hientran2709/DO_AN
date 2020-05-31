package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.ClassList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassListRepository extends JpaRepository<ClassList,Integer> {
    Optional<ClassList> findById(Integer id);
}
