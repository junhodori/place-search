package com.search.place.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RedisHash("topKeyword")
public class PopularKeyword implements Serializable {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private Integer num;

    @Builder
    public PopularKeyword(Integer id, String keyword, Integer num) {
        this.id = id;
        this.keyword = keyword;
        this.num = num;
    }
}
