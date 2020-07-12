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
@RedisHash("keyword")
public class Keyword implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private Integer num;

    @Builder
    public Keyword(String id, Integer num) {
        this.id = id;
        this.num = num;
    }
}
