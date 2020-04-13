package com.hientran.do_an.quanlygiangduong.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {

    Optional<Register> findOneByRegisterCode(String registerCode);

    Page<Register> findByStatusIn(Pageable pageable, List<Integer> status);

    @EntityGraph(attributePaths = "userRegisters")
    Optional<List<Register>> findOneByOrgNameContaining(String orgName);

    @EntityGraph(attributePaths = "userRegisters")
    Optional<List<Register>> findByTaxCodeContaining(String taxCode);

}
