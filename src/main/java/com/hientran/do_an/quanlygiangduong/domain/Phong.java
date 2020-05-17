package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phong")
public class Phong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sophong")
    private String soPhong;

    @Column(name = "toanha")
    private String toaNha;

    @Column(name = "tinhtrang")
    private boolean tinhTrang;


    @Override
    public String toString() {
        return "Phong{" +
                "id=" + id +
                ", soPhong='" + soPhong + '\'' +
                ", toaNha='" + toaNha + '\'' +
                ", tinhTrang=" + tinhTrang +
                '}';
    }
}
