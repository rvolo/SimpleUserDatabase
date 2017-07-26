package com.simpleuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleuser.model.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the user controller
 * MethodSorter is used to make sure that CreateUser is called before GetAllUsers and GetUser
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    private String userName = "test name";
    private String userEmail = "test@test.ca";

    @Test
    public void test01_CreateUser() throws Exception {
        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(userName, userEmail)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    public void test02_GetAllUsers() throws Exception {
        test01_CreateUser();

        mockMvc.perform(get("/users/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo(userName)))
                .andExpect(jsonPath("$[0].email", equalTo(userEmail)))
                .andExpect(jsonPath("$[1].userId", equalTo(2)))
                .andExpect(jsonPath("$[1].name", equalTo(userName)))
                .andExpect(jsonPath("$[1].email", equalTo(userEmail)));
    }

    @Test
    public void test03_GetUser() throws Exception {
        mockMvc.perform(get("/users/2/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.userId", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo(userName)))
                .andExpect(jsonPath("$.email", equalTo(userEmail)));
    }

    @Test
    public void test04_UpdateUser() throws Exception {
        String newName = "new name";
        String newEmail = "new@email.ca";
        mockMvc.perform(put("/users/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(newName, newEmail)))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));

        mockMvc.perform(get("/users/1/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.userId", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(newName)))
                .andExpect(jsonPath("$.email", equalTo(newEmail)));
    }

    @Test
    public void test05_DeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test(expected = NestedServletException.class)
    public void test06_CreateUser_NullName() throws Exception {
        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(null, userEmail)))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test(expected = NestedServletException.class)
    public void test07_CreateUser_NullEmail() throws Exception {
        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(userName, null)))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test(expected = NestedServletException.class)
    public void test08_CreateUser_EmptyName() throws Exception {
        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User("", userEmail)))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test(expected = NestedServletException.class)
    public void test09_CreateUser_EmptyEmail() throws Exception {
        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(userName, "")))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test(expected = NestedServletException.class)
    public void test10_UpdateUser_NullName() throws Exception {
        mockMvc.perform(put("/users/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(null, userEmail)))
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test(expected = NestedServletException.class)
    public void test11_UpdateUser_NullEmail() throws Exception {
        mockMvc.perform(put("/users/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(userName, null)))
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test(expected = NestedServletException.class)
    public void test12_UpdateUser_EmptyName() throws Exception {
        mockMvc.perform(put("/users/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User("", userEmail)))
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test(expected = NestedServletException.class)
    public void test12_UpdateUser_EmptyEmail() throws Exception {
        mockMvc.perform(put("/users/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User(userName, "")))
                .accept(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void test13_GetUser_NoExist() throws Exception {
        mockMvc.perform(get("/users/200/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(""));
    }

}

