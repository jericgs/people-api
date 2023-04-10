package br.com.peopleapi.peopleapi.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonConfigTest {

    private static final int BIRTH_YEAR = 1980;
    private static final int BIRTH_MONTH = 3;
    private static final int BIRTH_DAY = 10;

    @Test
    public void jacksonCustomizer() throws JsonProcessingException {
        JacksonConfig jacksonConfig = new JacksonConfig();
        Jackson2ObjectMapperBuilderCustomizer customizer = jacksonConfig.jacksonCustomizer();

        assertNotNull(customizer);

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        customizer.customize(builder);

        ObjectMapper objectMapper = builder.build();

        LocalDate localDate = LocalDate.of(BIRTH_YEAR, BIRTH_MONTH, BIRTH_DAY);
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String json = objectMapper.writeValueAsString(localDate);

        assertEquals("\"" + formattedDate + "\"", json);
    }

}
