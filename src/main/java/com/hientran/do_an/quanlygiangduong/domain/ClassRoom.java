package com.hientran.do_an.quanlygiangduong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class_room")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classroom_no")
    private String classroomNo;

    @Column(name = "building")
    private String building;

    @Column(name = "condition_room")
    private boolean conditionRoom;

    @Column(name = "room_type")
    private String roomType;

//    @JsonIgnore
//    @OneToOne(mappedBy = "classroom", cascade = CascadeType.ALL)
//    private Infrastructure infrastructure;

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", classroomNo='" + classroomNo + '\'' +
                ", building='" + building + '\'' +
                ", conditionRoom=" + conditionRoom +
                ", roomType=" + roomType +
                '}';
    }
}
