package br.com.peopleapi.peopleapi.common;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class ModelMapperConfigTest {

    @Test
    public void modelMapperBeanCreation() {
        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();
        assertNotNull(modelMapperConfig.modelMapper(), "ModelMapper bean should not be null");
    }
}
