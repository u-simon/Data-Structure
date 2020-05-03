package com.simon.datastructures.stack;

/**
 * @author simon
 * @date 2020/5/3 5:47 下午
 */
public enum Operational {
    /**
     *
     */
    ADD(1, "+"),
    SUB(1, "-"),
    MUL(2, "*"),
    DIV(2, "/");


    private int code;

    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    Operational(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Operational parseValue(String value) {
        Operational[] values = Operational.values();
        for (Operational operational : values) {
            if (value.equals(operational.value)) {
                return operational;
            }
        }
        return null;
    }
}
