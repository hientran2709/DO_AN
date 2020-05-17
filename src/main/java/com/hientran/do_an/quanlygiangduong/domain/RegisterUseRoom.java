package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "register_use_room")
public class RegisterUseRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "used_date")
    private Date  usedDate;

    @Column(name = "school_shift")
    private int schoolShift;

    @Column(name = "num_of_students")
    private Integer numOfStudents;

    @Column(name = "reason")
    private String reaSon;

    @Override
    public String toString() {
        return "DKSDPhong{" +
                "id=" + id +
                ", usedDate=" + usedDate +
                ", schoolShift=" + schoolShift +
                ", numOfStudents=" + numOfStudents +
                ", reaSon='" + reaSon + '\'' +
                '}';
    }
}
