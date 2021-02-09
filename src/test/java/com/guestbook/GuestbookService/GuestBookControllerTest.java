package com.guestbook.GuestbookService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
class GuestBookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void addEntry() throws Exception {
        Guest guest = new Guest("jack","Good");
        mockMvc.perform(post("/entry")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(guest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("jack"))
                .andExpect(jsonPath("$.comments").value("Good"))
                .andDo(document("AddGuest",
                        requestFields(
                                fieldWithPath("name").description("Name of the Guest"),
                                fieldWithPath("comments").description("Comments given by Guest"))));
    }

    @Test
    public void viewEntries() throws Exception {
        Guest guest = new Guest("jack","Good");
        Guest guest1 = new Guest("jill", "Not so Good");
        List<Guest> guestList = List.of(guest, guest1);
        mockMvc.perform(post("/entry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(guest)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/entry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(guest1)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/entry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("jack"))
                .andExpect(jsonPath("$.[0].comments").value("Good"))
                .andExpect(jsonPath("$.[1].name").value("jill"))
                .andExpect(jsonPath("$.[1].comments").value("Not so Good"))
                .andDo(document("ViewGuest",
                        responseFields(
                                fieldWithPath("[]").description("Array of Guests and their comments"),
                                fieldWithPath("[].name").description("Name of the Guest"),
                                fieldWithPath("[].comments").description("Comments given by Guest"))));
    }

}