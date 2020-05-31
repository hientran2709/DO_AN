package com.hientran.do_an.quanlygiangduong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "infrastructure")
public class Infrastructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_of_table")
    private int numOfTable;

    @Column(name = "table_type")
    private String tableType;

    @Column(name = "num_of_chair")
    private int numOfChair;

    @Column(name = "projector")
    private boolean projector;

    @Column(name = "sound")
    private boolean sound;

    @Column(name = "refresher")
    private boolean refresher;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @Override
    public String toString() {
        return "Infrastructure{" +
                "id=" + id +
                ", numOfTable=" + numOfTable +
                ", tableType='" + tableType + '\'' +
                ", numOfChair=" + numOfChair +
                ", projector=" + projector +
                ", sound=" + sound +
                ", refresher=" + refresher +
                '}';
    }
}
