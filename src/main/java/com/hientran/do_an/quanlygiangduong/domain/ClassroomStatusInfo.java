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

    @Column(name = "shift")
    private String shift;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "class_list_id")
    private ClassList classList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomStatusInfo that = (ClassroomStatusInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(day, that.day) &&
                Objects.equals(usedDate, that.usedDate) &&
                Objects.equals(shift, that.shift) &&
                Objects.equals(classRoom, that.classRoom) &&
                Objects.equals(classList, that.classList) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, usedDate, shift, classRoom, classList, user);
    }
    @Override
    public String toString() {
        return "ClassroomStatusInfo{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", usedDate=" + usedDate +
                ", shift=" + shift +
                '}';
    }
}
