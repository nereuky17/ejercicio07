package com.cic.ejercicio07;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Clase de pruebas de integración para la configuración de seguridad.
 * Verifica el comportamiento de la seguridad de la aplicación al acceder a endpoints específicos relacionados con los coches.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CocheSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Prueba que verifica que al intentar acceder al endpoint "/coches" sin autenticación,
     * la respuesta sea HTTP 401 Unauthorized.
     */
    
    @Test
    public void whenAccessCochesWithoutAuthentication_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/coches"))
            .andExpect(status().isUnauthorized());
    }

    /**
     * Prueba que verifica que al intentar acceder al endpoint "/coches" con un usuario autenticado,
     * la respuesta sea HTTP 200 OK.
     * 
     * La anotación @WithMockUser simula un usuario autenticado para la prueba.
     */
    @Test
    @WithMockUser
    public void whenAccessCochesWithAuthentication_thenOk() throws Exception {
        mockMvc.perform(get("/coches"))
            .andExpect(status().isOk());
    }

    /**
     * Prueba que verifica que al intentar acceder al endpoint "/coches/1" sin autenticación,
     * la respuesta sea HTTP 401 Unauthorized.
     */
    @Test
    public void whenAccessCocheByIdWithoutAuthentication_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/coches/1"))
            .andExpect(status().isUnauthorized());
    }

    /**
     * Prueba que verifica que al intentar acceder al endpoint "/coches/1" con un usuario autenticado,
     * la respuesta sea HTTP 200 OK.
     * 
     * La anotación @WithMockUser simula un usuario autenticado para la prueba.
     */
    @Test
    @WithMockUser
    public void whenAccessCocheByIdWithAuthentication_thenOk() throws Exception {
        mockMvc.perform(get("/coches/1"))
            .andExpect(status().isOk());
    }
}
