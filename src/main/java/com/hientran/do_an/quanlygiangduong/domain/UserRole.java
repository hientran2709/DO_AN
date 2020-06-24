package com.hientran.do_an.quanlygiangduong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {
    @EmbeddedId
    private UserRolePK id;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_name",referencedColumnName = "name" ,insertable = false,updatable = false)
    @JsonManagedReference
    @JsonIgnore
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id) &&
                Objects.equals(role, userRole.role) &&
                Objects.equals(user, userRole.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, user);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role=" + role +
                ", user=" + user +
                '}';
    }
}
