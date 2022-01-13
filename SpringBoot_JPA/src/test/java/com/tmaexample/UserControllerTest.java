package com.tmaexample;

import com.tmaexample.dto.UserDTO;
import com.tmaexample.entities.User;
import com.tmaexample.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private UserService userService;

    @Test
    public void viewUserByIDTest() throws Exception{
        Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(new UserDTO());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mock.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "{email:admin@testweb.com, name:admin}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
    @Test
    public void createUser() throws Exception {
        User mockUser = new User();
        UserDTO userDto = new UserDTO();
        userDto.setName(Mockito.anyString());
        userDto.setEmail(Mockito.anyString());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/user/create").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mock.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{email:dolphin9@testweb.com, name:dolphin9}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
