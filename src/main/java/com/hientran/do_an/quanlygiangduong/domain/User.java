package com.hientran.do_an.quanlygiangduong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", length = 100, unique = true)
    private String userId;

    @Column(name = "name", length = 50)
    private String name;

    @NotNull
    @Email
    @Column(name = "email", length = 50,unique = true)
    private String email;

    @NotNull
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "phone", length = 50)
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ClassroomStatusInfo> classroomStatusInfos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) ;
//                Objects.equals(classroomStatusInfos, user.classroomStatusInfos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, password, phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", classroomStatusInfos=" + classroomStatusInfos +
                '}';
    }
}
