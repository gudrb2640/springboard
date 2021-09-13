package com.trav.springboard.entity;

public enum BoardCategory {

    JAVA("자바"), SPRING("스프링"), SPRINGBOOT("스프링 부트"), SRPINGSECURITY("스프링 시큐리티"), ETC("기타");

    private final String description;

    BoardCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
