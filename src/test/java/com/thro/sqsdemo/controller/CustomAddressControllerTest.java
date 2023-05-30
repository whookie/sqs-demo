package com.thro.sqsdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CustomAddressControllerTest {
    
    @Autowired
    private MockMvc application;

    @Test
    void AddressController_GetNoAddressTest() throws Exception {
        this.application.perform(get("/address/get").param("address", "10.10.10.10")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(404));
    }

    @Test
    void AddressController_AddOneAddressTest() throws Exception {
        this.application.perform(post("/address/add").param("address", "240.0.0.2")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }

    @Test
    void AddressController_AddDuplicateAddressTest() throws Exception {
        this.application.perform(post("/address/add").param("address", "240.0.0.3")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));

        this.application.perform(post("/address/add").param("address", "240.0.0.3")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(409));
    }

    @Test
    void AddressController_GetAddress_AddressFound() throws Exception {
        this.application.perform(post("/address/add").param("address", "2.2.2.2")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
        
        this.application.perform(get("/address/get").param("address", "2.2.2.2")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }

    @Test
    void AddressController_AddInvalidAddress() throws Exception {
        this.application.perform(post("/address/add").param("address", "256.0.0.1")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(400));
    }

    @Test
    void AddressController_AddGetValidAddressWithFields() throws Exception {
        this.application.perform(post("/address/add").param("address", "1.1.1.1")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }
}
