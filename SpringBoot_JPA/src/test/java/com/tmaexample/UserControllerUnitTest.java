package com.tmaexample;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.tmaexample.controller.UserController;
import com.tmaexample.dto.UserDTO;
import com.tmaexample.entities.User;
import com.tmaexample.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {
    @Autowired
    private MockMvc mock;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mock = MockMvcBuilders.standaloneSetup(userController)
                .addPlaceholderValue("/user", "/user")
                .build();
    }
    @Test
    public void get_all_success() throws Exception {
        List<UserDTO> user = Arrays.asList(
                new UserDTO("a","a@aam.com"),
                new UserDTO("b","b@aam.com"),
                new UserDTO("c","c@aam.com"));

        when(userService.findAll()).thenReturn(user);

        mock.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }
    @Test
    public void get_by_id_success() throws Exception {
        when(userService.findById(1)).thenReturn((new UserDTO("fd","gf@gmail.com")));

        mock.perform(get("/user/{id}", 9))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is("bronze")));

        verify(userService, times(1)).findById(1);
        verifyNoMoreInteractions(userService);
    }

}
