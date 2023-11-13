package com.intuit.player.converter;

import com.intuit.player.constant.PlayerFileConstants;
import com.intuit.player.entity.Player;
import com.intuit.player.exception.ConversionException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlayerConverter implements IConverter<String[], Player> {
    
    public Player Convert(String ... raw) throws ConversionException {
        return createPlayerFromRawStrings(raw);
    }

    private Player createPlayerFromRawStrings(String[] row) throws ConversionException{
        Player player = new Player();

        setPlayerId(row, player);

        int birthYear = Integer.parseInt(row[PlayerFileConstants.BIRTH_YEAR]);
        int birthMonth = Integer.parseInt(row[PlayerFileConstants.BIRTH_MONTH]);
        int birthDay = Integer.parseInt(row[PlayerFileConstants.BIRTH_DAY]);
        player.setBirthDate(LocalDate.of(birthYear, birthMonth, birthDay));

        player.setBirthCountry(row[PlayerFileConstants.BIRTH_COUNTRY]);
        player.setBirthState(row[PlayerFileConstants.BIRTH_STATE]);
        player.setBirthCity(row[PlayerFileConstants.BIRTH_CITY]);

        setDeathProperties(row, player);

        player.setNameFirst(row[PlayerFileConstants.FIRST_NAME]);
        player.setNameLast(row[PlayerFileConstants.LAST_NAME]);
        player.setNameGiven(row[PlayerFileConstants.GIVEN_NAME]);

        player.setWeight(Integer.parseInt(row[PlayerFileConstants.WEIGHT]));
        player.setHeight(Integer.parseInt(row[PlayerFileConstants.HEIGHT]));

        player.setBatsHand(row[PlayerFileConstants.BATS].charAt(0));
        player.setThrowsHand(row[PlayerFileConstants.THROWS].charAt(0));

        LocalDate debut = LocalDate.parse(row[PlayerFileConstants.DEBUT]);
        LocalDate finalGame = LocalDate.parse(row[PlayerFileConstants.FINAL_GAME]);
        player.setDebut(debut);
        player.setFinalGame(finalGame);

        player.setRetroID(row[PlayerFileConstants.RETRO_ID]);
        player.setBbrefID(row[PlayerFileConstants.BBREF_ID]);

        return player;
    }

    private static void setPlayerId(String[] row, Player player) throws ConversionException{
        String playerId = row[PlayerFileConstants.PLAYER_ID];
        validateNotEmptyOrNull(playerId);
        player.setPlayerID(playerId);
    }

    private static void setDeathProperties(String[] row, Player player) {
        if (!row[PlayerFileConstants.DEATH_YEAR].isEmpty()) {

            int deathYear = Integer.parseInt(row[PlayerFileConstants.DEATH_YEAR]);
            int deathMonth = Integer.parseInt(row[PlayerFileConstants.DEATH_MONTH]);
            int deathDay = Integer.parseInt(row[PlayerFileConstants.DEATH_DAY]);
            player.setDeathDate(LocalDate.of(deathYear, deathMonth, deathDay));

            player.setDeathCountry(row[PlayerFileConstants.DEATH_COUNTRY]);
            player.setDeathState(row[PlayerFileConstants.DEATH_STATE]);
            player.setDeathCity(row[PlayerFileConstants.DEATH_CITY]);
        }
    }

    private static void validateNotEmptyOrNull(String value) throws ConversionException {
        if (StringUtils.isNotEmpty(value))
            return;
        throw new ConversionException("Value "+value+" is Null or Empty");
    }

}
