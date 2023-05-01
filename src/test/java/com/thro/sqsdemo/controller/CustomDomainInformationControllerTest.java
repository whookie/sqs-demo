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
class CustomDomainInformationControllerTest {
    
    @Autowired
    private MockMvc application;

    @Test
    void DomainInformationController_GetNoDomainTest() throws Exception {
        this.application.perform(get("/domain/get").param("domain", "doesnotexist.com")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(404));
    }

    @Test
    void DomainInformationController_AddOneDomainTest() throws Exception {
        this.application.perform(post("/domain/add").param("domain", "newdomain.com")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }

    @Test
    void DomainInformationController_AddDuplicateDomainTest() throws Exception {
        this.application.perform(post("/domain/add").param("domain", "duplicatedomain.com")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));

        this.application.perform(post("/domain/add").param("domain", "duplicatedomain.com")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(409));
    }

    @Test
    void DomainInformationController_GetDomain_DomainFound() throws Exception {
        this.application.perform(post("/domain/add").param("domain", "example.com")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
        
        this.application.perform(get("/domain/get").param("domain", "example.com")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200))
        .andExpect(content().json("{\"domain\": \"example.com\", \"informationFields\": []}", false));
    }
}
