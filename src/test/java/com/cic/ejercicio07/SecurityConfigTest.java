package com.cic.ejercicio07;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cic.ejercicio07.model.Coche;
import com.cic.ejercicio07.repository.CocheRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CocheRepository cocheRepository;

    
    // Mover la creación del coche a los tests específicos que lo necesitan
    private Coche coche;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*@Test
    public void whenAccessPublicEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk());
    }*/

    @Test
    @WithMockUser
    public void whenAuthenticatedUserAccessCoches_thenOk() throws Exception {
           // Crear coche solo cuando es necesario
            Coche coche = new Coche();
            coche.setMarca("Toyota");
            coche.setModelo("Corolla");
            cocheRepository.save(coche);

    mockMvc.perform(get("/coches")
            .param("page", "0")   // Agregar parámetro page
            .param("size", "10"))  // Agregar parámetro size
            .andExpect(status().isOk());
    }

    @Test
    public void whenUnauthenticatedUserAccessCoches_thenUnauthorized() throws Exception {
        SecurityContextHolder.clearContext(); // Limpiar el contexto para simular un usuario no autenticado
        mockMvc.perform(get("/coches")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isUnauthorized());
    }
}