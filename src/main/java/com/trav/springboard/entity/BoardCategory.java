package com.trav.springboard.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BoardCategory {

    JAVA("자바"), SPRING("스프링"), SPRINGBOOT("스프링 부트"), SRPINGSECURITY("스프링 시큐리티"), ETC("기타");

    private final String description;

    BoardCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    private static final List<BoardCategory> VALUES =

            Collections.unmodifiableList(Arrays.asList(values()));

    private static final int SIZE = VALUES.size();

    private static final Random RANDOM = new Random();

    public static BoardCategory randomCategory()  {

        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
