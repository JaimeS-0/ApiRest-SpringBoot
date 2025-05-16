package com.jaime.apirest.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deniega acceso a /empleados a usuarios USER")
    @WithMockUser(username = "usuario", roles = {"USER"})
    void testAccesoDenegadoParaUser() throws Exception {
        mockMvc.perform(get("/api/v1/empleados"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Permite acceso a /empleados a usuarios ADMIN")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAccesoPermitidoParaAdmin() throws Exception {
        mockMvc.perform(get("/api/v1/empleados"))
                .andExpect(status().isOk());
    }
}
