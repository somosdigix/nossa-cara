package br.com.digix.nossacara.mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DataConverter {
    public static LocalDateTime toDate(String milisegundos) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(milisegundos));
        return LocalDateTime.ofInstant(instant, ZoneId.of("America/Campo_Grande")).withNano(0);
    }
}
