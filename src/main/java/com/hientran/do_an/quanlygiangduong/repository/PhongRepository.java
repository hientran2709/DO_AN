package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer> {

    Optional<Phong> findById(Integer id);

    @Query("select o from Phong o where " +
    "(:id is null or o.id)"+
    "and (:soPhong is null or o.soPhong like %:soPhong%)" +
    "and (:toaNha is null or o.toaNha like %:toaNha%)" +
    "and (:tinhTrang is null or o.tinhTrang )")
    List<Phong> searchPhong(@Param("id") Integer id,
                            @Param("soPhong") String soPhong,
                            @Param("toaNha") String toaNha,
                            @Param("tinhTrang") Boolean tinhTrang);
}
