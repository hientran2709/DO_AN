package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
