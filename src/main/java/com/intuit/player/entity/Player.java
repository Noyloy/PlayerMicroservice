package com.intuit.player.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Data
public class Player {

    @Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerID;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private int weight;
    private int height;
    private char batsHand;
    private char throwsHand;
    private LocalDate debut;
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;

    public Player() {}

}
