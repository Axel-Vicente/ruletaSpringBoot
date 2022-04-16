package com.axel.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Ruleta {
    private int green = 0;
    private int[] red = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
    private int[] black = { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    private List<String> betTypes;
    private Integer winnerNumber;

    public Ruleta() {
        this.betTypes = new ArrayList<>();
        betTypes.add("Por rango");
        betTypes.add("Por numero");
        betTypes.add("Por color");
        betTypes.add("Por paridad");
        winnerNumber = (int)(Math.random() * 36);
    }
}
