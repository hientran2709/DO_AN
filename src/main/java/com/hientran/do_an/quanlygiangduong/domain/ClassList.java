package com.hientran.do_an.quanlygiangduong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class_list")
public class ClassList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "num_of_students")
    private Integer numOfStudents;

    @Column(name = "course")
    private String course;

    @JsonIgnore
    @OneToMany(mappedBy = "classList")
    private List<ClassroomStatusInfo> classroomStatusInfos;

    @Override
    public String toString() {
        return "ClassList{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", numOfStudents=" + numOfStudents +
                ", course='" + course + '\'' +
                '}';
    }
}
