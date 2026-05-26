package com.thegamersplace.domain.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Uuid {
    protected String value;

    public Uuid(){
        this(generateUuid());
    }

    public Uuid(String value){
        this.value = value;
    }

    public static String generateUuid(){
        return java.util.UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
