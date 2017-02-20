package com.exercise.controller;

import com.exercise.app.Application;
import com.exercise.model.RequestOwner;
import com.exercise.model.RequestRepository;
import com.exercise.service.JsonParser;
import com.exercise.service.RepositoryService;
import com.exercise.service.UserService;
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

    @MockBean
    private JsonParser jsonParser;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private UserService userService;


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
        RequestOwner requestOwner = new RequestOwner("userName", "gitHubId");

        URL url = new URL(String.format("https://localhost:8080/repositories/%s", requestOwner.getLogin()));

        String createdAt = "2015-05-18T19:06:24Z";

        RequestRepository requestRepository = new RequestRepository();
        requestRepository.setFullName("fullName");
        requestRepository.setCloneUrl("cloneUrl");
        requestRepository.setCreatedAt(createdAt);
        requestRepository.setDescription("description");
        requestRepository.setFork(false);
        requestRepository.setRequestOwner(requestOwner);

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(createdAt);
        String formattedDateTime = zonedDateTime.format(formatter);

        given(jsonParser.parseJsonFromUrl(new URL(String.format("https://api.github.com/users/%s/repos", requestOwner.getLogin()))))
                .willReturn(Collections.singletonList(requestRepository));

        mockMvc.perform(get(url.toURI()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.userName", is(requestOwner.getLogin())))
                .andExpect(jsonPath("$.gitHubId", is(requestOwner.getId())))
                .andExpect(jsonPath("$.repositories[0].fullName", is(requestRepository.getFullName())))
                .andExpect(jsonPath("$.repositories[0].description", is(requestRepository.getDescription())))
                .andExpect(jsonPath("$.repositories[0].cloneUrl", is(requestRepository.getCloneUrl())))
                .andExpect(jsonPath("$.repositories[0].createdAt", is(formattedDateTime)))
                .andExpect(jsonPath("$.repositories[0].fork", is(requestRepository.isFork())));
    }

    @Test
    public void noRepositoriesFound() throws Exception {
        RequestOwner requestOwner = new RequestOwner("userName", "gitHubId");
        URL url = new URL(String.format("https://localhost:8080/repositories/%s", requestOwner.getLogin()));

        given(jsonParser.parseJsonFromUrl(new URL(String.format("https://api.github.com/users/%s/repos", requestOwner.getLogin()))))
                .willReturn(Collections.emptyList());

        mockMvc.perform(get(url.toURI()))
                .andExpect(status().isNoContent());

    }

}