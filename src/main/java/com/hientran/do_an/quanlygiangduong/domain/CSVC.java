package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "csvc")
public class CSVC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "phong_id")
    private Integer phongId;

    @Column(name = "soban")
    private int soBan;

    @Column(name = "loaiban")
    private String loaiBan;

    @Column(name = "soghe")
    private int soGhe;

    @Column(name = "maychieu")
    private boolean mayChieu;

    @Column(name = "amthanh")
    private boolean amThanh;

    @Column(name = "dieuhoa")
    private boolean dieuHoa;

}
