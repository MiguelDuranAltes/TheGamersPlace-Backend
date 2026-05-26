package com.thegamersplace.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class Platform {
    private String name;
    private String slug;
    private Requirements requirements_en;

}
