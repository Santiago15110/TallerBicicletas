package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class TallerTest {
 /*
    private Taller taller;

    @BeforeEach
    void setUp() {
        taller = new Taller("BikeFix", "900123456-1", "Cra 15 #20-30");
    }

    //registrarCliente

    @Test
    void registrarCliente_deberiaRegistrarClienteNuevo() {
        // Act
        boolean resultado = taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");

        // Assert
        assertTrue(resultado);
        assertNotNull(taller.buscarClienteByCedula("123"));
    }

    @Test
    void registrarCliente_deberiaFallarSiCedulaYaExiste() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");

        // Act
        boolean resultado = taller.registrarCliente("Otro Nombre", "123", "3009999999", "Calle 2");

        // Assert
        assertFalse(resultado);
    }

    //buscarClienteByCedula

    @Test
    void buscarClienteByCedula_deberiaRetornarClienteExistente() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");

        // Act
        Cliente encontrado = taller.buscarClienteByCedula("123");

        // Assert
        assertNotNull(encontrado);
        assertEquals("123", encontrado.getCedula());
    }

    @Test
    void buscarClienteByCedula_deberiaRetornarNullSiNoExiste() {
        // Act
        Cliente encontrado = taller.buscarClienteByCedula("999");

        // Assert
        assertNull(encontrado);
    }
    //eliminarCliente

    @Test
    void eliminarCliente_deberiaEliminarClienteExistente() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");

        // Act
        boolean resultado = taller.eliminarCliente("123");

        // Assert
        assertTrue(resultado);
        assertNull(taller.buscarClienteByCedula("123"));
    }

    @Test
    void eliminarCliente_deberiaFallarSiClienteNoExiste() {
        // Act
        boolean resultado = taller.eliminarCliente("999");

        // Assert
        assertFalse(resultado);
    }

    //registrarCicla

    @Test
    void registrarCicla_deberiaRegistrarCiclaConClienteExistente() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");

        // Act
        boolean resultado = taller.registrarCicla("Trek", "Azul", "MC001", 2022, "123");

        // Assert
        assertTrue(resultado);
        assertNotNull(taller.buscarCiclaByNumeroMarco("MC001"));
    }

    @Test
    void registrarCicla_deberiaFallarSiClienteNoExiste() {
        // Act
        boolean resultado = taller.registrarCicla("Trek", "Azul", "MC001", 2022, "999");

        // Assert
        assertFalse(resultado);
    }

    @Test
    void registrarCicla_deberiaFallarSiNumeroMarcoYaExiste() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");
        taller.registrarCicla("Trek", "Azul", "MC001", 2022, "123");

        // Act
        boolean resultado = taller.registrarCicla("GW", "Rojo", "MC001", 2023, "123");

        // Assert
        assertFalse(resultado);
    }

    //buscarCiclaByNumeroMarco

    @Test
    void buscarCiclaByNumeroMarco_deberiaRetornarCiclaExistente() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");
        taller.registrarCicla("Trek", "Azul", "MC001", 2022, "123");

        // Act
        Cicla encontrada = taller.buscarCiclaByNumeroMarco("MC001");

        // Assert
        assertNotNull(encontrada);
        assertEquals("MC001", encontrada.getNumeroMarco());
    }

    //registrarMecanico

    @Test
    void registrarMecanico_deberiaRegistrarMecanicoNuevo() {
        // Act
        boolean resultado = taller.registrarMecanico("M01", "Carlos Ruiz");

        // Assert
        assertTrue(resultado);
        assertNotNull(taller.buscarMecanicoByCodigo("M01"));
    }

    @Test
    void registrarMecanico_deberiaFallarSiCodigoYaExiste() {
        // Arrange
        taller.registrarMecanico("M01", "Carlos Ruiz");

        // Act
        boolean resultado = taller.registrarMecanico("M01", "Andres Diaz");

        // Assert
        assertFalse(resultado);
    }

    //buscarMecanicoByCodigo

    @Test
    void buscarMecanicoByCodigo_deberiaRetornarNullSiNoExiste() {
        // Act
        Mecanico encontrado = taller.buscarMecanicoByCodigo("M99");

        // Assert
        assertNull(encontrado);
    }

    //registrarOrdenDeServicio

    @Test
    void registrarOrdenDeServicio_deberiaCrearOrdenCorrectamente() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");
        taller.registrarCicla("Trek", "Azul", "MC001", 2022, "123");
        taller.registrarMecanico("M01", "Carlos Ruiz");
        Cicla cicla = taller.buscarCiclaByNumeroMarco("MC001");
        Mecanico mecanico = taller.buscarMecanicoByCodigo("M01");

        // Act
        OrdenDeServicio orden = taller.registrarOrdenDeServicio(
                "OS001", LocalDate.of(2026, 9, 9), LocalTime.of(10, 30),
                "Cambio de frenos", "Frenos desgastados", cicla, mecanico);

        // Assert
        assertNotNull(orden);
        assertEquals("OS001", orden.getId());
    }

    //listHistorialByCicla

    @Test
    void listHistorialByCicla_deberiaRetornarOrdenesDeLaBicicleta() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");
        taller.registrarCicla("Trek", "Azul", "MC001", 2022, "123");
        taller.registrarMecanico("M01", "Carlos Ruiz");
        Cicla cicla = taller.buscarCiclaByNumeroMarco("MC001");
        Mecanico mecanico = taller.buscarMecanicoByCodigo("M01");
        taller.registrarOrdenDeServicio("OS001", LocalDate.of(2026, 9, 9),
                LocalTime.of(10, 30), "Cambio de frenos", "Frenos desgastados", cicla, mecanico);

        // Act
        ArrayList<OrdenDeServicio> historial = taller.listHistorialByCicla("MC001");

        // Assert
        assertEquals(1, historial.size());
    }

    @Test
    void listHistorialByCicla_deberiaRetornarVacioSiNoHayOrdenes() {
        // Act
        ArrayList<OrdenDeServicio> historial = taller.listHistorialByCicla("MCXXX");

        // Assert
        assertTrue(historial.isEmpty());
    }

    //listOrdenesByFecha

    @Test
    void listOrdenesByFecha_deberiaRetornarOrdenesDeLaFechaIndicada() {
        // Arrange
        taller.registrarCliente("Juan Perez", "123", "3001234567", "Calle 1");
        taller.registrarCicla("Trek", "Azul", "MC001", 2022, "123");
        taller.registrarMecanico("M01", "Carlos Ruiz");
        Cicla cicla = taller.buscarCiclaByNumeroMarco("MC001");
        Mecanico mecanico = taller.buscarMecanicoByCodigo("M01");
        LocalDate fecha = LocalDate.of(2026, 9, 9);
        taller.registrarOrdenDeServicio("OS001", fecha, LocalTime.of(10, 30),
                "Cambio de frenos", "Frenos desgastados", cicla, mecanico);

        // Act
        ArrayList<OrdenDeServicio> resultado = taller.listOrdenesByFecha(fecha);

        // Assert
        assertEquals(1, resultado.size());
    }

   */
}

