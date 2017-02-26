package com.exercise.controller;

import com.exercise.app.Application;
import com.exercise.model.RemoteRepository;
import com.exercise.model.RemoteUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by arecicalov on 2/20/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserRepoControllerTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.systemDefault());

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUsersRepositories() throws Exception {
        RemoteUser remoteUser = new RemoteUser("userName", "gitHubId");

        URL url = new URL(String.format("https://localhost:8080/repositories/%s", remoteUser.getLogin()));

        String createdAt = "2015-05-18T19:06:24Z";

        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.setFullName("fullName");
        remoteRepository.setCloneUrl("cloneUrl");
        remoteRepository.setCreatedAt(createdAt);
        remoteRepository.setDescription("description");
        remoteRepository.setFork(false);
        remoteRepository.setRemoteUser(remoteUser);

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(createdAt);
        String formattedDateTime = zonedDateTime.format(formatter);


        mockMvc.perform(get(url.toURI()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.userName", is(remoteUser.getLogin())))
                .andExpect(jsonPath("$.gitHubId", is(remoteUser.getId())))
                .andExpect(jsonPath("$.repositories[0].fullName", is(remoteRepository.getFullName())))
                .andExpect(jsonPath("$.repositories[0].description", is(remoteRepository.getDescription())))
                .andExpect(jsonPath("$.repositories[0].cloneUrl", is(remoteRepository.getCloneUrl())))
                .andExpect(jsonPath("$.repositories[0].createdAt", is(formattedDateTime)))
                .andExpect(jsonPath("$.repositories[0].fork", is(remoteRepository.isFork())));
    }

    @Test
    public void noRepositoriesFound() throws Exception {
        RemoteUser remoteUser = new RemoteUser("userName", "gitHubId");
        URL url = new URL(String.format("https://localhost:8080/repositories/%s", remoteUser.getLogin()));

        mockMvc.perform(get(url.toURI()))
                .andExpect(status().isNoContent());

    }

}