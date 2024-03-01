package org.unlogged.springwebfluxdemo.converter;

import org.bson.json.StrictJsonWriter;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringToInstantConverter implements Converter<String, Instant> {
    @Override
    public Instant convert(String source) {
        {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(source, DateTimeFormatter.ISO_DATE_TIME);
                return localDateTime.toInstant(ZoneOffset.UTC);
            } catch (DateTimeParseException dtp) {
                return null;
            }
        }

    }
}
