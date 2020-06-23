package com.hientran.do_an.quanlygiangduong.service.dto;

import com.hientran.do_an.quanlygiangduong.domain.ClassList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CLassListDTO {
    private Integer id;
    private String className;
    private Integer numOfStudents;
    private String course;

    public CLassListDTO(ClassList classList) {
        this.id = classList.getId();
        this.className = classList.getClassName();
        this.numOfStudents = classList.getNumOfStudents();
        this.course = classList.getCourse();
    }
}
