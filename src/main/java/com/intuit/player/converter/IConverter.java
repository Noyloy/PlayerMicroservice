package com.intuit.player.converter;

import com.intuit.player.exception.ConversionException;

public interface IConverter <FROM, TO>{
    TO Convert (FROM arg) throws ConversionException;
}
