package com.thegamersplace.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Ratings {
    private Integer id;

    private String title;

    private Long count;

    private Double percent;

    //cast temporal
    public Integer castRating(){
        if(this.title.equals("exceptional"))
            return 5;
        else if (this.title.equals("recommended")) {
            return 4;
        }
        else if (this.title.equals("meh")) {
            return 3;
        }
        else if (this.title.equals("skip")) {
            return 1;
        }
        else
            return 0;
    }
}
