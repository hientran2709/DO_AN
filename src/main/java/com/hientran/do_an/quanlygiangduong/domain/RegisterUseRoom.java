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
    private String schoolShift;

    @Column(name = "num_of_students")
    private Integer numOfStudents;

    @Column(name = "reason")
    private String reaSon;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "class_list_id")
    private ClassList classList;

    @Override
    public String toString() {
        return "RegisterUseRoom{" +
                "id=" + id +
                ", usedDate=" + usedDate +
                ", schoolShift=" + schoolShift +
                ", numOfStudents=" + numOfStudents +
                ", reaSon='" + reaSon + '\'' +
                ", status=" + status +
                '}';
    }
}
