package com.hientran.do_an.quanlygiangduong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news_feed")
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "title can not null")
    @Column(name = "title", length = 512)
    private String title;

    @Column(name = "content", nullable = true, length = 1024)
    private String content;

    @NotNull(message = "createdDate can not null")
    @Column(name = "created_date")
    private Instant createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsFeed newsFeed = (NewsFeed) o;
        return id == newsFeed.id &&
                Objects.equals(title, newsFeed.title) &&
                Objects.equals(content, newsFeed.content) &&
                Objects.equals(createdDate, newsFeed.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createdDate);
    }

    @Override
    public String toString() {
        return "NewsFeed{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
