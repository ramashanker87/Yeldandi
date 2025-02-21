package com.rama.app.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({OperationController.class})
public class OperationControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    public void addTest() throws Exception {
        long a= (long) 5.0;
        long b= (long)6.0;
        ResultActions result=mockMvc.perform(get("/add").param("a", String.valueOf(a)).param("b", String.valueOf(b)));
        result.andExpect(status().isOk());
        String resultData=result.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(resultData,"11");
    }

    @Test
    public void subTest() throws Exception {
        long a= (long) 13.0;
        long b= (long) 6.0;
        ResultActions result=mockMvc.perform(post("/sub").param("a", String.valueOf(a)).param("b", String.valueOf(b)));
        result.andExpect(status().isOk());
        String resultData=result.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(resultData,"7");
    }

    @Test
    public void mulTest() throws Exception {
        long a= (long) 3.0;
        long b= (long) 6.0;
        ResultActions result=mockMvc.perform(put("/mul").param("a", String.valueOf(a)).param("b", String.valueOf(b)));
        result.andExpect(status().isOk());
        String resultData=result.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(resultData,"18");
    }
}
