package org.example.converters;

import jakarta.persistence.AttributeConverter;
import org.example.model.enums.Pohlavi;

public class PohlaviConverter implements AttributeConverter<Pohlavi, Character> {
    @Override
    public Character convertToDatabaseColumn(Pohlavi pohlavi) {
        if (pohlavi == null) {
            return null;
        }
        return pohlavi.getKod();
    }

    @Override
    public Pohlavi convertToEntityAttribute(Character character) {
        if (character == null) {
            return null;
        }
        return Pohlavi.getEnumFromKod(character);
    }
}
