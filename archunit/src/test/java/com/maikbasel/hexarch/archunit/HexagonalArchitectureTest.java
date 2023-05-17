package com.maikbasel.hexarch.archunit;

import org.junit.jupiter.api.Test;

import static com.maikbasel.hexarch.archunit.Architectures.hexagonalArchitecture;
import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.*;

class HexagonalArchitectureTest {

    // description with optional layers

    @Test
    void givenHexArchWithConfigurationLayer_whenReadingDescription_thenShouldReturnConfigurationLayerDescription() {
        var sut = hexagonalArchitecture()
                .configurations("..config..");

        assertThat(sut.getDescription())
                .isEqualTo("Hexagonal architecture consisting of" +  lineSeparator() +
                        "configurations ('..config..')");
    }

    @Test
    void givenHexArchWithApplicationServiceLayer_whenReadingDescription_thenShouldReturnApplicationServiceLayerDescription() {
        var sut = hexagonalArchitecture()
                .applicationServices("..application.service..");

        assertThat(sut.getDescription())
                .isEqualTo("Hexagonal architecture consisting of" +  lineSeparator() +
                        "application services ('..application.service..')");
    }

    @Test
    void givenHexArchWithDrivenPortLayer_whenReadingDescription_thenShouldReturnDrivenPortLayerDescription() {
        var sut = hexagonalArchitecture()
                .drivenPorts("..domain.spi..");

        assertThat(sut.getDescription())
                .isEqualTo("Hexagonal architecture consisting of" +  lineSeparator() +
                        "driven ports ('..domain.spi..')");
    }

    @Test
    void givenHexArchWithDrivingPortLayer_whenReadingDescription_thenShouldReturnDrivingPortLayerDescription() {
        var sut = hexagonalArchitecture()
                .drivingPorts("..domain.api..");

        assertThat(sut.getDescription())
                .isEqualTo("Hexagonal architecture consisting of" +  lineSeparator() +
                        "driving ports ('..domain.api..')");
    }

    @Test
    void givenHexArchWithAllLayers_whenReadingDescription_thenShouldReturnFullLayerDescription() {

    }

    @Test
    void givenHexArchMissingAnyLayersOrOverriddenDescription_whenReadingDescription_thenShouldReturnDefaultDescription() {
        var sut = hexagonalArchitecture();

        assertThat(sut.getDescription()).isEqualTo("Hexagonal architecture consisting of");
    }

    @Test
    void givenHexArchWithOverriddenDescription_whenReadingDescription_thenShouldReturnOverriddenDescription() {
        var sut = hexagonalArchitecture()
                .as("overridden");

        assertThat(sut.getDescription()).isEqualTo("overridden");
    }

    @Test
    void givenHexArchWithOverriddenDescriptionWithReason_whenReadingDescription_thenShouldReturnOverriddenDescriptionWithReason() {
        var sut = hexagonalArchitecture()
                .as("overridden")
                .because("some reason");

        assertThat(sut.getDescription()).isEqualTo("overridden, because some reason");
    }
}