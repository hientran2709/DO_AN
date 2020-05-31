package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classroom_status_info")
public class ClassroomStatusInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "day", length = 50)
    private String day;

    @NotNull(message = "usedDate cant not null")
    @Column(name = "used_date")
    private Date usedDate;

    @Column(name = "shift_1")
    private Boolean shift1;

    @Column(name = "shift_2")
    private Boolean shift2;

    @Column(name = "shift_3")
    private Boolean shift3;

    @Column(name = "shift_4")
    private Boolean shift4;

    @Column(name = "shift_5̀")
    private Boolean shift5;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassList classList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomStatusInfo that = (ClassroomStatusInfo) o;
        return id == that.id &&
                Objects.equals(day, that.day) &&
                Objects.equals(usedDate, that.usedDate) &&
                Objects.equals(shift1, that.shift1) &&
                Objects.equals(shift2, that.shift2) &&
                Objects.equals(shift3, that.shift3) &&
                Objects.equals(shift4, that.shift4) &&
                Objects.equals(shift5, that.shift5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, usedDate, shift1, shift2, shift3, shift4, shift5);
    }
}
