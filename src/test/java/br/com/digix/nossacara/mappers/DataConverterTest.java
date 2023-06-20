package br.com.digix.nossacara.mappers;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DataConverterTest {

    @Test
    void toDate() {
        LocalDateTime localDateTime = DataConverter.toDate("1677181801486");
        assertThat(localDateTime.getHour()).isEqualTo(15);
    }
}