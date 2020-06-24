package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 250)
    private String description;

    @Override
    public String toString() {
        return "Roles{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
