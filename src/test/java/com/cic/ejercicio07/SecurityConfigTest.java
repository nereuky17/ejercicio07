package com.cic.ejercicio07;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // Crear un coche en la base de datos antes de cada test
        Coche coche = new Coche();
        coche.setMarca("Toyota");
        coche.setModelo("Corolla");
        cocheRepository.save(coche);
    }

    @Test
    public void whenAccessPublicEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void whenAuthenticatedUserAccessCoches_thenOk() throws Exception {
        mockMvc.perform(get("/coches"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenUnauthenticatedUserAccessCoches_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/coches"))
                .andExpect(status().isUnauthorized());
    }
}