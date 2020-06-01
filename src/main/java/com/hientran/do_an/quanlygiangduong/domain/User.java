package com.hientran.do_an.quanlygiangduong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Column(name = "password", nullable = true, length = 100)
    private String password;

    @Column(name = "phone", nullable = true, length = 50)
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ClassroomStatusInfo> classroomStatusInfos;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
//                ", classroomStatusInfos=" + classroomStatusInfos +
                '}';
    }
}
