package com.thro.sqsdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CustomAddressInformationControllerTest {
    
    @Autowired
    private MockMvc application;
    
    @Test
    void AddressInformationController_addInformationField_noDomain() throws Exception {
        this.application.perform(post("/information/add")
            .param("address", "8.8.8.8")
            .param("name", "type")
            .param("value", "DNS")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(404));
    }

    @Test
    void AddressInformationController_addGetInformation() throws Exception {
        this.application.perform(post("/address/add")
            .param("address", "4.4.4.4")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));

        this.application.perform(post("/information/add")
            .param("address", "4.4.4.4")
            .param("name", "type")
            .param("value", "DNS")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }

    @Test
    void AddressInformationController_deleteInformation() throws Exception {
        this.application.perform(post("/address/add")
            .param("address", "6.6.6.6")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));

        this.application.perform(post("/information/add")
            .param("address", "6.6.6.6")
            .param("name", "type")
            .param("value", "DNS")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));

        this.application.perform(delete("/information/delete")
            .param("address", "6.6.6.6")
            .param("name", "type")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }
}
