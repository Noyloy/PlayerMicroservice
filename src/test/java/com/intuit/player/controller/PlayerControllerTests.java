package com.intuit.player.controller;

import com.intuit.player.entity.Player;
import com.intuit.player.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerControllerTests {
    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    public void getAllPlayers_callsPlayerServiceGetAllPlayers() {
        // Act
        playerController.getAllPlayers();
        // Assert
        verify(playerService, times(1)).getAllPlayers();
    }

    @Test
    public void getPlayerById_callsPlayerServiceGetPlayerById_returnsOK() {
        // Arrange
        String playerId = "NoyLoy123";
        var p = new Player();
        p.setPlayerID(playerId);
        when(playerService.getPlayerById(playerId)).thenReturn(Optional.of(p));

        // Act
        var response = playerController.getPlayerById(playerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(playerId, response.getBody().getPlayerID());
    }

    @Test
    public void getPlayerById_whenServiceEmpty_returnsNotFound() {
        // Arrange
        String playerId = "NoyLoy123";
        when(playerService.getPlayerById(playerId)).thenReturn(Optional.empty());

        // Act
        var response = playerController.getPlayerById(playerId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
