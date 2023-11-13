package com.intuit.player.converter;

import com.intuit.player.constant.PlayerFileConstants;
import com.intuit.player.exception.ConversionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerConverterTests {

    private PlayerConverter playerConverter;

    @BeforeEach
    public void BeforeMethod() {
        playerConverter = new PlayerConverter();
    }

    @Test
    public void convert_EmptyData_ShouldThrowArrayOutOfBoundsException() {
        // Act & Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                playerConverter.Convert(new String[0]));
    }

    @Test
    public void convert_nullPlayerIdData_ShouldThrowConversionException() {
        // Arrange
        String expectedMessage = "Value null is Null or Empty";
        // Act & Assert
        ConversionException exception = assertThrows(ConversionException.class, () ->
                playerConverter.Convert(new String[10]));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void convert_EmptyPlayerIdData_ShouldThrowConversionException() {
        // Arrange
        var row = new String[10];
        row[PlayerFileConstants.PLAYER_ID] = "";

        String expectedMessage = "Value  is Null or Empty";
        // Act & Assert
        ConversionException exception = assertThrows(ConversionException.class, () ->
                playerConverter.Convert(row));
        assertEquals(expectedMessage, exception.getMessage());
    }
}
