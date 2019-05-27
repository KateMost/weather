package com.weather.rest.controller;

import com.weather.model.WeatherResponse;
import com.weather.service.WeatherService;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    private static final String CITY = "London";

    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;
    @InjectMocks
    private WeatherController subject;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(subject).build();
    }

    @Test
    void shouldObtainWeatherDataOnGet() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse();
        when(weatherService.getWeatherData(CITY)).thenReturn(weatherResponse);

        mockMvc.perform(get("/weather")
                .param("city", CITY))
                .andExpect(status().isOk());

        verify(weatherService).getWeatherData(CITY);
    }

}
