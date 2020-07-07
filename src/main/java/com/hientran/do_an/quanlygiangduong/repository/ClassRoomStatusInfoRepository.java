package com.hientran.do_an.quanlygiangduong.repository;

import com.hientran.do_an.quanlygiangduong.domain.ClassroomStatusInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRoomStatusInfoRepository extends JpaRepository<ClassroomStatusInfo,Integer> {
    List<ClassroomStatusInfo> findByClassRoom_BuildingAndUsedDate(String building, Date usedDate);

    List<ClassroomStatusInfo> findByUser_UserId(String userId);

    List<ClassroomStatusInfo> findByClassRoom_BuildingAndUsedDateAndShift(String building, Date usedDate,String shift);
}
