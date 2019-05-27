package com.weather.integration;

import com.google.common.cache.LoadingCache;
import com.weather.WeatherApplication;
import com.weather.model.WeatherResponse;
import com.weather.rest.controller.WeatherController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeatherApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WeatherController weatherController;
    @Autowired
    private LoadingCache<String, WeatherResponse> weatherCache;

    @Test
    void shouldCacheObtainedThroughAPIResponses() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London"));
        assertEquals(0, weatherCache.stats().hitCount());
        mockMvc.perform(get("/weather")
                .param("city", "London"));
        assertEquals(1, weatherCache.stats().hitCount());
        mockMvc.perform(get("/weather")
                .param("city", "Kharkiv"));
        assertEquals(1, weatherCache.stats().hitCount());
    }


}
