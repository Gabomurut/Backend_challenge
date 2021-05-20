package com.challenge.backend;

import com.challenge.backend.Data.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

    private MockMvc mockMvc;
    private PostRepository mockRepository;

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(this.applicationContext)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void getAllPosts() throws Exception {
        this.mockMvc
            .perform(
                get("/posts")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
    }

    @Test
    public void getOnePost() throws Exception {
        this.mockMvc
            .perform(
                get("/posts/3")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNotFound());
    }




    /*@Test
    @WithUserDetails()
    public void testSaveWithMock() throws Exception {

        this.mockMvc
            .perform(
                post("/posts/1")
                    .content(this.objectMapper.writeValueAsBytes(VehicleForm.builder().name("test").build()))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isCreated());
    }*/

}