package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {

    Optional<ClassRoom> findById(Integer id);
    Optional<ClassRoom> findByClassroomNoAndBuilding(String classroomNo, String building);

    @Query("select o from ClassRoom o where " +
    "(:classroomNo is null or o.classroomNo=:classroomNo)" +
    "and (:building is null or o.building=:building )" +
    "and (:conditionRoom is null or o.conditionRoom=:conditionRoom )")
    List<ClassRoom> searchClassroom(@Param("classroomNo") String classroomNo,
                                    @Param("building") String building,
                                    @Param("conditionRoom") Boolean conditionRoom);
//    List<Phong> findBySoPhong(String soPhong);
}
