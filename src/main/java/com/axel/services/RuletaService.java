package com.axel.services;

import com.axel.entities.Ruleta;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Getter
@Setter
public class RuletaService {
    private Ruleta ruleta;
    private Boolean isWinner;
    private Boolean isLoser;
    private int[] redNumbers;
    private int[] blackNumbers;
    private int greenNumber;
    private List<String> betType;
    private int longType;
    private Boolean parar;
    private Integer dinero;
    private Integer betMoney;
    private Integer correctNumber;

    public RuletaService() {
        this.ruleta = new Ruleta();
        this.betType = new ArrayList<>();
        this.isLoser = false;
        this.isWinner = false;
        this.parar = false;
        this.redNumbers = ruleta.getRed();
        this.blackNumbers = ruleta.getBlack();
        this.greenNumber = ruleta.getGreen();
        this.betType = ruleta.getBetTypes();
        this.longType = ruleta.getBetTypes().size();
        this.dinero = 7500;
        this.betMoney = 0;
    }

    public void play(String gameType, String betOption, Integer betMoney) {
        this.betMoney = betMoney; // dinero que aposto
        this.correctNumber = ruleta.getWinnerNumber(); // numero ganador
        if (parar) return; // si ya gano o perdio no puede jugar

        switch (gameType) { // tipo de apuesta
            case "0":
                if (betOption.equals("1-18")) { // si es un numero entre 1 y 18
                    if (ruleta.getWinnerNumber() <= 18) {
                        dinero += betMoney;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    }
                } else if (betOption.equals("19-36")) { // si es un numero entre 19 y 36
                    if (ruleta.getWinnerNumber() >= 19) {
                        dinero += betMoney;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    }
                }
                break;
            case "1":
                if (betOption.equals(ruleta.getWinnerNumber().toString())) { // si es el numero ganador
                    dinero += (betMoney * 8) - betMoney;
                    isWinner = true;
                    isLoser = false;
                } else {
                    dinero -= betMoney;
                    isLoser = true;
                    isWinner = false;
                }
                break;
            case "2":
                Boolean containsValue; // si el numero ganador esta en el array
                if (betOption.equals("red")) {
                    containsValue = contains(redNumbers, correctNumber); // si el numero ganador esta en el array de numeros rojos
                    if (containsValue) {
                        dinero += betMoney;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    }
                } else if (betOption.equals("black")) {
                    containsValue = contains(blackNumbers, correctNumber); // si el numero ganador esta en el array de numeros negros
                    if (containsValue) {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    } else {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    }
                } else if (betOption.equals("green")) {
                    if (Objects.equals(correctNumber, greenNumber)) { // si el numero ganador es el verde
                        dinero += (betMoney * 14) - betMoney;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    }
                }
                break;
            case "3":
                if (betOption.equals("even")) { // si el numero ganador es par
                    if (correctNumber % 2 == 0) {
                        dinero += betMoney;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    }
                } else if (betOption.equals("odd")) { // si el numero ganador es impar
                    if (correctNumber % 2 != 0) {
                        dinero += betMoney;
                        isWinner = true;
                        isLoser = false;
                    } else {
                        dinero -= betMoney;
                        isLoser = true;
                        isWinner = false;
                    }
                }

        }

        ruleta = new Ruleta();  // Reiniciamos la ruleta
        if (dinero <= 0) {      // Si el jugador no tiene dinero, se le cierra el juego
            this.parar = true;
        }
    }

    // Reiniciamos el juego
    public void reset() {
        this.ruleta = new Ruleta();
        this.betType = new ArrayList<>();
        this.isLoser = false;
        this.isWinner = false;
        this.parar = false;
        this.redNumbers = ruleta.getRed();
        this.blackNumbers = ruleta.getBlack();
        this.greenNumber = ruleta.getGreen();
        this.betType = ruleta.getBetTypes();
        this.longType = ruleta.getBetTypes().size();
        this.dinero = 7500;
        this.betMoney = 0;
    }

    // metodo para saber si un numero esta en un array
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
}
