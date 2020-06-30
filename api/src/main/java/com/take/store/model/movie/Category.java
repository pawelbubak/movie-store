package com.take.store.model.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Category {
    ACTION("ACTION"),
    CLASSICS("CLASSICS"),
    COMEDY("COMEDY"),
    DRAMA("DRAMA"),
    FAMILY("FAMILY"),
    SCI_FI("SCI-FI");

    private String value;

    Category(String category) {
        this.value = category;
    }

    @JsonCreator
    public static Category forValue(String value) {
        if (value == null) {
            return null;
        }

        return Stream.of(Category.values())
                .filter(category -> category.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
