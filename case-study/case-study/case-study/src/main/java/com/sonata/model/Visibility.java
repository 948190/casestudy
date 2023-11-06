package com.sonata.model;

public enum Visibility {
    YES("yes"),
    NO("no");

    private final String value;

    Visibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Visibility fromValue(String value) {
        for (Visibility visibility : values()) {
            if (visibility.value.equalsIgnoreCase(value)) {
                return visibility;
            }
        }
        return NO; 
    }
}
